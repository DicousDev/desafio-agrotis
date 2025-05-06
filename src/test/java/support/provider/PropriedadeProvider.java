package support.provider;

import br.com.agrotis.desafio.model.Propriedade;

import java.util.Collections;

public class PropriedadeProvider {

    public static Propriedade.PropriedadeBuilder padrao() {
        return Propriedade.builder()
                .id(1L)
                .nome("Propriedade Dexter")
                .pessoas(Collections.emptySet());
    }
}
