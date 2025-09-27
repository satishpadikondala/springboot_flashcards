package com.example.flashcards.controller;

import com.example.flashcards.model.Flashcard;
import com.example.flashcards.service.FlashcardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flashcards")
@CrossOrigin("*") // Allow React frontend
public class FlashcardController {

    private final FlashcardService service;

    public FlashcardController(FlashcardService service) {
        this.service = service;
    }

    // ✅ Create flashcard
    @PostMapping
    public Flashcard addFlashcard(@RequestBody Flashcard flashcard) {
        return service.addFlashcard(flashcard);
    }

    // ✅ Get all flashcards
    @GetMapping
    public List<Flashcard> getAllFlashcards() {
        return service.getAllFlashcards();
    }

    // ✅ Get flashcard by serialNo
    @GetMapping("/{serialNo}")
    public Flashcard getFlashcardBySerialNo(@PathVariable Long serialNo) {
        return service.getFlashcardBySerialNo(serialNo);
    }

    // ✅ Update flashcard by serialNo
    @PutMapping("/{serialNo}")
    public Flashcard updateFlashcard(@PathVariable Long serialNo, @RequestBody Flashcard flashcard) {
        return service.updateFlashcard(serialNo, flashcard);
    }

    // ✅ Delete flashcard by serialNo
    @DeleteMapping("/{serialNo}")
    public String deleteFlashcard(@PathVariable Long serialNo) {
        service.deleteFlashcard(serialNo);
        return "Flashcard deleted successfully";
    }

 // ✅ Search flashcards by keyword in the URL path
    @GetMapping("/search/{keyword}")
    public List<Flashcard> searchFlashcards(@PathVariable String keyword) {
        return service.searchFlashcards(keyword);
    }


    // ✅ Get flashcards by subject
    @GetMapping("/subject/{subject}")
    public List<Flashcard> getFlashcardsBySubject(@PathVariable String subject) {
        return service.getFlashcardsBySubject(subject);
    }
}
