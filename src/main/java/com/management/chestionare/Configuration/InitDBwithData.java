package com.management.chestionare.Configuration;

import com.management.chestionare.Repository.*;
import com.management.chestionare.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

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
    private RepoVariantaDeRaspuns variantaDeRaspuns;

    @PostConstruct
    public void init() {
        addDataIfNotExist(repoUtilizator, this::utilizatori);
        addDataIfNotExist(repoChestionar, this::chestionare);
        addDataIfNotExist(chestionarEfectuat, this::chestionareEfectuate);
        addDataIfNotExist(repoIntrebare, this::intrebari);
        addDataIfNotExist(intrebareEfectuata, this::intrebariEfectuate);
        addDataIfNotExist(variantaDeRaspuns, this::varianteRaspuns);
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
        utilizatori.add(new Utilizator(0L, "Adrian Trinc", Rol.UTILIZATOR_OBISNUIT, "adrian", "adrina"));
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
        List<VariantaDeRaspuns> variantaRaspuns = new ArrayList<>(5);
        //todo: add data
        variantaDeRaspuns.saveAll(variantaRaspuns);
        variantaDeRaspuns.flush();
    }
}
