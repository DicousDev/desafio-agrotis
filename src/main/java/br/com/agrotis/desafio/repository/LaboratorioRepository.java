package br.com.agrotis.desafio.repository;

import br.com.agrotis.desafio.model.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long>  {
}
