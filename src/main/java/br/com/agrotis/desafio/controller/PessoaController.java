package br.com.agrotis.desafio.controller;

import br.com.agrotis.desafio.controlleradvice.ApiResponseError;
import br.com.agrotis.desafio.dto.filter.PessoaFilter;
import br.com.agrotis.desafio.dto.in.CadastroPessoaDTO;
import br.com.agrotis.desafio.dto.out.PessoaDTO;
import br.com.agrotis.desafio.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/v1/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService service;

    @Operation(summary = "Consulta pessoas cadastrada no sistema.",
            description = "Consulta pessoas cadastrada no sistema incluído filtros.",
            responses = {
                    @ApiResponse(
                            description = "Consulta pessoas cadastrada no sistema realizada com sucesso.",
                            responseCode = "201",
                            useReturnTypeSchema = true,
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = PessoaDTO.class),
                                            mediaType = MediaType.APPLICATION_JSON_VALUE
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "Dados enviados inválidos",
                            responseCode = "400",
                            useReturnTypeSchema = true,
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = ApiResponseError.class),
                                            mediaType = MediaType.APPLICATION_JSON_VALUE
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "Erro inesperado do servidor.",
                            responseCode = "500",
                            useReturnTypeSchema = true,
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = ApiResponseError.class),
                                            mediaType = MediaType.APPLICATION_JSON_VALUE
                                    )
                            }
                    )
            }
    )
    @GetMapping
    public Page<PessoaDTO> pesquisarPessoas(@ParameterObject @Valid PessoaFilter filter) {
        return service.pesquisarPessoas(filter);
    }

    @Operation(summary = "Cadastra uma pessoa",
            description = "Cadastra uma pessoa por vez",
            responses = {
                    @ApiResponse(
                            description = "Pessoa cadastrada no sistema com sucesso.",
                            responseCode = "201",
                            useReturnTypeSchema = true,
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = PessoaDTO.class),
                                            mediaType = MediaType.APPLICATION_JSON_VALUE
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "Dados enviados inválidos",
                            responseCode = "400",
                            useReturnTypeSchema = true,
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = ApiResponseError.class),
                                            mediaType = MediaType.APPLICATION_JSON_VALUE
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "Propriedade ou laboratorio não encontrado para anexar a pessoa.",
                            responseCode = "404",
                            useReturnTypeSchema = true,
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = ApiResponseError.class),
                                            mediaType = MediaType.APPLICATION_JSON_VALUE
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "Erro inesperado do servidor.",
                            responseCode = "500",
                            useReturnTypeSchema = true,
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = ApiResponseError.class),
                                            mediaType = MediaType.APPLICATION_JSON_VALUE
                                    )
                            }
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PessoaDTO cadastrar(@RequestBody @Valid CadastroPessoaDTO pessoa) {
        return service.cadastrar(pessoa);
    }
}
