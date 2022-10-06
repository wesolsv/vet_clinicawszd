package br.com.clinicawszd.clinicavet.service;

import br.com.clinicawszd.clinicavet.dto.AgendamentoDTO;
import br.com.clinicawszd.clinicavet.exceptions.BadRequestException;
import br.com.clinicawszd.clinicavet.exceptions.ObjectNotFoundException;
import br.com.clinicawszd.clinicavet.model.Agendamento;
import br.com.clinicawszd.clinicavet.repository.AgendamentoRepository;
import br.com.clinicawszd.clinicavet.util.StatusAgendamento;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
@Slf4j
@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;

    public Agendamento createdNewAg(Agendamento novo) {
        log.info("Criando novo Agendamento");

        Agendamento ag = new Agendamento.Builder()
                .pet(novo.getPet())
                .dtCriacao(LocalDateTime.now())
                .dtAgendamento(novo.getDtAgendamento())
                .procedimento(novo.getProcedimento())
                .agStatus(StatusAgendamento.AGENDADO)
                .build();
        try{
           repository.save(ag);
        }catch (BadRequestException e){
            throw new BadRequestException("Falha ao criar Agendamento, verifique o payload");
        }
        return ag;
    }
    public ArrayList<Agendamento> getAllAg() {
        log.info("Lendo todos os Agendamentos");
        return (ArrayList<Agendamento>) repository.findAll();
    }
    public Agendamento getOneAg(Long id) {
        log.info("Pegando um Agendamento");
        return repository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Agendamento não encontrado id = " + id + " Tipo " + Agendamento.class.getName()));
    }
    public Agendamento updateAg(Long id, Agendamento novo) {
        getOneAg(id);
        novo.setId(id);
        log.info("Atualizado o Agendamento");
        return repository.save(novo);
    }

    public Agendamento updateStatus(String status, Long id) {
        Agendamento ag = getOneAg(id);
        ag.setAgStatus(StatusAgendamento.valueOf(status));
        return repository.save(ag);
    }

    public void deleteAgById(Long id) {
        getOneAg(id);
        log.info("Deletando o Agendamento");
        repository.deleteById(id);
    }

    public ArrayList<AgendamentoDTO> getAllAgByYearMonth(Integer ano, Integer mes){
        log.info("Pegando todos os Agendamentos por Ano e Mes");
        return repository.returnAgendYearMonth(ano, mes);
    }
}
