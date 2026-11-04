
package br.com.fiap.reciclagem.service;

import br.com.fiap.reciclagem.model.Deposito;
import br.com.fiap.reciclagem.model.PontoColeta;
import br.com.fiap.reciclagem.repository.DepositoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DepositoService {

    private final DepositoRepository repository;
    private final PontoColetaService pontoColetaService;

    public DepositoService(
            DepositoRepository repository,
            PontoColetaService pontoColetaService) {
        this.repository = repository;
        this.pontoColetaService = pontoColetaService;
    }

    // CADASTRAR DEPÓSITO ATUALIZANDO O ESTOQUE
    @Transactional
    public Deposito registrarDeposito(Deposito deposito) {

        PontoColeta pontoColeta = deposito.getPontoColeta();

        BigDecimal estoqueAtual = pontoColeta.getQuantidadeAtualKg();
        BigDecimal quantidadeDepositada = deposito.getQuantidadeKg();
        BigDecimal capacidadeMaxima = pontoColeta.getCapacidadeKg();

        BigDecimal novoEstoque = estoqueAtual.add(quantidadeDepositada);

        if (novoEstoque.compareTo(capacidadeMaxima) > 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Depósito não permitido: a capacidade máxima do ponto seria ultrapassada."
            );
        }

        pontoColeta.setQuantidadeAtualKg(novoEstoque);
        pontoColetaService.salvar(pontoColeta);

        return repository.save(deposito);
    }

    // ATUALIZAR DEPÓSITO E AJUSTAR O ESTOQUE
    @Transactional
    public Deposito atualizarDeposito(Long id, Deposito novosDados) {

        Deposito deposito = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Depósito não encontrado."
                ));

        PontoColeta pontoAntigo = deposito.getPontoColeta();
        PontoColeta pontoNovo = novosDados.getPontoColeta();

        BigDecimal quantidadeAntiga = deposito.getQuantidadeKg();
        BigDecimal quantidadeNova = novosDados.getQuantidadeKg();

        // REVERTER O DEPÓSITO ORIGINAL
        BigDecimal estoqueAntigo = pontoAntigo.getQuantidadeAtualKg()
                .subtract(quantidadeAntiga);

        if (estoqueAntigo.compareTo(BigDecimal.ZERO) < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Não é possível atualizar: o estoque atual é menor que a quantidade do depósito original."
            );
        }

        pontoAntigo.setQuantidadeAtualKg(estoqueAntigo);

        // CALCULAR O NOVO ESTOQUE DO PONTO DE DESTINO
        BigDecimal estoqueNovo = pontoNovo.getQuantidadeAtualKg();

        if (pontoAntigo.getId().equals(pontoNovo.getId())) {
            estoqueNovo = estoqueAntigo;
        }

        BigDecimal resultado = estoqueNovo.add(quantidadeNova);

        // VERIFICAR CAPACIDADE DO PONTO DE DESTINO
        if (resultado.compareTo(pontoNovo.getCapacidadeKg()) > 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Atualização não permitida: a capacidade máxima do ponto seria ultrapassada."
            );
        }

        // ATUALIZAR ESTOQUE DO PONTO DE DESTINO
        pontoNovo.setQuantidadeAtualKg(resultado);

        pontoColetaService.salvar(pontoAntigo);

        if (!pontoAntigo.getId().equals(pontoNovo.getId())) {
            pontoColetaService.salvar(pontoNovo);
        }

        // ATUALIZAR DADOS DO DEPÓSITO
        deposito.setQuantidadeKg(quantidadeNova);
        deposito.setDataDeposito(novosDados.getDataDeposito());
        deposito.setPontoColeta(pontoNovo);

        return repository.save(deposito);
    }

    // SALVAR DEPÓSITO
    public Deposito salvar(Deposito deposito) {
        return repository.save(deposito);
    }

    // LISTAR TODOS
    public List<Deposito> listarTodos() {
        return repository.findAll();
    }

    // BUSCAR POR ID
    public Deposito buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    // EXCLUIR DEPÓSITO E AJUSTAR O ESTOQUE
    @Transactional
    public void excluir(Long id) {

        Deposito deposito = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Depósito não encontrado."
                ));

        PontoColeta pontoColeta = deposito.getPontoColeta();

        BigDecimal estoqueAtual = pontoColeta.getQuantidadeAtualKg();
        BigDecimal quantidadeDepositada = deposito.getQuantidadeKg();

        BigDecimal novoEstoque = estoqueAtual.subtract(quantidadeDepositada);

        if (novoEstoque.compareTo(BigDecimal.ZERO) < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Não é possível excluir: o estoque atual é menor que a quantidade do depósito."
            );
        }

        pontoColeta.setQuantidadeAtualKg(novoEstoque);

        pontoColetaService.salvar(pontoColeta);

        repository.delete(deposito);
    }
}
