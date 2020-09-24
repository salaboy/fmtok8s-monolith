package com.salaboy.conferences.site;

import com.salaboy.conferences.site.model.Proposal;
import com.salaboy.conferences.site.model.ProposalStatus;
import com.salaboy.conferences.site.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProposalService {

    @Autowired
    private ProposalRepository repository;

    public Set<Proposal> getProposals(boolean pending) {
        if (!pending) {

            return new HashSet<>(repository.findAll());

        } else {

            return new HashSet<>(repository.findByStatus(ProposalStatus.PENDING));
        }
    }
}
