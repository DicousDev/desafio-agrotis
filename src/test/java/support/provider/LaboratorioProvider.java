package support.provider;

import br.com.agrotis.desafio.model.Laboratorio;

import java.util.Collections;

public class LaboratorioProvider {

    public static Laboratorio.LaboratorioBuilder padrao() {
        return Laboratorio.builder()
                .id(1L)
                .nome("Laboratório Dexter")
                .pessoas(Collections.emptySet());
    }
}
