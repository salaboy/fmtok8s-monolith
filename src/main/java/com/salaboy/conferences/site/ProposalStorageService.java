package com.salaboy.conferences.site;

import com.salaboy.conferences.site.model.Proposal;
import com.salaboy.conferences.site.model.ProposalStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProposalStorageService {
    private Set<Proposal> proposals = new HashSet<>();

    public void add(Proposal proposal){
        proposals.add(proposal);
    }


    public void delete(String id) {
        proposals.stream().filter(p -> p.getId().equals(id)).findFirst().ifPresent(p -> proposals.remove(p));
    }

    public Set<Proposal> getProposals(boolean pending) {
        if (!pending) {
            return proposals;
        } else {
            return proposals.stream().filter(p -> p.getStatus().equals(ProposalStatus.PENDING)).collect(Collectors.toSet());
        }
    }

    public Optional<Proposal> getProposalById(String id) {
        return proposals.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
}
