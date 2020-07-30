package com.salaboy.conferences.site;

import com.salaboy.conferences.site.model.AgendaItem;
import com.salaboy.conferences.site.model.Proposal;
import com.salaboy.conferences.site.model.ProposalDecision;
import com.salaboy.conferences.site.model.ProposalStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/c4p/")
public class C4PController {

    @Autowired
    private ProposalStorageService proposalStorageService;

    @Autowired
    private AgendaController agendaService;

    @Autowired
    private EmailController emailService;

    private final String[] DAYS = {"Monday", "Tuesday"};
    private final String[] TIMES = {"9:00 am", "10:00 am", "11:00 am", "1:00 pm", "2:00 pm", "3:00 pm", "4:00 pm", "5:00 pm"};


    @PostMapping()
    public Proposal newProposal(@RequestBody Proposal proposal) {
        proposalStorageService.add(proposal);
        emitEvent("> New Proposal Received Event ( " + proposal + ")");
        return proposal;
    }

    @DeleteMapping("/{id}")
    public void deleteProposal(@PathVariable("id") String id) {
        proposalStorageService.delete(id);
    }

    @GetMapping()
    public Set<Proposal> getAll(@RequestParam(value = "pending", defaultValue = "false", required = false) boolean pending) {
        return proposalStorageService.getProposals(pending);

    }

    @GetMapping("/{id}")
    public Optional<Proposal> getById(@PathVariable("id") String id) {
        return proposalStorageService.getProposalById(id);
    }

    @PostMapping(value = "/{id}/decision")
    public void decide(@PathVariable("id") String id, @RequestBody ProposalDecision decision) {
        emitEvent("> Proposal Approved Event ( " + ((decision.isApproved()) ? "Approved" : "Rejected") + ")");
        Optional<Proposal> proposalOptional = proposalStorageService.getProposalById(id);
        if (proposalOptional.isPresent()) {
            Proposal proposal = proposalOptional.get();

            // Apply Decision to Proposal
            proposal.setApproved(decision.isApproved());
            proposal.setStatus(ProposalStatus.DECIDED);
            proposalStorageService.add(proposal);

//          Only if it is Approved create a new Agenda Item into the Agenda Service
            if (decision.isApproved()) {
                agendaService.newAgendaItem(createNewAgendaItemFromProposal(proposal));
            }

            // Notify Potential Speaker By Email
            emailService.sendEmailNotification(proposal);
        } else {
            emitEvent(" Proposal Not Found Event (" + id + ")");
        }

    }

    private AgendaItem createNewAgendaItemFromProposal(Proposal proposal) {
        emitEvent("> Add Proposal To Agenda Event ");
        Random random = new Random();
        int day = random.nextInt(2);
        int time = random.nextInt(8);
        return new AgendaItem(proposal.getTitle(), proposal.getAuthor(), DAYS[day], TIMES[time]);
    }

    private void emitEvent(String content) {
        log.info(content);
    }
}
