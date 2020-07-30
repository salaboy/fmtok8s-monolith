package com.salaboy.conferences.site;

import com.salaboy.conferences.site.model.Proposal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/email/")
@Slf4j
public class EmailController {
    @PostMapping("/")
    public void sendEmail(@RequestBody Map<String, String> email) {
        String toEmail = email.get("toEmail");
        String emailTitle = email.get("title");
        String emailContent = email.get("content");
        printEmail(toEmail, emailTitle, emailContent);
    }


    @PostMapping("/notification")
    public void sendEmailNotification(@RequestBody Proposal proposal) {
        String emailBody = "Dear " + proposal.getAuthor() + ", \n";
        String emailTitle = "Conference Committee Communication";
        emailBody += "\t\t We are";
        if (proposal.isApproved()) {
            emailBody += " happy ";
        } else {
            emailBody += " sorry ";
        }
        emailBody += "to inform you that: \n";
        emailBody += "\t\t\t `" + proposal.getTitle() + "` -> `" + proposal.getDescription() + "`, \n";
        emailBody += "\t\t was";
        if (proposal.isApproved()) {
            emailBody += " approved ";
        } else {
            emailBody += " rejected ";
        }
        emailBody += "for this conference.";
        printEmail(proposal.getEmail(),emailTitle, emailBody );
    }

    private void printEmail(String to, String title, String body){
        log.info("+-------------------------------------------------------------------+");
        log.info("\t Email Sent to: " + to);
        log.info("\t Email Title: " + title);
        log.info("\t Email Body: " + body);
        log.info("+-------------------------------------------------------------------+\n\n");
    }
}
