package br.com.clinicawszd.clinicawszd.controller;

import br.com.clinicawszd.clinicawszd.model.Tutor;
import br.com.clinicawszd.clinicawszd.service.TutorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/tutor")
public class TutorController {

    @Autowired
    private TutorImpl service;

    @GetMapping
    public List<Tutor> getAllTutor(){
        return service.getAllTt();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tutor> getTutor(@PathVariable Long id){
        Tutor res = service.getOneTutor(id);
        if(res != null){
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Tutor> createNewTutor(@Valid @RequestBody Tutor novo){
        Tutor res = service.createNewTt(novo);
        if(res != null){
           return ResponseEntity.ok(res);
        }
        return ResponseEntity.status(203).build();
    }

    @PutMapping
    public ResponseEntity<Tutor> updateTutor(@Valid @RequestBody Tutor novo){
        Tutor res = service.updateTt(novo);
        if(res != null){
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public void deleteTutor(@PathVariable Long id){
        service.deleteTt(id);
    }
}
