package br.com.fiap.reciclagem.repository;

import br.com.fiap.reciclagem.model.Deposito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositoRepository extends JpaRepository<Deposito, Long> {
}