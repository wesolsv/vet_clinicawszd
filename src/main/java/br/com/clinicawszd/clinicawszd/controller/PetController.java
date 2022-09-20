package br.com.clinicawszd.clinicawszd.controller;

import br.com.clinicawszd.clinicawszd.model.Pet;
import br.com.clinicawszd.clinicawszd.service.PetImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/pet")
public class PetController {

    @Autowired
    private PetImpl service;

    @PostMapping
    public ResponseEntity<Pet> createNew(@Valid @RequestBody Pet novo){
        Pet res = service.createNewPt(novo);
        if(res != null){
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.status(203).build();
    }

    @GetMapping
    public ArrayList<Pet> getAllPets(){
        return service.getAllPt();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getOnePet(@PathVariable Long id){
        Pet res = service.getOnePt(id);
        if(res != null){
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<Pet> updatePet(@Valid @RequestBody  Pet novo){
        Pet res = service.updatePt(novo);
        if(res != null){
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable Long id){
        service.deletePt(id);
    }
}
