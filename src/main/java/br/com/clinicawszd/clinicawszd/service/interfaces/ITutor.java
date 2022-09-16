package br.com.clinicawszd.clinicawszd.service.interfaces;

import br.com.clinicawszd.clinicawszd.model.Tutor;

import java.util.ArrayList;

public interface ITutor {

    //Create
    public Tutor createNewTt(Tutor novo);
    //Read
    public ArrayList<Tutor> getAllTt();
    //Update
    public Tutor updateTt(Tutor novo);
    //Delete
    public Tutor deleteTt(Long id);
}
