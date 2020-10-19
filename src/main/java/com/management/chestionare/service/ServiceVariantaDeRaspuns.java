package com.management.chestionare.service;

import com.management.chestionare.Repository.RepoVariantaDeRaspuns;
import com.management.chestionare.domain.VariantaDeRaspuns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceVariantaDeRaspuns {
    private final RepoVariantaDeRaspuns repoVariantaDeRaspuns;

    @Autowired
    public ServiceVariantaDeRaspuns(RepoVariantaDeRaspuns repoVariantaDeRaspuns) {
        this.repoVariantaDeRaspuns = repoVariantaDeRaspuns;
    }

    @Transactional
    public VariantaDeRaspuns save(VariantaDeRaspuns variantaDeRaspuns) {
        return repoVariantaDeRaspuns.saveAndFlush(variantaDeRaspuns);
    }
}
