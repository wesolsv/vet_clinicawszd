package br.com.clinicawszd.clinicawszd.service;

import br.com.clinicawszd.clinicawszd.model.Pet;
import br.com.clinicawszd.clinicawszd.repository.PetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Slf4j
@Service
public class PetService {

    @Autowired
    private PetRepository repository;

    public Pet createNewPt(Pet novo) {
       log.info("Adiciona novo pet!");
        System.out.println(novo);
        return  repository.save(novo);
    }

    public ArrayList<Pet> getAllPt() {
        log.info("Pega todos os pets!");
        return (ArrayList<Pet>) repository.findAll();
    }

    public Pet getOnePt(Long id){
        log.info("Pegar um único pet!");
        return repository.findById(id).orElse(null);
    }

    public Pet updatePt(Pet novo) {
        log.info("Atualiza pet!");
        return repository.save(novo);
    }

    public void deletePtById(Long id){
        log.info("Deleta Pet");
        repository.deleteById(id);
    };
}
