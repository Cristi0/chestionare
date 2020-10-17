package com.management.chestionare.service;

import com.management.chestionare.Repository.RepoIntrebare;
import com.management.chestionare.domain.Chestionar;
import com.management.chestionare.domain.Intrebare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("SpellCheckingInspection")
@Service
public class ServiceIntrebare {
    private final RepoIntrebare repoIntrebare;

    @Autowired
    public ServiceIntrebare(RepoIntrebare repoIntrebare) {
        this.repoIntrebare = repoIntrebare;
    }

    @Transactional(readOnly = true)
    public List<Intrebare> findAllByChestionar_ChestionarId(Long chestionarId) {
        return repoIntrebare.findAllByChestionar_ChestionarId(chestionarId);
    }
}
