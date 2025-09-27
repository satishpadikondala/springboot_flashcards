package com.example.flashcards.model;

import jakarta.persistence.*;

@Entity
public class Flashcard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") 
    private Long id;   // Database PK (may skip numbers if deleted)

    // Additional serial number column
    @Column(name = "serial_no")
    private Long serialNo;  // Continuous sequence

    @Column(name = "question", nullable = false, length = 500)
    private String question;

    @Lob
    @Column(name = "answer", nullable = false)
    private String answer;

    @Column(name = "subject", nullable = false, length = 100)
    private String subject;

    // Constructors
    public Flashcard() {}

    public Flashcard(String question, String answer, String subject) {
        this.question = question;
        this.answer = answer;
        this.subject = subject;
    }

    // Getters and Setters
    public Long getId() { return id; }
    
    
    public Long getSerialNo() { return serialNo; }
    public void setSerialNo(Long serialNo) {  this.serialNo=serialNo;}

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    @Override
    public String toString() {
        return "Flashcard [id=" + id + ", serialNo=" + serialNo +
               ", question=" + question + ", answer=" + answer +
               ", subject=" + subject + "]";
    }
}
