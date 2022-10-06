package br.com.clinicawszd.clinicavet;

import br.com.clinicawszd.clinicavet.model.Pet;
import br.com.clinicawszd.clinicavet.model.Tutor;
import br.com.clinicawszd.clinicavet.service.TutorService;
import br.com.clinicawszd.clinicavet.util.Porte;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClinicawszdApplicationTests {

	@LocalServerPort
	private Integer port;

	@Autowired
	private TestRestTemplate restTemplate;

	private String host = "http://localhost:";

	private static Tutor tutorTest = new Tutor.Builder()
			.nome("Test Tutor")
			.telefone("(64)938897625".replaceAll("\\D", ""))
			.cpf("666.055.499-44".replaceAll("\\D", ""))
			.email("testmail@bfbr.com")
			.endereco("rua 1 vazio").build();

	private static Pet petTest = new Pet.Builder()
			.nome("Bananinha")
			.idade(3)
			.porte(Porte.G)
			.tipo("Aguia")
			.tutor(null)
			.build();


	//Tutor Testes 1 ao 5
	@Test
	void shouldCreateNewTutor(){
		ResponseEntity<Tutor> tutor = createEntity(Tutor.class, "tutor", tutorTest);

		assertEquals(tutor.getStatusCode(), HttpStatus.CREATED);
		assertNotNull(tutor.getBody().getId());
		assertEquals(tutor.getBody().getCpf(), "66605549944");
		assertEquals(tutor.getBody().getTelefone(), "64938897625");

		deleteEntity("tutor", tutor.getBody().getId());
	}
	@Test
	void shouldEditTutor(){

		ResponseEntity<Tutor> createdTutor = createEntity(Tutor.class, "tutor", tutorTest);

		createdTutor.getBody().setCpf("12345678911");
		createdTutor.getBody().setTelefone("34985613298");

		ResponseEntity<Tutor> tutor = editarEntidade(Tutor.class, "tutor", createdTutor.getBody(), createdTutor.getBody().getId());

		assertEquals(tutor.getStatusCode(), HttpStatus.CREATED);
		assertEquals(tutor.getBody().getCpf(), "12345678911");
		assertEquals(tutor.getBody().getTelefone(), "34985613298");
		deleteEntity("tutor", tutor.getBody().getId());
	}
	@Test
	void shouldReturnTutor(){

		ResponseEntity<Tutor> createdTutor = createEntity(Tutor.class, "tutor", tutorTest);
		ResponseEntity<Tutor> tutor = obterEntidade(Tutor.class, "tutor","/" + createdTutor.getBody().getId());

		assertEquals(tutor.getStatusCode(), HttpStatus.OK);
		assertEquals(tutor.getBody().getId(), createdTutor.getBody().getId());
		deleteEntity("tutor", tutor.getBody().getId());
	}
	@Test
	void shouldReturnAllTutor(){
		List<Tutor> tutor = obterEntidade(List.class, "tutor","").getBody();
		assertNotNull(tutor);
	}

	@Test
	void shouldDeleteTutor(){
		ResponseEntity<Tutor> createdTutor = createEntity(Tutor.class, "tutor", tutorTest);
		deleteEntity("tutor", createdTutor.getBody().getId());
		ResponseEntity<Tutor> tutor = obterEntidade(Tutor.class, "tutor", "/"+ createdTutor.getBody().getId());
		assertEquals(tutor.getStatusCode(), HttpStatus.NOT_FOUND);
	}

	//Pet Testes 5 ao 8
	@Test
	void shouldCreateNewPet(){
		petTest.setTutor(createEntity(Tutor.class, "tutor", tutorTest).getBody());
		ResponseEntity<Pet> pet = createEntity(Pet.class, "pet", petTest);

		pet.getBody().setIdade(10);
		pet.getBody().setNome("BANANA");

		ResponseEntity<Pet> petAlterado = editarEntidade(Pet.class, "pet", pet.getBody(), pet.getBody().getId());

		assertEquals(pet.getStatusCode(), HttpStatus.CREATED);
		assertEquals(pet.getBody().getIdade(), 10);
		assertEquals(pet.getBody().getNome(), "BANANA");

		deleteEntity("pet", pet.getBody().getId());
		deleteEntity("tutor", pet.getBody().getTutor().getId());
	}

	@Test
	void shouldCreateEditPet(){
		petTest.setTutor(createEntity(Tutor.class, "tutor", tutorTest).getBody());
		ResponseEntity<Pet> pet = createEntity(Pet.class, "pet", petTest);

		assertEquals(pet.getStatusCode(), HttpStatus.CREATED);
		assertNotNull(pet.getBody().getId());
		assertEquals(pet.getBody().getIdade(), 3);

		deleteEntity("pet", pet.getBody().getId());
		deleteEntity("tutor", pet.getBody().getTutor().getId());
	}

	@Test
	void shouldReturnPet(){

		petTest.setTutor(createEntity(Tutor.class, "tutor", tutorTest).getBody());
		ResponseEntity<Pet> createdPet = createEntity(Pet.class, "pet", petTest);
		ResponseEntity<Pet> petRetornado = obterEntidade(Pet.class, "pet","/" + createdPet.getBody().getId());

		assertEquals(petRetornado.getStatusCode(), HttpStatus.OK);
		assertEquals(petRetornado.getBody().getId(), createdPet.getBody().getId());

		deleteEntity("pet", createdPet.getBody().getId());
		deleteEntity("tutor", createdPet.getBody().getTutor().getId());
	}
	@Test
	void shouldReturnAllPets(){
		petTest.setTutor(createEntity(Tutor.class, "tutor", tutorTest).getBody());
		ResponseEntity<Pet> createdPet = createEntity(Pet.class, "pet", petTest);
		ResponseEntity<Pet> petRetornado = obterEntidade(Pet.class, "pet","/" + createdPet.getBody().getId());
		assertNotNull(petRetornado);

		deleteEntity("pet", createdPet.getBody().getId());
		deleteEntity("tutor", createdPet.getBody().getTutor().getId());
	}

	@Test
	void shouldDeletePet(){
		petTest.setTutor(createEntity(Tutor.class, "tutor", tutorTest).getBody());
		ResponseEntity<Pet> createdPet = createEntity(Pet.class, "pet", petTest);

		deleteEntity("pet", createdPet.getBody().getId());
		deleteEntity("tutor", createdPet.getBody().getTutor().getId());

		ResponseEntity<Pet> pet = obterEntidade(Pet.class, "pet", "/"+ createdPet.getBody().getId());
		assertEquals(pet.getStatusCode(), HttpStatus.NOT_FOUND);
	}


	private <T> ResponseEntity<T> obterEntidade(Class<T> classe, String endPoint, String parametro) {
		return restTemplate.getForEntity(host + port + "/api/v1/"+endPoint + parametro, classe);
	}

	private <T, U> ResponseEntity<T> createEntity(Class<T> classe, String endPoint, U request) {
		return restTemplate.postForEntity(host + port + "/api/v1/" + endPoint, request, classe);
	}
	private <T,U> ResponseEntity<T> editarEntidade(Class<T> classe, String endPoint, U request, Long id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return restTemplate.exchange(host + port + "/api/v1/"+ endPoint +"/{id}",
				HttpMethod.PUT, new HttpEntity<U>(request, headers), classe, id);
	}

	private void deleteEntity(String endPoint, Long id) {
		restTemplate.delete(host + port + "/api/v1/" + endPoint + "/" + id);
	}
}
