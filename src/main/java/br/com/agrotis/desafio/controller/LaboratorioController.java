package br.com.agrotis.desafio.controller;

import br.com.agrotis.desafio.controlleradvice.ApiResponseError;
import br.com.agrotis.desafio.dto.in.CadastroLaboratorioDTO;
import br.com.agrotis.desafio.dto.out.LaboratorioDTO;
import br.com.agrotis.desafio.dto.out.PessoaDTO;
import br.com.agrotis.desafio.service.LaboratorioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/laboratorios")
@RequiredArgsConstructor
public class LaboratorioController {

    private final LaboratorioService service;

    @Operation(summary = "Cadastra um laboratório",
            description = "Cadastra uma laboratório por vez",
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
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LaboratorioDTO cadastrar(@RequestBody @Valid CadastroLaboratorioDTO laboratorio) {
        return service.cadastrar(laboratorio);
    }
}
