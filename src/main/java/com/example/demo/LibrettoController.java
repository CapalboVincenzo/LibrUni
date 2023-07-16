package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/libretti")
public class LibrettoController {
    private final LibrettoService librettoService;

    @Autowired
    public LibrettoController(LibrettoService librettoService) {
        this.librettoService = librettoService;
    }

    @PostMapping("{studenteId}")
    public ResponseEntity<Libretto> creaLibretto(@PathVariable Integer studenteId) {
        Libretto libretto = librettoService.createLibretto(studenteId);
        return ResponseEntity.status(HttpStatus.CREATED).body(libretto);
    }

    @GetMapping
    public ResponseEntity<List<Libretto>> getAllLibretti() {
        List<Libretto> libretti = librettoService.getAllLibretti();
        return ResponseEntity.ok(libretti);
    }
}