package com.example.blessingspetitions.service;

import com.example.blessingspetitions.entity.Petition;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetitionService {
    private final List<Petition> petitions = new ArrayList<>();

    public PetitionService() {
        petitions.add(new Petition(1L, "Better Public Transport", "We need more buses.", "Blessing"));
        petitions.add(new Petition(2L, "Plant More Trees", "Greenery for the city.", "Alex"));
    }

    public List<Petition> findAll() {
        return petitions;
    }

    public Petition save(Petition petition) {
        petition.setId((long) (petitions.size() + 1));
        petitions.add(petition);
        return petition;
    }

    //find by ID
    public Optional<Petition> findById(Long id) {
        for (Petition p : petitions) {
            if (p.getId().equals(id)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    //  search by Title
    public List<Petition> searchByTitle(String title) {
        List<Petition> results = new ArrayList<>();
        for (Petition p : petitions) {

            // Check if the title contains the search word (ignoring case)
            if (p.getTitle().toLowerCase().contains(title.toLowerCase())) {
                results.add(p);
            }
        }
        return results;
    }
}
