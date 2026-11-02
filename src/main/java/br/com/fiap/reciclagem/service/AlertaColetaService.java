package br.com.fiap.reciclagem.service;

import br.com.fiap.reciclagem.model.AlertaColeta;
import br.com.fiap.reciclagem.repository.AlertaColetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertaColetaService {

    private final AlertaColetaRepository repository;

    public AlertaColetaService(AlertaColetaRepository repository) {
        this.repository = repository;
    }

    public AlertaColeta salvar(AlertaColeta alertaColeta) {
        return repository.save(alertaColeta);
    }

    public List<AlertaColeta> listarTodos() {
        return repository.findAll();
    }

    public AlertaColeta buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}