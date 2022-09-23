package br.com.clinicawszd.clinicavet.service;

import br.com.clinicawszd.clinicavet.exceptions.BadRequestException;
import br.com.clinicawszd.clinicavet.exceptions.ObjectNotFoundException;
import br.com.clinicawszd.clinicavet.model.Tutor;
import br.com.clinicawszd.clinicavet.repository.TutorRepository;
import br.com.clinicawszd.clinicavet.util.FormatNumber;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TutorService {

    @Autowired
    private TutorRepository repository;

    public Tutor createNewTt(Tutor novo) {
        log.info("Adicionando Novo tutor!");
        FormatNumber f = new FormatNumber();
        Tutor tutor = novo;
        tutor.setTelefone(f.formatarNumero("telefone", novo.getTelefone()));
        tutor.setCpf(f.formatarNumero("cpf", novo.getCpf()));

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
