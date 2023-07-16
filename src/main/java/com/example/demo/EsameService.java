package com.example.demo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EsameService {
    private final EsameRepository esameRepository;
    private final LibrettoService librettoService;
    private final StudenteService studenteService;

    @Autowired
    public EsameService(EsameRepository esameRepository, LibrettoService librettoService, StudenteService studenteService) {
        this.esameRepository = esameRepository;
        this.librettoService = librettoService;
        this.studenteService = studenteService;
    }

    public Esame createEsame(Integer librettoId, String materia, int voto) {
        if (librettoService.verificaLibrettoEsistente(librettoId)) {
            Libretto libretto = librettoService.getLibrettoById(librettoId);
            Studente studente = libretto.getStudente();
            if (studente != null && studenteService.verificaStudenteEsistente(studente.getId())) {
                Esame esame = new Esame();
                esame.setMateria(materia);
                esame.setVoto(voto);
                esame.setLibretto(libretto);
                return esameRepository.save(esame);
            } else {
                throw new IllegalArgumentException("Il libretto non è collegato a uno studente esistente.");
            }
        } else {
            throw new IllegalArgumentException("Il libretto non esiste.");
        }
    }
    
    public List<Esame> getAllEsami() {
        return esameRepository.findAll();
    }

    public Esame getEsameById(Integer esameId) {
        return esameRepository.findById(esameId)
                .orElseThrow(() -> new IllegalArgumentException("Esame non trovato con ID: " + esameId));
    }
}
    
