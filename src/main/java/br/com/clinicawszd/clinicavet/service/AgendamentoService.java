package br.com.clinicawszd.clinicavet.service;

import br.com.clinicawszd.clinicavet.model.Agendamento;
import br.com.clinicawszd.clinicavet.repository.AgendamentoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Slf4j
@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;

    public Agendamento createNewAg(Agendamento novo) {
        log.info("Criando novo Agendamento");
        System.out.println(novo);
        return repository.save(novo);
    }

    public ArrayList<Agendamento> getAllAg() {
        log.info("Lendo todos os Agendamentos");
        return (ArrayList<Agendamento>) repository.findAll();
    }

    public Agendamento getOneAg(Long id) {
        log.info("Pegando um Agendamento");
        return repository.findById(id).orElse(null);
    }

    public Agendamento updateAg(Agendamento novo) {
        log.info("Atualizado o Agendamento");
        return repository.save(novo);
    }

    public void deleteAgById(Long id) {
        repository.deleteById(id);
    }
}
