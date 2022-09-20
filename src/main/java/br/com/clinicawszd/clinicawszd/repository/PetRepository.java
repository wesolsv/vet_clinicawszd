package br.com.clinicawszd.clinicawszd.repository;

import br.com.clinicawszd.clinicawszd.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
