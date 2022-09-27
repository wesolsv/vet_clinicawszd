package br.com.clinicawszd.clinicavet.service;

import br.com.clinicawszd.clinicavet.exceptions.ObjectNotFoundException;
import br.com.clinicawszd.clinicavet.model.Pet;
import br.com.clinicawszd.clinicavet.model.Tutor;
import br.com.clinicawszd.clinicavet.repository.PetRepository;
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
        return  repository.save(novo);
    }

    public ArrayList<Pet> getAllPt() {
        log.info("Pega todos os pets!");
        return (ArrayList<Pet>) repository.findAll();
    }

    public Pet getOnePt(Long id){
        log.info("Pegar um único pet!");
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Objeto não encontrado id = " + id + " Tipo " + Pet.class.getName()));
    }

    public Pet updatePt(Pet novo) {
        getOnePt(novo.getId());
        log.info("Atualizando um pet!");
        return repository.save(novo);
    }

    public void deletePtById(Long id){
        getOnePt(id);
        log.info("Deletando um Pet");
        repository.deleteById(id);
    };
}
