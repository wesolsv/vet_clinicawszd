package br.com.clinicawszd.clinicawszd.service.interfaces;

import br.com.clinicawszd.clinicawszd.model.Pet;

import java.util.ArrayList;

public interface IPet {

    //Create
    public Pet createNewPt(Pet novo);
    //Read
    public ArrayList<Pet> getAllPt();

    public Pet getOnePt(Long id);

    //Update
    public Pet updatePt(Pet novo);
    //Delete
    public void deletePt(Long id);
}
