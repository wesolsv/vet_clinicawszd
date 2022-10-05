package br.com.clinicawszd.clinicavet.service;

import br.com.clinicawszd.clinicavet.exceptions.BadRequestException;
import br.com.clinicawszd.clinicavet.exceptions.ObjectNotFoundException;
import br.com.clinicawszd.clinicavet.model.Agendamento;
import br.com.clinicawszd.clinicavet.model.Pet;
import br.com.clinicawszd.clinicavet.repository.AgendamentoRepository;
import br.com.clinicawszd.clinicavet.repository.PetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PetService {

    @Autowired
    private PetRepository repository;
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public Pet createNewPt(Pet novo) {
       log.info("Adiciona novo pet!");

       try{
           List<Agendamento> lista = new ArrayList<>(novo.getAgendamentos());

           if(!lista.isEmpty()){
               novo.getAgendamentos().clear();
               Pet pet = repository.save(novo);
               for(Agendamento ag : lista){
                   ag.setPet(pet);
                   agendamentoRepository.save(ag);
               }
               pet.setAgendamentos(lista);
               return pet;
           }
       }
       catch (BadRequestException e){
           throw new BadRequestException("Erro ao adicionar pet, verifique o payload");
       }

       return  repository.save(novo);
    }

    public ArrayList<Pet> getAllPt() {
        log.info("Pega todos os pets!");
        return (ArrayList<Pet>) repository.findAll();
    }

    public Pet getOnePt(Long id){
        log.info("Pegando um único pet!");
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Pet não encontrado id = " + id + " Tipo " + Pet.class.getName()));
    }

    public Pet updatePt(Long id, Pet novo) {
        getOnePt(id);
        novo.setId(id);
        log.info("Atualizando um pet!");
        return repository.save(novo);
    }

    public void deletePtById(Long id){
        getOnePt(id);
        log.info("Deletando um Pet");
        repository.deleteById(id);
    };
}
