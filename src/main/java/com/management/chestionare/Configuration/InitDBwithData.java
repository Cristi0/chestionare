package com.management.chestionare.Configuration;

import com.management.chestionare.Repository.*;
import com.management.chestionare.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@SuppressWarnings("SpellCheckingInspection")
@Component
public class InitDBwithData {
    @Autowired
    private RepoChestionar repoChestionar;
    @Autowired
    private RepoChestionarEfectuat chestionarEfectuat;
    @Autowired
    private RepoIntrebare repoIntrebare;
    @Autowired
    private RepoIntrebareEfectuata intrebareEfectuata;
    @Autowired
    private RepoUtilizator repoUtilizator;
    @Autowired
    private RepoVariantaDeRaspuns repoVariantaDeRaspuns;

    @PostConstruct
    @Transactional
    public void init() {
        addDataIfNotExist(repoUtilizator, this::utilizatori);
        addDataIfNotExist(repoChestionar, this::chestionare);
        addDataIfNotExist(chestionarEfectuat, this::chestionareEfectuate);
        addDataIfNotExist(repoIntrebare, this::intrebari);
        addDataIfNotExist(intrebareEfectuata, this::intrebariEfectuate);
        addDataIfNotExist(repoVariantaDeRaspuns, this::varianteRaspuns);
    }

    private void addDataIfNotExist(JpaRepository repo, Runnable function) {
        if (!(repo.count() > 0)) {
            function.run();
        }
    }

    private void utilizatori() {
        List<Utilizator> utilizatori = new ArrayList<>(5);
        utilizatori.add(new Utilizator(0L, "Admin", Rol.ADMINISTRATOR, "admin", "admin"));
        utilizatori.add(new Utilizator(0L, "Profesor", Rol.ADMINISTRATOR, "prof", "prof"));
        utilizatori.add(new Utilizator(0L, "Popa Cristi", Rol.UTILIZATOR_OBISNUIT, "cristi", "cristi"));
        utilizatori.add(new Utilizator(0L, "Adrian Trinc", Rol.UTILIZATOR_OBISNUIT, "adrian", "adrian"));
        utilizatori.add(new Utilizator(0L, "User", Rol.UTILIZATOR_OBISNUIT, "user", "user"));
        repoUtilizator.saveAll(utilizatori);
        repoUtilizator.flush();
    }

    private void chestionare() {
        List<Utilizator> utilizatori = new ArrayList<>(5);
        Utilizator utilizatorImplicit = new Utilizator(0L, "numePrenume0", Rol.ADMINISTRATOR, "numeDeUtilizator0", "parola0");
        utilizatori.add(repoUtilizator.findById((long) 1).orElse(utilizatorImplicit));
        List<Chestionar> chestionare = new ArrayList<>(5);
        chestionare.add(new Chestionar(0L, "Chestionar1", 3, utilizatori.get(0)));
        chestionare.add(new Chestionar(0L, "Chestionar2", 5, utilizatori.get(0)));
        chestionare.add(new Chestionar(0L, "Chestionar3", 3, utilizatori.get(0)));
        chestionare.add(new Chestionar(0L, "Chestionar4", 5, utilizatori.get(0)));
        chestionare.add(new Chestionar(0L, "Chestionar5", 3, utilizatori.get(0)));
        repoChestionar.saveAll(chestionare);
        repoChestionar.flush();
    }

    private void chestionareEfectuate() {
        List<ChestionarEfectuat> chestionareEfectuate = new ArrayList<>(5);
        //todo: add data
        chestionarEfectuat.saveAll(chestionareEfectuate);
        chestionarEfectuat.flush();
    }

    private void intrebari() {
        List<Chestionar> chestionare = new ArrayList<>(5);
        Utilizator utilizatorImplicit = new Utilizator(0L, "numePrenume0", Rol.ADMINISTRATOR, "numeDeUtilizator0", "parola0");
        Chestionar chestionarImplicit = new Chestionar(0L, "descriere0", 0, utilizatorImplicit);
        chestionare.add(repoChestionar.findById((long) 1).orElse(chestionarImplicit));
        chestionare.add(repoChestionar.findById((long) 2).orElse(chestionarImplicit));
        chestionare.add(repoChestionar.findById((long) 3).orElse(chestionarImplicit));
        chestionare.add(repoChestionar.findById((long) 4).orElse(chestionarImplicit));
        chestionare.add(repoChestionar.findById((long) 5).orElse(chestionarImplicit));

        List<Intrebare> intrebari = new ArrayList<>(11);

        intrebari.add(new Intrebare(0L, "Prima intrebare din chestionarul 1", 1, chestionare.get(0), new HashSet<>()));
        intrebari.add(new Intrebare(0L, "A doua intrebare din chestionarul 1", 2, chestionare.get(0), new HashSet<>()));
        intrebari.add(new Intrebare(0L, "A treia intrebare din chestionarul 1", 3, chestionare.get(0), new HashSet<>()));

        intrebari.add(new Intrebare(0L, "Prima intrebare din chestionarul 2", 1, chestionare.get(1), new HashSet<>()));
        intrebari.add(new Intrebare(0L, "A doua intrebare din chestionarul 2", 2, chestionare.get(1), new HashSet<>()));
        intrebari.add(new Intrebare(0L, "A treia intrebare din chestionarul 2", 3, chestionare.get(1), new HashSet<>()));
        intrebari.add(new Intrebare(0L, "A patra intrebare din chestionarul 2", 4, chestionare.get(1), new HashSet<>()));
        intrebari.add(new Intrebare(0L, "A cincea intrebare din chestionarul 2", 5, chestionare.get(1), new HashSet<>()));

        intrebari.add(new Intrebare(0L, "Prima intrebare din chestionarul 3", 1, chestionare.get(2), new HashSet<>()));
        intrebari.add(new Intrebare(0L, "A doua intrebare din chestionarul 3", 2, chestionare.get(2), new HashSet<>()));
        intrebari.add(new Intrebare(0L, "A treia intrebare din chestionarul 3", 3, chestionare.get(2), new HashSet<>()));

        intrebari.add(new Intrebare(0L, "Prima intrebare din chestionarul 4", 1, chestionare.get(3), new HashSet<>()));
        intrebari.add(new Intrebare(0L, "A doua intrebare din chestionarul 4", 2, chestionare.get(3), new HashSet<>()));
        intrebari.add(new Intrebare(0L, "A treia intrebare din chestionarul 4", 3, chestionare.get(3), new HashSet<>()));
        intrebari.add(new Intrebare(0L, "A patra intrebare din chestionarul 4", 4, chestionare.get(3), new HashSet<>()));
        intrebari.add(new Intrebare(0L, "A cincea intrebare din chestionarul 4", 5, chestionare.get(3), new HashSet<>()));

        intrebari.add(new Intrebare(0L, "Prima intrebare din chestionarul 5", 1, chestionare.get(4), new HashSet<>()));
        intrebari.add(new Intrebare(0L, "A doua intrebare din chestionarul 5", 2, chestionare.get(4), new HashSet<>()));
        intrebari.add(new Intrebare(0L, "A treia intrebare din chestionarul 5", 3, chestionare.get(4), new HashSet<>()));

        repoIntrebare.saveAll(intrebari);
        repoIntrebare.flush();
    }

    private void intrebariEfectuate() {
        List<IntrebareEfectuata> intrebariEfectuate = new ArrayList<>(5);
        //todo: add data
        intrebareEfectuata.saveAll(intrebariEfectuate);
        intrebareEfectuata.flush();
    }

    private void varianteRaspuns() {
        List<Intrebare> intrebari = new ArrayList<>(5);

        Utilizator utilizatorImplicit = new Utilizator(0L, "numePrenume0", Rol.ADMINISTRATOR, "numeDeUtilizator0", "parola0");
        Chestionar chestionarImplicit = new Chestionar(0L, "descriere0", 0, utilizatorImplicit);
        Intrebare intrebareImplicita = new Intrebare(0L, "continut0", 0, chestionarImplicit, new HashSet<>());

        intrebari.add(repoIntrebare.findById((long) 1).orElse(intrebareImplicita));
        intrebari.add(repoIntrebare.findById((long) 2).orElse(intrebareImplicita));
        intrebari.add(repoIntrebare.findById((long) 3).orElse(intrebareImplicita));

        intrebari.add(repoIntrebare.findById((long) 4).orElse(intrebareImplicita));
        intrebari.add(repoIntrebare.findById((long) 5).orElse(intrebareImplicita));
        intrebari.add(repoIntrebare.findById((long) 6).orElse(intrebareImplicita));
        intrebari.add(repoIntrebare.findById((long) 7).orElse(intrebareImplicita));
        intrebari.add(repoIntrebare.findById((long) 8).orElse(intrebareImplicita));

        intrebari.add(repoIntrebare.findById((long) 9).orElse(intrebareImplicita));
        intrebari.add(repoIntrebare.findById((long) 10).orElse(intrebareImplicita));
        intrebari.add(repoIntrebare.findById((long) 11).orElse(intrebareImplicita));

        intrebari.add(repoIntrebare.findById((long) 12).orElse(intrebareImplicita));
        intrebari.add(repoIntrebare.findById((long) 13).orElse(intrebareImplicita));
        intrebari.add(repoIntrebare.findById((long) 14).orElse(intrebareImplicita));
        intrebari.add(repoIntrebare.findById((long) 15).orElse(intrebareImplicita));
        intrebari.add(repoIntrebare.findById((long) 16).orElse(intrebareImplicita));

        intrebari.add(repoIntrebare.findById((long) 17).orElse(intrebareImplicita));
        intrebari.add(repoIntrebare.findById((long) 18).orElse(intrebareImplicita));
        intrebari.add(repoIntrebare.findById((long) 19).orElse(intrebareImplicita));

        varianteRaspunsCreare(intrebari);
    }

    private void varianteRaspunsCreare(List<Intrebare> intrebari) {
        List<VariantaDeRaspuns> varianteDeRaspuns = new ArrayList<>(3);

        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta corecta 1", true, intrebari.get(0)));
        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta gresita 1", false, intrebari.get(0)));
        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta gresita 2", false, intrebari.get(0)));

        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta gresita 1", false, intrebari.get(1)));
        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta gresita 2", false, intrebari.get(1)));
        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta gresita 3", false, intrebari.get(1)));
        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta corecta 1", true, intrebari.get(1)));
        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta gresita 4", false, intrebari.get(1)));

        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta corecta 1", true, intrebari.get(2)));
        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta gresita 1", false, intrebari.get(2)));

        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta gresita 1", false, intrebari.get(3)));
        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta corecta 1", true, intrebari.get(3)));
        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta gresita 2", false, intrebari.get(3)));

        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta gresita 1", false, intrebari.get(4)));
        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta gresita 2", false, intrebari.get(4)));
        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta corecta 1", true, intrebari.get(4)));

        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta corecta 1", true, intrebari.get(5)));
        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta gresita 1", false, intrebari.get(5)));
        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta gresita 2", false, intrebari.get(5)));

        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta gresita 1", false, intrebari.get(6)));
        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta corecta 1", true, intrebari.get(6)));
        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta gresita 2", false, intrebari.get(6)));

        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta gresita 1", false, intrebari.get(7)));
        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta gresita 2", false, intrebari.get(7)));
        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta corecta 1", true, intrebari.get(7)));

        adaugaVarianteDeRaspunsOrdine1(intrebari, varianteDeRaspuns, 8);
        adaugaVarianteDeRaspunsOrdine2(intrebari, varianteDeRaspuns, 9);
        adaugaVarianteDeRaspunsOrdine3(intrebari, varianteDeRaspuns, 10);
        adaugaVarianteDeRaspunsOrdine1(intrebari, varianteDeRaspuns, 11);
        adaugaVarianteDeRaspunsOrdine2(intrebari, varianteDeRaspuns, 12);
        adaugaVarianteDeRaspunsOrdine3(intrebari, varianteDeRaspuns, 13);
        adaugaVarianteDeRaspunsOrdine1(intrebari, varianteDeRaspuns, 14);
        adaugaVarianteDeRaspunsOrdine2(intrebari, varianteDeRaspuns, 15);
        adaugaVarianteDeRaspunsOrdine3(intrebari, varianteDeRaspuns, 16);
        adaugaVarianteDeRaspunsOrdine1(intrebari, varianteDeRaspuns, 17);
        adaugaVarianteDeRaspunsOrdine2(intrebari, varianteDeRaspuns, 18);

        repoVariantaDeRaspuns.saveAll(varianteDeRaspuns);
        repoVariantaDeRaspuns.flush();
    }

    private void adaugaVarianteDeRaspunsOrdine1(List<Intrebare> intrebari, List<VariantaDeRaspuns> varianteDeRaspuns, int index) {
        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta corecta 1", true, intrebari.get(index)));
        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta gresita 1", false, intrebari.get(index)));
        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta gresita 2", false, intrebari.get(index)));
    }

    private void adaugaVarianteDeRaspunsOrdine2(List<Intrebare> intrebari, List<VariantaDeRaspuns> varianteDeRaspuns, int index) {
        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta gresita 1", false, intrebari.get(index)));
        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta corecta 1", true, intrebari.get(index)));
        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta gresita 2", false, intrebari.get(index)));
    }

    private void adaugaVarianteDeRaspunsOrdine3(List<Intrebare> intrebari, List<VariantaDeRaspuns> varianteDeRaspuns, int index) {
        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta gresita 1", false, intrebari.get(index)));
        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta gresita 2", false, intrebari.get(index)));
        varianteDeRaspuns.add(new VariantaDeRaspuns(0L, "Varianta corecta 1", true, intrebari.get(index)));
    }
}
