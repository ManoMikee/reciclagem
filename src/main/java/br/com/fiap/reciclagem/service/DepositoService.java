package br.com.fiap.reciclagem.service;

import br.com.fiap.reciclagem.model.Deposito;
import br.com.fiap.reciclagem.repository.DepositoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositoService {

    private final DepositoRepository repository;

    public DepositoService(DepositoRepository repository) {
        this.repository = repository;
    }

    public Deposito salvar(Deposito deposito) {
        return repository.save(deposito);
    }

    public List<Deposito> listarTodos() {
        return repository.findAll();
    }

    public Deposito buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}