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

    // Constructor Injection (Your preferred style)
    public PetitionController(PetitionService petitionService) {
        this.petitionService = petitionService;
    }

    // 1. Page to view all petitions
    @GetMapping
    public String getAll(Model model) {
        List<Petition> petitions = petitionService.findAll();
        model.addAttribute("petitions", petitions);
        return "view-all"; // returns view-all.html
    }

    // 2. Page to create a petition
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("petition", new Petition(null, "", "", ""));
        return "create-petition"; // returns create-petition.html
    }

    @PostMapping("/save")
    public String create(@ModelAttribute Petition petition) {
        petitionService.save(petition);
        return "redirect:/petitions"; // Go back to list after saving
    }

    // 3. Page to view specific petition and "sign" it
    @GetMapping("/{id}")
    public String getById(@PathVariable Long id, Model model) {
        Optional<Petition> result = petitionService.findById(id);
        if (result.isPresent()) {
            model.addAttribute("petition", result.get());
            return "view-details"; // returns view-details.html
        }
        return "redirect:/petitions"; // Redirect if not found
    }

    // 4. Page to search + Results page
    @GetMapping("/search")
    public String search(@RequestParam(required = false) String title, Model model) {
        if (title != null && !title.isEmpty()) {
            List<Petition> results = petitionService.searchByTitle(title);
            model.addAttribute("results", results);
            model.addAttribute("searchQuery", title);
            return "search-results"; // Page with the results
        }
        return "search-form"; // Page to perform the search
    }
}
