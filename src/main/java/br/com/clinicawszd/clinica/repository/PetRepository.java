package br.com.clinicawszd.clinica.repository;

import br.com.clinicawszd.clinica.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
