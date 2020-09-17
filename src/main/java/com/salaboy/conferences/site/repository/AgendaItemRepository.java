package com.salaboy.conferences.site.repository;

import com.salaboy.conferences.site.model.AgendaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendaItemRepository extends JpaRepository<AgendaItem, String> {
    List<AgendaItem> findAllByDay(String day);
}
