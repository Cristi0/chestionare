package com.management.chestionare.Configuration;

import com.management.chestionare.Repository.*;
import com.management.chestionare.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitDBwithData {
    @Autowired
    private RepoChestionar chestionar;
    @Autowired
    private RepoChestionarEfectuat chestionarEfectuat;
    @Autowired
    private RepoIntrebare intrebare;
    @Autowired
    private RepoIntrebareEfectuata intrebareEfectuata;
    @Autowired
    private RepoUtilizator utilizator;
    @Autowired
    private RepoVariantaDeRaspuns variantaDeRaspuns;

    @PostConstruct
    public void init() {
        addDataIfNotExist(utilizator,this::utilizatori);
        addDataIfNotExist(chestionar,this::chestionare);
        addDataIfNotExist(chestionarEfectuat,this::chestionareEfectuate);
        addDataIfNotExist(intrebare,this::intrebari);
        addDataIfNotExist(intrebareEfectuata,this::intrebariEfectuate);
        addDataIfNotExist(variantaDeRaspuns,this::varianteRaspuns);
    }

    private void addDataIfNotExist(JpaRepository repo, Runnable function){
        if(!(repo.count()>0)) {
            function.run();
        }
    }
    private void utilizatori() {
        List<Utilizator> utilizatori = new ArrayList<>(5);
        utilizatori.add(new Utilizator(0L,"Admin", Rol.ADMINISTRATOR,"admin", "admin"));
        utilizatori.add(new Utilizator(0L,"Profesor", Rol.ADMINISTRATOR,"prof", "prof"));
        utilizatori.add(new Utilizator(0L,"Popa Cristi", Rol.UTILIZATOR_OBISNUIT,"cristi", "cristi"));
        utilizatori.add(new Utilizator(0L,"Adrian Trinc", Rol.UTILIZATOR_OBISNUIT,"adrian", "adrina"));
        utilizatori.add(new Utilizator(0L,"User", Rol.UTILIZATOR_OBISNUIT,"user", "user"));

        utilizator.saveAll(utilizatori);
        utilizator.flush();
    }

    private void chestionare(){
        List<Chestionar> chestionare = new ArrayList<>(5);
        //todo: add data
        chestionar.saveAll(chestionare);
        chestionar.flush();
    }
    private void chestionareEfectuate(){
        List<ChestionarEfectuat> chestionareEfectuate = new ArrayList<>(5);
        //todo: add data
        chestionarEfectuat.saveAll(chestionareEfectuate);
        chestionarEfectuat.flush();
    }
    private void intrebari(){
        List<Intrebare> intrebari = new ArrayList<>(5);
        //todo: add data
        intrebare.saveAll(intrebari);
        intrebare.flush();
    }
    private void intrebariEfectuate(){
        List<IntrebareEfectuata> intrebariEfectuate = new ArrayList<>(5);
        //todo: add data
        intrebareEfectuata.saveAll(intrebariEfectuate);
        intrebareEfectuata.flush();
    }
    private void varianteRaspuns(){
        List<VariantaDeRaspuns> variantaRaspuns = new ArrayList<>(5);
        //todo: add data
        variantaDeRaspuns.saveAll(variantaRaspuns);
        variantaDeRaspuns.flush();
    }
}
