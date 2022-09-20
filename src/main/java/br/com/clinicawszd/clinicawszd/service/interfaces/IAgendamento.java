package br.com.clinicawszd.clinicawszd.service.interfaces;

import br.com.clinicawszd.clinicawszd.model.Agendamento;

import java.util.ArrayList;

public interface IAgendamento {

    //Create
    public Agendamento createNewAg(Agendamento novo);
    //Read
    public ArrayList<Agendamento> getAllAg();

    public Agendamento getOneAg(Long id);
    //Update
    public Agendamento updateAg(Agendamento novo);
    //Delete
    public void deleteAg(Long id);
}
