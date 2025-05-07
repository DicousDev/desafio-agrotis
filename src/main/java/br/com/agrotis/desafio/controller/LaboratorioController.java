package br.com.agrotis.desafio.controller;

import br.com.agrotis.desafio.controlleradvice.ApiResponseError;
import br.com.agrotis.desafio.dto.in.AtualizarLaboratorioDTO;
import br.com.agrotis.desafio.dto.in.CadastroLaboratorioDTO;
import br.com.agrotis.desafio.dto.out.LaboratorioComPessoasDTO;
import br.com.agrotis.desafio.dto.out.LaboratorioDTO;
import br.com.agrotis.desafio.service.LaboratorioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/laboratorios")
@RequiredArgsConstructor
public class LaboratorioController {

    private final LaboratorioService service;

    @Operation(summary = "Pesquisa laboratórios por página.",
            description = "Pesquisa laboratórios por página.",
            responses = {
                    @ApiResponse(
                            description = "Laboratórios pesquisados com sucesso.",
                            responseCode = "200",
                            useReturnTypeSchema = true,
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = LaboratorioComPessoasDTO.class),
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
    public Page<LaboratorioDTO> pesquisar(@RequestParam(value = "nome", required = false) String nome,
                                          @RequestParam(value = "page", defaultValue = "0") int page,
                                          @RequestParam(value = "size", defaultValue = "30") int size) {
        return service.pesquisarPorPagina(nome, page, size);
    }

    @Operation(summary = "Pesquisa laboratório pelo ID.",
            description = "Pesquisa detalhes do laboratório pelo ID.",
            responses = {
                    @ApiResponse(
                            description = "Laboratório pesquisado no sistema com sucesso.",
                            responseCode = "201",
                            useReturnTypeSchema = true,
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = LaboratorioComPessoasDTO.class),
                                            mediaType = MediaType.APPLICATION_JSON_VALUE
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "Dados enviados inválidos.",
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
                            description = "Laboratório não encontrado.",
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
    @GetMapping("{id}")
    public LaboratorioComPessoasDTO pesquisarPeloId(@PathVariable("id") Long idLaboratorio) {
        return service.pesquisarPeloIdFetchPessoas(idLaboratorio);
    }

    @Operation(summary = "Cadastra um laboratório.",
            description = "Cadastra uma laboratório por vez.",
            responses = {
                    @ApiResponse(
                            description = "Laboratório cadastrado no sistema com sucesso.",
                            responseCode = "201",
                            useReturnTypeSchema = true,
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = LaboratorioDTO.class),
                                            mediaType = MediaType.APPLICATION_JSON_VALUE
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "Dados enviados inválidos.",
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
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LaboratorioDTO cadastrar(@RequestBody @Valid CadastroLaboratorioDTO laboratorio) {
        return service.cadastrar(laboratorio);
    }

    @Operation(summary = "Atualiza campos de um laboratório.",
            description = "Atualiza campos de um laboratório.",
            responses = {
                    @ApiResponse(
                            description = "Laboratório atualizado no sistema com sucesso.",
                            responseCode = "201",
                            useReturnTypeSchema = true,
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = LaboratorioDTO.class),
                                            mediaType = MediaType.APPLICATION_JSON_VALUE
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "Dados enviados inválidos.",
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
                            description = "Laboratório não encontrado.",
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
    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public LaboratorioDTO atualizar(@PathVariable("id") Long id, @RequestBody @Valid AtualizarLaboratorioDTO laboratorio) {
        return service.atualizar(id, laboratorio);
    }
}
