package com.example.flashcards.service;

import com.example.flashcards.model.Flashcard;
import com.example.flashcards.repository.FlashcardRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class FlashcardService {

    private final FlashcardRepository repository;

    public FlashcardService(FlashcardRepository repository) {
        this.repository = repository;
    }

    // ✅ Add a new flashcard
    public Flashcard addFlashcard(Flashcard flashcard) {
        if (flashcard.getQuestion() == null || flashcard.getAnswer() == null || flashcard.getSubject() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Question, Answer, and Subject are required");
        }

        repository.save(flashcard);  // save first to generate DB id
        renumberSerials();           // recalculate serialNos
        return flashcard;
    }

    // ✅ Get all flashcards (always with correct serialNo)
    public List<Flashcard> getAllFlashcards() {
        renumberSerials();
        return repository.findAllByOrderByIdAsc();
    }

    // ✅ Get flashcard by serialNo (instead of DB id)
    public Flashcard getFlashcardBySerialNo(Long serialNo) {
        Flashcard card = repository.findBySerialNo(serialNo);
        if (card == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Flashcard not found with serialNo " + serialNo);
        }
        return card;
    }

    // ✅ Update flashcard by serialNo
    public Flashcard updateFlashcard(Long serialNo, Flashcard flashcard) {
        Flashcard existing = repository.findBySerialNo(serialNo);
        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Flashcard not found with serialNo " + serialNo);
        }

        existing.setQuestion(flashcard.getQuestion());
        existing.setAnswer(flashcard.getAnswer());
        existing.setSubject(flashcard.getSubject());

        return repository.save(existing);
    }

    // ✅ Delete by serialNo and renumber
    public void deleteFlashcard(Long serialNo) {
        Flashcard card = repository.findBySerialNo(serialNo);
        if (card == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Flashcard not found with serialNo " + serialNo);
        }

        repository.delete(card);
        renumberSerials();
    }

    // ✅ Search by keyword
    public List<Flashcard> searchFlashcards(String keyword) {
        return repository.findByQuestionContainingIgnoreCase(keyword);
    }

    // ✅ Filter by subject
    public List<Flashcard> getFlashcardsBySubject(String subject) {
        return repository.findBySubject(subject);
    }

    // 🔑 Helper: Renumber all flashcards sequentially (1..n)
    private void renumberSerials() {
        List<Flashcard> flashcards = repository.findAllByOrderByIdAsc();
        for (int i = 0; i < flashcards.size(); i++) {
            flashcards.get(i).setSerialNo((long) (i + 1));
        }
        repository.saveAll(flashcards);
    }
}
