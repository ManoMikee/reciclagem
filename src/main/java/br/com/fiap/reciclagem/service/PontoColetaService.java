package br.com.fiap.reciclagem.service;

import br.com.fiap.reciclagem.model.PontoColeta;
import br.com.fiap.reciclagem.repository.PontoColetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PontoColetaService {

    private final PontoColetaRepository repository;

    public PontoColetaService(PontoColetaRepository repository) {
        this.repository = repository;
    }

    public PontoColeta salvar(PontoColeta pontoColeta) {
        return repository.save(pontoColeta);
    }

    public List<PontoColeta> listarTodos() {
        return repository.findAll();
    }

    public PontoColeta buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}