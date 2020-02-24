package com.salaboy.conferences.site;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

@Controller
class ConferenceSiteController {
    @Value("${version:0.0.1}")
    private String version;

    @GetMapping("/")
    public String index(Model model) {
        String agendaString = "Agenda Section";
        String cp4PublicString = "C4P Public Section";
        model.addAttribute("version", version);
        model.addAttribute("agenda", agendaString);
        model.addAttribute("c4pPublic", cp4PublicString);
        return "index";
    }

    @GetMapping("/backoffice")
    public String backoffice(Model model) {
        String emailString = "Email Section";
        String cp4String = "C4P Section";
        model.addAttribute("version", version);
        model.addAttribute("c4p", cp4String);
        model.addAttribute("email", emailString);
        return "backoffice";
    }




}
