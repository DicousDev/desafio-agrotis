package br.com.agrotis.desafio.repository;

import br.com.agrotis.desafio.model.Propriedade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropriedadeRepository extends JpaRepository<Propriedade, Long> {
}
