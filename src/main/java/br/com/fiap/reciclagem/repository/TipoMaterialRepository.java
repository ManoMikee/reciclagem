package br.com.fiap.reciclagem.repository;

import br.com.fiap.reciclagem.model.TipoMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoMaterialRepository extends JpaRepository<TipoMaterial, Long> {
}