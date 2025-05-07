package br.com.agrotis.desafio.model;

import br.com.agrotis.desafio.validator.GenericValidator;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.HashCodeExclude;

import java.util.List;
import java.util.Set;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@EqualsAndHashCode
@Entity
@Table(name = "laboratorio")
public class Laboratorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @OneToMany(mappedBy = "laboratorio")
    private List<Pessoa> pessoas;

    public Laboratorio updateNome(String nome) {
        return toBuilder().nome(nome).build();
    }

    public static class LaboratorioBuilder {

        public Laboratorio build() {
            GenericValidator.validateNotBlank("Nome do laboratório é obrigatório.", nome);
            GenericValidator.validateMaxLength(nome, 255, "O nome do laboratório excedeu o limite de caracteres.");
            return new Laboratorio(id, nome, pessoas);
        }
    }
}
