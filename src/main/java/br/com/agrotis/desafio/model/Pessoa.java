package br.com.agrotis.desafio.model;

import br.com.agrotis.desafio.validator.GenericValidator;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDateTime dataInicial;
    private LocalDateTime dataFinal;
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "propriedade_id")
    private Propriedade propriedade;
    @ManyToOne
    @JoinColumn(name = "laboratorio_id")
    private Laboratorio laboratorio;


    public static class PessoaBuilder {

        public Pessoa build() {
            GenericValidator.validateNotBlank("Nome da pessoa é obrigatório.", nome);
            GenericValidator.validateMaxLength(nome, 255, "O nome da pessoa excedeu o limite de caracteres.");
            GenericValidator.validateNotNull("É obrigatório anexar uma propriedade a pessoa.", propriedade);
            GenericValidator.validateNotNull("É obrigatório anexar um laboratório a pessoa.", laboratorio);
            GenericValidator.validateNotNull("Data inicial não pode ser nula.", dataInicial);
            GenericValidator.validateNotNull("Data final não pode ser nula.", dataFinal);
            GenericValidator.validation("Data inicial não pode ser igual ou superior a data final.", () -> dataInicial.equals(dataFinal) || dataInicial.isAfter(dataFinal));
            return new Pessoa(id, nome, dataInicial, dataFinal, observacoes, propriedade, laboratorio);
        }
    }
}
