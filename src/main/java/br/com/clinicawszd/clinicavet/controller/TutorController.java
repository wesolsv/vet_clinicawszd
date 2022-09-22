package br.com.clinicawszd.clinicavet.controller;

import br.com.clinicawszd.clinicavet.model.Tutor;
import br.com.clinicawszd.clinicavet.service.TutorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/tutor")
public class TutorController {

    @Autowired
    private TutorService service;

    @ApiOperation(value = "Retorna uma lista de tutores")
    @GetMapping
    public List<Tutor> getAllTutor(){
        return service.getAllTt();
    }

    @ApiOperation(value = "Retorna um tutor")
    @GetMapping("/{id}")
    public ResponseEntity<Tutor> getTutor(@PathVariable Long id){
        Tutor res = service.getOneTutor(id);
        if(res != null){
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.notFound().build();
    }
    @ApiOperation(value = "Adiciona um tutor")
    @PostMapping
    public ResponseEntity<Tutor> createNewTutor(@Valid @RequestBody Tutor novo){
        Tutor res = service.createNewTt(novo);
        if(res != null){
           return ResponseEntity.ok(res);
        }
        return ResponseEntity.status(203).build();
    }

    @ApiOperation(value = "Altera um tutor")
    @PutMapping
    public ResponseEntity<Tutor> updateTutor(@Valid @RequestBody Tutor novo){
        Tutor res = service.updateTt(novo);
        if(res != null){
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.badRequest().build();
    }

    @ApiOperation(value = "Exclui um tutor")
    @DeleteMapping("/{id}")
    public void deleteTutor(@PathVariable Long id){
        service.deleteTutorById(id);
    }
}
