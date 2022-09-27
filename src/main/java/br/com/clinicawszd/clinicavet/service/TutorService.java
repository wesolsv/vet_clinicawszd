package br.com.clinicawszd.clinicavet.service;

import br.com.clinicawszd.clinicavet.exceptions.BadRequestException;
import br.com.clinicawszd.clinicavet.exceptions.ObjectNotFoundException;
import br.com.clinicawszd.clinicavet.model.Tutor;
import br.com.clinicawszd.clinicavet.repository.TutorRepository;
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

        novo.setTelefone(novo.getTelefone().replaceAll("\\D", ""));
        novo.setCpf(novo.getCpf().replaceAll("\\D", ""));


        Tutor tutor = new Tutor.Builder()
                .nome(novo.getNome())
                .telefone(novo.getTelefone())
                .cpf(novo.getCpf())
                .email(novo.getEmail())
                .endereco(novo.getEndereco()).build();

        System.out.println(tutor);

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

    public Tutor updateTt(Tutor novo) {
        getOneTutor(novo.getId());
        log.info("Alterando tutor id = " + novo.getId());
        return repository.save(novo);
    }

    public void deleteTutorById(Long id) {
        log.info("Removendo um tutor!");
        getOneTutor(id);
        repository.deleteById(id);
    }
}
