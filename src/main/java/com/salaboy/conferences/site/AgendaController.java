package com.salaboy.conferences.site;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salaboy.conferences.site.model.AgendaItem;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/agenda/")
@Slf4j
public class AgendaController {

    private ObjectMapper objectMapper = new ObjectMapper();
    private Set<AgendaItem> agendaItems = new TreeSet<>(Comparator.comparing(AgendaItem::getTime).thenComparing(AgendaItem::getId));

    @PostMapping()
    public String newAgendaItem(@RequestBody AgendaItem agendaItem) {
        log.info("> New Agenda Item Received: " + agendaItem);
        if(agendaItem.getTitle().contains("fail")){
            throw new IllegalStateException("Something went wrong with adding the Agenda Item: " + agendaItem);
        }
        boolean added = agendaItems.add(agendaItem);
        if(added) {
            log.info("> Agenda Item Added to Agenda: " + agendaItem);
            return "Agenda Item Added to Agenda";
        }else{
            log.info("> Agenda Item NOT added to Agenda: "+ agendaItem);
            return "Agenda Item Not added";
        }
    }

    @GetMapping()
    public Set<AgendaItem> getAll() {
        return agendaItems;
    }

    @GetMapping("/day/{day}")
    public Set<AgendaItem> getAllByDay(@PathVariable(value = "day", required = true) String day) {
        return agendaItems.stream().filter(a -> a.getDay().equals(day)).collect(Collectors.toSet());
    }

    @GetMapping("/{id}")
    public Optional<AgendaItem> getById(@PathVariable("id") String id) {
        return agendaItems.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @DeleteMapping("/")
    public void clearAgendaItems(){
        agendaItems.clear();
    }
}
