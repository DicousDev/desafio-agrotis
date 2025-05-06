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

import java.util.Set;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "propriedade")
public class Propriedade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @OneToMany(mappedBy = "propriedade")
    private Set<Pessoa> pessoas;

    public static class PropriedadeBuilder {

        public Propriedade build() {
            GenericValidator.validateNotBlank("Nome da propriedade é obrigatório.", nome);
            GenericValidator.validateMaxLength(nome, 255, "O nome da propriedade excedeu o limite de caracteres.");
            return new Propriedade(id, nome, pessoas);
        }
    }
}
