
package br.com.fiap.reciclagem.service;

import br.com.fiap.reciclagem.model.Coleta;
import br.com.fiap.reciclagem.model.PontoColeta;
import br.com.fiap.reciclagem.repository.ColetaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ColetaService {

    private final ColetaRepository repository;
    private final PontoColetaService pontoColetaService;

    public ColetaService(
            ColetaRepository repository,
            PontoColetaService pontoColetaService) {
        this.repository = repository;
        this.pontoColetaService = pontoColetaService;
    }

    // REGISTRAR COLETA COM ATUALIZAÇÃO DO ESTOQUE
    @Transactional
    public Coleta registrarColeta(Coleta coleta) {

        PontoColeta pontoColeta = coleta.getPontoColeta();

        BigDecimal estoqueAtual = pontoColeta.getQuantidadeAtualKg();
        BigDecimal quantidadeColetada = coleta.getQuantidadeKg();

        // VERIFICAR SE EXISTE ESTOQUE SUFICIENTE
        if (estoqueAtual.compareTo(quantidadeColetada) < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Estoque insuficiente para realizar esta coleta."
            );
        }

        // DESCONTAR A QUANTIDADE COLETADA
        BigDecimal novoEstoque = estoqueAtual.subtract(quantidadeColetada);

        pontoColeta.setQuantidadeAtualKg(novoEstoque);

        // SALVAR O NOVO ESTOQUE
        pontoColetaService.salvar(pontoColeta);

        // SALVAR A COLETA
        return repository.save(coleta);
    }

    public Coleta salvar(Coleta coleta) {
        return repository.save(coleta);
    }

    public List<Coleta> listarTodos() {
        return repository.findAll();
    }

    public Coleta buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
