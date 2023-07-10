package pl.dariusz.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import pl.dariusz.rest.controller.Controller;
import pl.dariusz.rest.entity.UserDto;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class RestApplicationTests {

	private Controller controller;
	private RestTemplate restTemplate;

	@BeforeEach
	public void setup() {
		controller = new Controller();
		restTemplate = mock(RestTemplate.class);
		ReflectionTestUtils.setField(controller, "restTemplate", restTemplate);
	}

	@Test
	public void testGetUsers() {
		List<UserDto> users = Arrays.asList(
				new UserDto("Dariusz", "Letkiewicz", "male", 23),
				new UserDto("Jan", "Nowak", "male", 40),
				new UserDto("Kasia", "Kowalska", "female", 27)
		);

		ResponseEntity<List<UserDto>> responseEntity = new ResponseEntity<>(users, HttpStatus.OK);

		when(restTemplate.exchange(
				"http://db/db/v1/users",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<UserDto>>() {}
		)).thenReturn(responseEntity);

		List<UserDto> result = controller.getUsers();

		assertEquals(users, result);
	}

}
