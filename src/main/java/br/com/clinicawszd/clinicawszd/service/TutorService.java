package br.com.clinicawszd.clinicawszd.service;

import br.com.clinicawszd.clinicawszd.model.Tutor;
import br.com.clinicawszd.clinicawszd.repository.TutorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class TutorService {

    @Autowired
    private TutorRepository repository;

    public Tutor createNewTt(Tutor novo) {
        log.info("Adicionando Novo tutor!");
        return repository.save(novo);
    }
    public ArrayList<Tutor> getAllTt() {

        log.info("Pegando todos os tutores!");
        return (ArrayList<Tutor>) repository.findAll();
    }

    public Tutor getOneTutor(Long id) {
        log.info("Pegando um tutor!");

        return repository.findById(id).orElse(null);
    }

    public Tutor updateTt(Tutor novo) {
        log.info("Alterando tutor id = " + novo.getId());
        return repository.save(novo);
    }

    public void deleteTutorById(Long id) {
        log.info("Removendo um tutor!");
        repository.deleteById(id);
    }
}
