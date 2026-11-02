package br.com.fiap.reciclagem.repository;

import br.com.fiap.reciclagem.model.PontoColeta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PontoColetaRepository extends JpaRepository<PontoColeta, Long> {
}