package com.prospecta.controller;

	

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.prospecta.entity.Entry;
import com.prospecta.entity.EntryDTO;
import com.prospecta.entity.EntryResponse;
import com.prospecta.service.EntryService;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final String API_URL = "https://api.publicapis.org/entries";

    private final EntryService entryService;

    @Autowired
    public ApiController(EntryService entryService) {
        this.entryService = entryService;
    }

    @GetMapping("/listByCategory")
    public ResponseEntity<List<EntryDTO>> listByCategory(@RequestParam String category) {
        RestTemplate restTemplate = new RestTemplate();
        EntryResponse response = restTemplate.getForObject(API_URL, EntryResponse.class);

        if (response == null || response.getEntries() == null || response.getEntries().isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<EntryDTO> entriesInCategory = response.getEntries().stream()
                .filter(entry -> entry.getCategory().equalsIgnoreCase(category))
                .map(EntryDTO::new)
                .collect(Collectors.toList());

        if (entriesInCategory.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(entriesInCategory);
    }

    @PostMapping("/saveEntry")
    public ResponseEntity<String> saveEntry(@RequestBody Entry entry) {
        Entry savedEntry = entryService.save(entry);
        if (savedEntry != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Entry saved successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save entry");
        }
    }
}
