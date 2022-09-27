package br.com.clinicawszd.clinicavet.controller;

import br.com.clinicawszd.clinicavet.model.Pet;
import br.com.clinicawszd.clinicavet.service.PetService;
import br.com.clinicawszd.clinicavet.service.TutorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/pet")
public class PetController {

    @Autowired
    private PetService service;
    @Autowired
    private TutorService tutorService;

    @ApiOperation(value = "Adiciona um pet")
    @PostMapping
    public ResponseEntity<Pet> createNew(@Valid @RequestBody Pet novo){
        tutorService.getOneTutor(novo.getTutor().getId());
        Pet res = service.createNewPt(novo);
        if(res != null){
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.status(203).build();
    }
    @ApiOperation(value = "Retorna todos os pets")
    @GetMapping
    public ArrayList<Pet> getAllPets(){
        return service.getAllPt();
    }

    @ApiOperation(value = "Retorna um pet")
    @GetMapping("/{id}")
    public ResponseEntity<Pet> getOnePet(@PathVariable Long id){
        Pet res = service.getOnePt(id);
        if(res != null){
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Altera um pet")
    @PutMapping
    public ResponseEntity<Pet> updatePet(@Valid @RequestBody  Pet novo){
        Pet res = service.updatePt(novo);
        if(res != null){
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Excluí um pet")
    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable Long id){
        service.deletePtById(id);
    }
}
