package com.salaboy.conferences.site.repository;

import com.salaboy.conferences.site.model.Proposal;
import com.salaboy.conferences.site.model.ProposalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, String> {

    List<Proposal> findByStatus(final ProposalStatus status);

}
