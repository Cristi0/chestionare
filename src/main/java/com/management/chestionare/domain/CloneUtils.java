package com.management.chestionare.domain;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class CloneUtils {
    public static VariantaDeRaspuns clone(VariantaDeRaspuns altaVariantaDeRaspuns, Intrebare intrebare) {
        return new VariantaDeRaspuns(
                0L,
                altaVariantaDeRaspuns.getContinut(),
                altaVariantaDeRaspuns.getVariantaCorecta(),
                intrebare
        );
    }

    public static Intrebare clone(Intrebare altaIntrebare, Chestionar chestionarClonat) {
        Intrebare intrebare = new Intrebare(
                0L,
                altaIntrebare.getContinut(),
                altaIntrebare.getNumarDePuncte(),
                chestionarClonat,
                new HashSet<>()
        );
        altaIntrebare
                .getVarianteDeRaspuns()
                .forEach(variantaDeRaspuns -> intrebare
                        .getVarianteDeRaspuns()
                        .add(CloneUtils.clone(variantaDeRaspuns, intrebare))
                );
        return intrebare;
    }

    public static List<Intrebare> clone(List<Intrebare> intrebari, Chestionar chestionarClonat) {
        return intrebari
                .stream()
                .map(intrebareIter -> CloneUtils.clone(intrebareIter, chestionarClonat))
                .collect(Collectors.toList());
    }

    public static Chestionar clone(Chestionar altChestionar) {
        return new Chestionar(
                0L,
                altChestionar.getDescriere(),
                altChestionar.getNumarDeIntrebari(),
                altChestionar.getUtilizatorCreator()
        );
    }
}
