package br.com.clinicawszd.clinicavet.controller;

import br.com.clinicawszd.clinicavet.model.Tutor;
import br.com.clinicawszd.clinicavet.service.TutorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
        return ResponseEntity.ok(service.getOneTutor(id));
    }

    @ApiOperation(value = "Adiciona um tutor")
    @PostMapping
    public ResponseEntity<Tutor> createNewTutor(@RequestBody Tutor novo){
        return new ResponseEntity<Tutor>(service.createNewTt(novo), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Altera um tutor")
    @PutMapping("/{id}")
    public ResponseEntity<Tutor> updateTutor(@PathVariable Long id, @RequestBody Tutor novo){
        return new ResponseEntity<Tutor>(service.updateTt(id, novo), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Exclui um tutor")
    @DeleteMapping("/{id}")
    public void deleteTutor(@PathVariable Long id){
        service.deleteTutorById(id);
    }
}
