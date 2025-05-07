package br.com.agrotis.desafio.repository.specification;

import br.com.agrotis.desafio.dto.filter.PessoaFilter;
import br.com.agrotis.desafio.model.Pessoa;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class PessoaSpecification {

    public static Specification<Pessoa> filter(PessoaFilter filter) {
        return (root, query, builder) -> {
            Predicate predicate = builder.conjunction();
            root.fetch("propriedade", JoinType.LEFT);
            root.fetch("laboratorio", JoinType.LEFT);

            if(Objects.nonNull(filter.getNome()) && !filter.getNome().isBlank()) {
                predicate = builder.and(predicate, builder.like(builder.lower(root.get("nome")), "%" + filter.getNome().toLowerCase() + "%"));
            }

            if(Objects.nonNull(filter.getObservacoes()) && !filter.getObservacoes().isBlank()) {
                predicate = builder.and(predicate, builder.like(builder.lower(root.get("observacoes")), "%" + filter.getObservacoes().toLowerCase() + "%"));
            }

            if (Objects.nonNull(filter.getDataInicialMinima())) {
                Predicate dataInicialMinimaPredicate = builder.greaterThanOrEqualTo(root.get("dataInicial"), filter.getDataInicialMinima());
                predicate = builder.and(predicate, dataInicialMinimaPredicate);
            }

            if (Objects.nonNull(filter.getDataInicialMaxima())) {
                Predicate dataInicialMaximaPredicate = builder.lessThanOrEqualTo(root.get("dataInicial"), filter.getDataInicialMaxima());
                predicate = builder.and(predicate, dataInicialMaximaPredicate);
            }

            if (Objects.nonNull(filter.getDataFinalMinima())) {
                Predicate dataFinalMinimaPredicate = builder.greaterThanOrEqualTo(root.get("dataFinal"), filter.getDataFinalMinima());
                predicate = builder.and(predicate, dataFinalMinimaPredicate);
            }

            if (Objects.nonNull(filter.getDataFinalMaxima())) {
                Predicate dataFinalMaximaPredicate = builder.lessThanOrEqualTo(root.get("dataFinal"), filter.getDataFinalMaxima());
                predicate = builder.and(predicate, dataFinalMaximaPredicate);
            }

            if(Objects.nonNull(filter.getNomePropriedades()) && !filter.getNomePropriedades().isEmpty()) {
                predicate = builder.and(predicate, root.get("propriedade").get("nome").in(filter.getNomePropriedades()));
            }

            if(Objects.nonNull(filter.getNomeLaboratorios()) && !filter.getNomeLaboratorios().isEmpty()) {
                predicate = builder.and(predicate, root.get("laboratorio").get("nome").in(filter.getNomeLaboratorios()));
            }

            query.orderBy(builder.desc(root.get("id")));
            return predicate;
        };
    }
}
