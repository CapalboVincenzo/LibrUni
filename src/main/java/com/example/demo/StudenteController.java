package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/studenti")
public class StudenteController {
    private final StudenteService studenteService;

    @Autowired
    public StudenteController(StudenteService studenteService) {
        this.studenteService = studenteService;
    }

    @PostMapping
    public ResponseEntity<Studente> creaStudente(@RequestBody Studente studenteDTO) {
        Studente studente = studenteService.createStudente(studenteDTO.getNome(), studenteDTO.getCognome());
        return ResponseEntity.status(HttpStatus.CREATED).body(studente);
    }

    @GetMapping
    public ResponseEntity<List<Studente>> getAllStudenti() {
        List<Studente> studenti = studenteService.getAllStudente();
        return ResponseEntity.ok(studenti);
    }
}