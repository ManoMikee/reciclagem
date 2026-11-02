package br.com.fiap.reciclagem.service;

import br.com.fiap.reciclagem.model.TipoMaterial;
import br.com.fiap.reciclagem.repository.TipoMaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoMaterialService {

    private final TipoMaterialRepository repository;

    public TipoMaterialService(TipoMaterialRepository repository) {
        this.repository = repository;
    }

    public TipoMaterial salvar(TipoMaterial tipoMaterial) {
        return repository.save(tipoMaterial);
    }

    public List<TipoMaterial> listarTodos() {
        return repository.findAll();
    }

    public TipoMaterial buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}