package com.example.flashcards.repository;

import com.example.flashcards.model.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {
    List<Flashcard> findByQuestionContainingIgnoreCase(String keyword);
    List<Flashcard> findBySubject(String subject);
    List<Flashcard> findAllByOrderByIdAsc();
    Flashcard findBySerialNo(Long serialNo);
    
    // (Optional) if you want subject-based search too
    List<Flashcard> findBySubjectIgnoreCase(String subject);


}
