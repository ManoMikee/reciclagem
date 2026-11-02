package br.com.fiap.reciclagem.service;

import br.com.fiap.reciclagem.model.Coleta;
import br.com.fiap.reciclagem.repository.ColetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColetaService {

    private final ColetaRepository repository;

    public ColetaService(ColetaRepository repository) {
        this.repository = repository;
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