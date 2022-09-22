package br.com.clinicawszd.clinicavet.repository;

import br.com.clinicawszd.clinicavet.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
