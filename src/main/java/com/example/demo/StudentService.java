package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudenteRepository studenteRepository;

    @Autowired
    public StudentService(StudenteRepository studenteRepository) {
        this.studenteRepository = studenteRepository;
    }

    public Student createStudente(String nome, String cognome) {
        Student studente = new Student();
        studente.setNome(nome);
        studente.setCognome(cognome);
        return studenteRepository.save(studente);
    }

    public boolean verificaStudenteEsistente(Integer studenteId) {
        return studenteRepository.existsById(studenteId);
    }

    public Student getStudenteById(Integer studenteId) {
        return studenteRepository.findById(studenteId)
                .orElseThrow(() -> new IllegalArgumentException("Studente non trovato con ID: " + studenteId));
    }
public List<Student>getAllStudente(){
	return studenteRepository.findAll();
			}
}