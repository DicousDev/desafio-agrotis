package br.com.agrotis.desafio.dto.filter;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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
