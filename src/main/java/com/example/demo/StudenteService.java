package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudenteService {
    private final StudenteRepository studenteRepository;

    @Autowired
    public StudenteService(StudenteRepository studenteRepository) {
        this.studenteRepository = studenteRepository;
    }

    public Studente createStudente(String nome, String cognome) {
        Studente studente = new Studente();
        studente.setNome(nome);
        studente.setCognome(cognome);
        return studenteRepository.save(studente);
    }

    public boolean verificaStudenteEsistente(Integer studenteId) {
        return studenteRepository.existsById(studenteId);
    }

    public Studente getStudenteById(Integer studenteId) {
        return studenteRepository.findById(studenteId)
                .orElseThrow(() -> new IllegalArgumentException("Studente non trovato con ID: " + studenteId));
    }
public List<Studente>getAllStudente(){
	return studenteRepository.findAll();
			}
}