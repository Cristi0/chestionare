package com.management.chestionare.service;

import com.management.chestionare.Repository.RepoIntrebare;
import com.management.chestionare.domain.Chestionar;
import com.management.chestionare.domain.Intrebare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceIntrebare {
    private final RepoIntrebare repoIntrebare;

    @Autowired
    public ServiceIntrebare(RepoIntrebare repoIntrebare) {
        this.repoIntrebare = repoIntrebare;
    }

    @Transactional(readOnly = true)
    public List<Intrebare> findAll() {
        return repoIntrebare.findAll();
    }

    @Transactional(readOnly = true)
    public List<Intrebare> findAllByChestionar_ChestionarId(Long chestionarId) {
        return repoIntrebare.findAllByChestionar_ChestionarId(chestionarId);
    }

    @Transactional(readOnly = true)
    public List<Intrebare> findAllByChestionar_UtilizatorCreator_NumeDeUtilizator(String numeDeUtilizator) {
        return repoIntrebare.findAllByChestionar_UtilizatorCreator_NumeDeUtilizator(numeDeUtilizator);
    }

    @Transactional(readOnly = true)
    public Optional<Intrebare> findById(Long id) {
        return repoIntrebare.findById(id);
    }

    @Transactional
    public Intrebare save(Chestionar chestionar, Intrebare intrebare) {
        chestionar.setNumarDeIntrebari(chestionar.getNumarDeIntrebari() + 1);
        return repoIntrebare.saveAndFlush(intrebare);
    }

    @Transactional
    public void delete(Chestionar chestionar, Intrebare intrebare) {
        chestionar.setNumarDeIntrebari(chestionar.getNumarDeIntrebari() - 1);
        repoIntrebare.delete(intrebare);
    }
}
