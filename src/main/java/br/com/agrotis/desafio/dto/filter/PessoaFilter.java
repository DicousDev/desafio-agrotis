package br.com.agrotis.desafio.dto.filter;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PessoaFilter {

    private Integer page = 0;
    private Integer size = 30;
    private String nome;
    private LocalDateTime dataInicialMinima;
    private LocalDateTime dataInicialMaxima;
    private LocalDateTime dataFinalMinima;
    private LocalDateTime dataFinalMaxima;
    private String observacoes;
    private List<String> nomePropriedades;
    private List<String> nomeLaboratorios;
}
