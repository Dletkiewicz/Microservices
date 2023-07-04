package pl.dariusz.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.RestTemplate;
import pl.dariusz.rest.entity.UserDto;

import java.util.List;

@RestController
@RequestMapping("/rest/v1")
public class Controller {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        ResponseEntity<List<UserDto>> response = restTemplate.exchange(
                "http://db/db/v1/users",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserDto>>() {}
        );
        return response.getBody();
    }

    @GetMapping("/users/sort/age")
    public List<UserDto> getUsersSortedByAge() {
        ResponseEntity<List<UserDto>> response = restTemplate.exchange(
                "http://db/db/v1/users/sort/age",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserDto>>() {}
        );
        return response.getBody();
    }

    @GetMapping("/users/sort/firstname")
    public List<UserDto> getUsersSortedByFirstName() {
        ResponseEntity<List<UserDto>> response = restTemplate.exchange(
                "http://db/db/v1/users/sort/firstname",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserDto>>() {}
        );
        return response.getBody();
    }

    @GetMapping("/users/filter/age")
    public List<UserDto> getUsersFilteredByAge() {
        ResponseEntity<List<UserDto>> response = restTemplate.exchange(
                "http://db/db/v1/users/filter/age",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserDto>>() {}
        );
        return response.getBody();
    }

    @GetMapping("/users/filter/gender")
    public List<UserDto> getUsersFilteredByGender() {
        ResponseEntity<List<UserDto>> response = restTemplate.exchange(
                "http://db/db/v1/users/filter/gender",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserDto>>() {}
        );
        return response.getBody();
    }

    @GetMapping("/users/paged")
    public List<UserDto> getUsersPaged() {
        ResponseEntity<List<UserDto>> response = restTemplate.exchange(
                "http://db/db/v1/users/paged",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserDto>>() {}
        );
        return response.getBody();
    }

}
