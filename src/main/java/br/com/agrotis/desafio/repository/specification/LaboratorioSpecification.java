package br.com.agrotis.desafio.repository.specification;

import br.com.agrotis.desafio.model.Laboratorio;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class LaboratorioSpecification {

    public static Specification<Laboratorio> filters(String nome) {

        List<Specification<Laboratorio>> specifications = new ArrayList<>();
        if (nome != null && !nome.isBlank()) {
            specifications.add((root, query, builder) -> builder.like(builder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));
        }

        return Specification.allOf(specifications);
    }
}
