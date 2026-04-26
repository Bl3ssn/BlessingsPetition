package com.example.blessingspetitions.controller;

import com.example.blessingspetitions.entity.Petition;
import com.example.blessingspetitions.service.PetitionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/petitions")
public class PetitionController {

    private final PetitionService petitionService;

    public PetitionController(PetitionService petitionService) {
        this.petitionService = petitionService;
    }

    @GetMapping
    public String getAll(Model model) {
        List<Petition> petitions = petitionService.findAll();
        model.addAttribute("petitions", petitions);
        return "ViewPetition";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("petition", new Petition(null, "", "", ""));
        return "CreatePetition";
    }

    @PostMapping("/save")
    public String create(@ModelAttribute Petition petition) {
        petitionService.save(petition);
        return "redirect:/petitions";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Long id, Model model) {
        Optional<Petition> result = petitionService.findById(id);
        if (result.isPresent()) {
            model.addAttribute("petition", result.get());
            return "ViewPetition";
        }
        return "redirect:/petitions";
    }


    @GetMapping("/search")
    public String search(@RequestParam(required = false) String title, Model model) {
        if (title != null && !title.isEmpty()) {
            List<Petition> results = petitionService.searchByTitle(title);
            model.addAttribute("results", results);
            model.addAttribute("searchQuery", title);
            return "SearchResult";
        }
        return "SearchPetition";
    }
}
