package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LibrettoService {
    private final LibrettoRepository librettoRepository;
    private final StudenteService studenteService;

    @Autowired
    public LibrettoService(LibrettoRepository librettoRepository, StudenteService studenteService) {
        this.librettoRepository = librettoRepository;
        this.studenteService = studenteService;
    }

    public Libretto createLibretto(	Integer studenteId) {
        if (studenteService.verificaStudenteEsistente(studenteId)) {
            if (!librettoRepository.existsByStudenteId(studenteId)) {
                Libretto libretto = new Libretto();
          Studente studente = studenteService.getStudenteById(studenteId);
                libretto.setStudente(studente);
                return librettoRepository.save(libretto);
            } else {
                throw new IllegalArgumentException("Lo studente ha già un libretto.");
            }
        } else {
            throw new IllegalArgumentException("Lo studente non esiste.");
        }
    }

    public boolean verificaLibrettoEsistente(Integer librettoId) {
        return librettoRepository.existsById(librettoId);
    }

    public Libretto getLibrettoById(Integer librettoId) {
        return librettoRepository.findById(librettoId)
                .orElseThrow(() -> new IllegalArgumentException("Libretto non trovato con ID: " + librettoId));
 }
    
    public List<Libretto>getAllLibretti(){
    	return librettoRepository.findAll();
    }
}