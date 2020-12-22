package com.management.chestionare.service;

import com.management.chestionare.Repository.RepoChestionarEfectuat;
import com.management.chestionare.domain.ChestionarEfectuat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceChestionarEfectuat {
    private final RepoChestionarEfectuat repoChestionarEfectuat;

    @Autowired
    public ServiceChestionarEfectuat(RepoChestionarEfectuat repoChestionarEfectuat) {
        this.repoChestionarEfectuat = repoChestionarEfectuat;
    }

    @Transactional(readOnly = true)
    public List<ChestionarEfectuat> findAllByUtilizator_NumeDeUtilizator(String numeDeUtilizator) {
        return repoChestionarEfectuat.findAllByUtilizator_NumeDeUtilizator(numeDeUtilizator);
    }
}
