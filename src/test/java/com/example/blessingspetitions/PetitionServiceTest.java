package com.example.blessingspetitions;

import com.example.blessingspetitions.entity.Petition;
import com.example.blessingspetitions.service.PetitionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class PetitionServiceTest {

    private PetitionService petitionService;


    @BeforeEach
    void setUp() {
        petitionService = new PetitionService();
    }

    @Test
    void testFindAll() {
        assertEquals(2, petitionService.findAll().size());
    }

    @Test
    void testSavePetition() {
        Petition newPetition = new Petition(null, "Save the Bees", "Protect nature", "User1");

        petitionService.save(newPetition);

        List<Petition> all = petitionService.findAll();
        assertEquals(3, all.size(), "List size should be 3 after adding one");
        assertEquals("Save the Bees", all.get(2).getTitle());
    }

    @Test
    void testSearchByTitle() {
        // Search for "Trees" (One of our default petitions is "Plant More Trees")
        List<Petition> results = petitionService.searchByTitle("Trees");

        assertFalse(results.isEmpty(), "Should find at least one result");
        assertTrue(results.get(0).getTitle().contains("Trees"));
    }

    // 4. Test for "View Specific" Feature
    @Test
    void testFindById() {
        Optional<Petition> petition = petitionService.findById(1L);

        assertTrue(petition.isPresent(), "Petition with ID 1 should exist");
        assertEquals("Better Public Transport", petition.get().getTitle());
    }

}
