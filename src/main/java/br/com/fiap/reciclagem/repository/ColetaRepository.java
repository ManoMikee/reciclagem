package br.com.fiap.reciclagem.repository;

import br.com.fiap.reciclagem.model.Coleta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColetaRepository extends JpaRepository<Coleta, Long> {
}