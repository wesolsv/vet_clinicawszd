package br.com.clinicawszd.clinicavet.service;

import br.com.clinicawszd.clinicavet.exceptions.BadRequestException;
import br.com.clinicawszd.clinicavet.exceptions.ObjectNotFoundException;
import br.com.clinicawszd.clinicavet.model.Pet;
import br.com.clinicawszd.clinicavet.model.Tutor;
import br.com.clinicawszd.clinicavet.repository.PetRepository;
import br.com.clinicawszd.clinicavet.repository.TutorRepository;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TutorService {

    @Autowired
    private TutorRepository repository;

    @Autowired
    private PetRepository petRepository;

    public Tutor createNewTt(Tutor novo) {
        log.info("Adicionando Novo tutor!");

        Tutor tutor = new Tutor.Builder()
                .nome(novo.getNome())
                .telefone(novo.getTelefone().replaceAll("\\D", ""))
                .cpf(novo.getCpf().replaceAll("\\D", ""))
                .email(novo.getEmail())
                .endereco(novo.getEndereco()).build();

        try{
            repository.save(tutor);
        }catch (BadRequestException e){
            throw new BadRequestException("Falha ao criar novo Tutor!");
        }
        return tutor;
    }
    public ArrayList<Tutor> getAllTt() {
        log.info("Pegando todos os tutores!");
        return (ArrayList<Tutor>) repository.findAll();
    }

    public Tutor getOneTutor(Long id) {
        log.info("Pegando um tutor!");
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Objeto não encontrado id = " + id + " Tipo " + Tutor.class.getName()));
    }

    public Tutor updateTt(Long id, Tutor novo) {
        getOneTutor(id);
        novo.setId(id);
        log.info("Alterando tutor id = " + id);
        return repository.save(novo);
    }

    public void deleteTutorById(Long id){
        log.info("Removendo um tutor!");
        getOneTutor(id);

        List<Pet> pets = (List<Pet>) petRepository.findAll();

        for(Pet p : pets){
            if(p.getTutor().getId() == id){
                throw new BadRequestException("Não é possível excluir um tutor que tem um pet");
            }
        }

        repository.deleteById(id);
    }
}
