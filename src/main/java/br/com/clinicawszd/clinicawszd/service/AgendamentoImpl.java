package br.com.clinicawszd.clinicawszd.service;

import br.com.clinicawszd.clinicawszd.model.Agendamento;
import br.com.clinicawszd.clinicawszd.repository.AgendamentoRepository;
import br.com.clinicawszd.clinicawszd.service.interfaces.IAgendamento;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Slf4j
@Service
public class AgendamentoImpl implements IAgendamento {

    @Autowired
    private AgendamentoRepository repository;

    @Override
    public Agendamento createNewAg(Agendamento novo) {
        log.info("Criando novo Agendamento");
        return repository.save(novo);
    }

    @Override
    public ArrayList<Agendamento> getAllAg() {
        log.info("Lendo todos os Agendamentos");
        return (ArrayList<Agendamento>) repository.findAll();
    }

    @Override
    public Agendamento getOneAg(Long id) {
        log.info("Pegando um Agendamento");
        return repository.findById(id).orElse(null);
    }

    @Override
    public Agendamento updateAg(Agendamento novo) {
        log.info("Atualizado o Agendamento");
        return repository.save(novo);
    }

    @Override
    public void deleteAg(Long id) {
        repository.deleteById(id);
    }
}
