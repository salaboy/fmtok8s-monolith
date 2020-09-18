package com.salaboy.conferences.site;

import com.salaboy.conferences.site.model.AgendaItem;
import com.salaboy.conferences.site.repository.AgendaItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/agenda/")
@Slf4j
public class AgendaController {

    @Autowired
    private AgendaItemRepository agendaItemRepository;

    @PostMapping()
    public String newAgendaItem(@RequestBody AgendaItem agendaItem) {

        log.info("> New Agenda Item Received: " + agendaItem);

        if(agendaItem.getTitle().contains("fail")){
            throw new IllegalStateException("Something went wrong with adding the Agenda Item: " + agendaItem);
        }

        try {

            agendaItemRepository.save(agendaItem);
            log.info("> Agenda Item Added to Agenda: " + agendaItem);
            return "Agenda Item Added to Agenda";

        } catch (Exception exception) {
            log.info("> Agenda Item NOT added to Agenda: "+ agendaItem);
            return "Agenda Item Not added";
        }
    }

    @GetMapping()
    public List<AgendaItem> getAll() {

        return agendaItemRepository.findAll();
    }

    @GetMapping("/day/{day}")
    public Set<AgendaItem> getAllByDay(@PathVariable(value = "day", required = true) String day) {
        return agendaItemRepository.findAllByDay(day).stream().collect(Collectors.toSet());
    }

    @GetMapping("/{id}")
    public Optional<AgendaItem> getById(@PathVariable("id") String id) {
        return agendaItemRepository.findById(id);
    }

    @DeleteMapping("/")
    public void clearAgendaItems() {

        agendaItemRepository.deleteAll();

    }
}
