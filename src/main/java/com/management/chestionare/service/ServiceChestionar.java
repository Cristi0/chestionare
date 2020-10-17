package com.management.chestionare.service;

import com.management.chestionare.Repository.RepoChestionar;
import com.management.chestionare.domain.Chestionar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SpellCheckingInspection")
@Service
public class ServiceChestionar {
    private final RepoChestionar repoChestionar;

    @Autowired
    public ServiceChestionar(RepoChestionar repoChestionar) {
        this.repoChestionar = repoChestionar;
    }

    @Transactional(readOnly = true)
    public List<Chestionar> findAll() {
        return repoChestionar.findAll();
    }

    @Transactional(readOnly = true)
    public List<Chestionar> findAllByUtilizatorCreator_NumeDeUtilizator(String numeDeUtilizator) {
        return repoChestionar.findAllByUtilizatorCreator_NumeDeUtilizator(numeDeUtilizator);
    }

    @Transactional(readOnly = true)
    public Optional<Chestionar> findById(Long id) {
        return repoChestionar.findById(id);
    }
}
