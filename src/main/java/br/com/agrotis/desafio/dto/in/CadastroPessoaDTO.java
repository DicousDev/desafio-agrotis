package br.com.agrotis.desafio.dto.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CadastroPessoaDTO {

    @NotBlank
    @Size(max = 255)
    private String nome;
    @NotNull
    private LocalDateTime dataInicial;
    @NotNull
    private LocalDateTime dataFinal;
    @Size(max = 255)
    private String observacoes;
    @NotNull
    private Long propriedadeId;
    @NotNull
    private Long laboratorioId;
}
