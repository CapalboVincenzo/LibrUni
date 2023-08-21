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
public class StudentController {
    private final StudentService studenteService;

    @Autowired
    public StudentController(StudentService studenteService) {
        this.studenteService = studenteService;
    }

    @PostMapping
    public ResponseEntity<Student> creaStudente(@RequestBody Student studenteDTO) {
        Student studente = studenteService.createStudente(studenteDTO.getNome(), studenteDTO.getCognome());
        return ResponseEntity.status(HttpStatus.CREATED).body(studente);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudenti() {
        List<Student> studenti = studenteService.getAllStudente();
        return ResponseEntity.ok(studenti);
    }
}