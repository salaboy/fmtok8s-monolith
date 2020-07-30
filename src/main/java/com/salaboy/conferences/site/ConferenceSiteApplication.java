package com.salaboy.conferences.site;

import com.salaboy.conferences.site.model.AgendaItem;
import com.salaboy.conferences.site.model.Proposal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class ConferenceSiteApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConferenceSiteApplication.class, args);
    }
}

@Controller
class ConferenceSiteController {
    @Value("${version:0.0.0}")
    private String version;

    @Autowired
    private AgendaController agendaService;

    @Autowired C4PController c4pService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("version", version);
        model.addAttribute("agendaItemsMonday", agendaService.getAllByDay("Monday"));
        model.addAttribute("agendaItemsTuesday", agendaService.getAllByDay("Tuesday"));

        return "index";
    }

    @GetMapping("/backoffice")
    public String backoffice(@RequestParam(value = "pending", required = false, defaultValue = "false") boolean pending, Model model) {
        Set<Proposal> proposals = c4pService.getAll(pending);
        model.addAttribute("version", version);

        model.addAttribute("pending", (pending) ? "checked" : "");

        if (proposals != null) {
            model.addAttribute("proposals", proposals);
        }
        return "backoffice";
    }




}
