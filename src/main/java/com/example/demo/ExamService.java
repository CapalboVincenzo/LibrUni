package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamService {
    private final ExamRepository esameRepository;
    private final LibrettoService librettoService;
    private final StudentService studenteService;

    @Autowired
    public ExamService(ExamRepository esameRepository, LibrettoService librettoService, StudentService studenteService) {
        this.esameRepository = esameRepository;
        this.librettoService = librettoService;
        this.studenteService = studenteService;
    }

    public Exam createEsame(Integer librettoId, String materia, int voto) {
        if (librettoService.verificaLibrettoEsistente(librettoId)) {
            Libretto libretto = librettoService.getLibrettoById(librettoId);
            Student studente = libretto.getStudente();
            if (studente != null && studenteService.verificaStudenteEsistente(studente.getId())) {
                Exam esame = new Exam();
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
    
    public List<Exam> getAllEsami() {
        return esameRepository.findAll();
    }

    public Exam getEsameById(Integer esameId) {
        return esameRepository.findById(esameId)
                .orElseThrow(() -> new IllegalArgumentException("Esame non trovato con ID: " + esameId));
    }
}
    
