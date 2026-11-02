package br.com.fiap.reciclagem.repository;

import br.com.fiap.reciclagem.model.AlertaColeta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertaColetaRepository extends JpaRepository<AlertaColeta, Long> {
}