package com.salaboy.conferences.site.repository;

import com.salaboy.conferences.site.model.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, String> {}
