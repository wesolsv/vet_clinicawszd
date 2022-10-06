package br.com.clinicawszd.clinicavet;

import br.com.clinicawszd.clinicavet.model.Tutor;
import br.com.clinicawszd.clinicavet.service.TutorService;
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
@ExtendWith(MockitoExtension.class)
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

	@Order(1)
	@Test
	public void shouldCreateNewTutor(){
		ResponseEntity<Tutor> tutor = createEntity(Tutor.class, "tutor", tutorTest);

		assertEquals(tutor.getStatusCode(), HttpStatus.CREATED);
		assertNotNull(tutor.getBody().getId());
		assertEquals(tutor.getBody().getCpf(), "66605549944");
		assertEquals(tutor.getBody().getTelefone(), "64938897625");
	}

	@Order(2)
	@Test
	public void shouldEditTutor(){

		tutorTest.setCpf("12345678911");
		tutorTest.setTelefone("34985613298");

		ResponseEntity<Tutor> createdTutor = createEntity(Tutor.class, "tutor", tutorTest);

		ResponseEntity<Tutor> tutor = editarEntidade(Tutor.class, "tutor", createdTutor.getBody(), createdTutor.getBody().getId());

		assertEquals(tutor.getStatusCode(), HttpStatus.CREATED);
		assertEquals(tutor.getBody().getCpf(), "12345678911");
		assertEquals(tutor.getBody().getTelefone(), "34985613298");
	}

	@Order(3)
	@Test
	public void shouldReturnTutor(){

		ResponseEntity<Tutor> createdTutor = createEntity(Tutor.class, "tutor", tutorTest);
		ResponseEntity<Tutor> tutor = obterEntidade(Tutor.class, "tutor","/" + createdTutor.getBody().getId());

		assertEquals(tutor.getStatusCode(), HttpStatus.OK);
		assertEquals(tutor.getBody().getId(), createdTutor.getBody().getId());
	}
	@Order(4)
	@Test
	public void shouldReturnAllTutor(){
		List<Tutor> tutor = obterEntidade(List.class, "tutor","").getBody();
		assertNotNull(tutor);
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

}
