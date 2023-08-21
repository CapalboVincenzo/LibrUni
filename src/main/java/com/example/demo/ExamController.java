package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/esami")
public class ExamController {
    private final ExamService esameService;

    @Autowired
    public ExamController(ExamService esameService) {
        this.esameService = esameService;
    }

    @PostMapping("/libretti/{librettoId}")
    public ResponseEntity<Exam> creaEsame(@PathVariable Integer librettoId, @RequestBody Exam esame) {
        Exam nuovoEsame = esameService.createEsame(librettoId, esame.getMateria(), esame.getVoto());
        return ResponseEntity.status(HttpStatus.CREATED).body(nuovoEsame);
    }

    @GetMapping
    public ResponseEntity<List<Exam>> getAllEsami() {
        List<Exam> esami = esameService.getAllEsami();
        return ResponseEntity.ok(esami);
    }
}