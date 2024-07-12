package com.kuan.tddinterview.springboottest;

import com.kuan.tddinterview.springboottest.model.User;
import com.kuan.tddinterview.springboottest.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.IntStream;

@ActiveProfiles(profiles = "test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaginationControllerTest {

    private final String api = "/api/v1/pagination";

    @Resource
    TestRestTemplate restTemplate;

    @Resource
    UserRepository userRepository;

    @Test
    public void givenGetUsers_whenPageIsRequestedFor3ItemsPerPageWhereTheDatabaseHas20Users_thenReceive3Users() {
        List<User> users = IntStream.rangeClosed(1, 20)
                .mapToObj(i -> User.builder().username("test-name-" + i).displayName(i + "").password("passw").build())
                .toList();
        userRepository.saveAllAndFlush(users);

        ResponseEntity<TestPage<Object>> response = getUsers(api + "?page=0&size=3");
        Assertions.assertEquals(3, response.getBody().getContent().size());
    }

    @Test
    public void givenGetUsers_whenPageSizeNotProvided_thenReceivePageSizeAs10() {
        ResponseEntity<TestPage<Object>> response = getUsers(api);
        Assertions.assertEquals(10, response.getBody().getSize());
    }

    @Test
    public void givenGetUsers_whenPageSizeIsGreaterThan100_thenReceivePageSizeAs100() {
        ResponseEntity<TestPage<Object>> response = getUsers(api + "?size=500");
        Assertions.assertEquals(100, response.getBody().getSize());
    }

    @Test
    public void givenGetUsers_whenPageSizeIsNegative_thenReceivePageSizeAs10() {
        ResponseEntity<TestPage<Object>> response = getUsers(api + "?size=-5");
        Assertions.assertEquals(10, response.getBody().getSize());
    }

    @Test
    public void givenGetUsers_whenPageIsNegative_thenReceiveFirstPage() {
        ResponseEntity<TestPage<Object>> response = getUsers(api + "?page=-5");
        Assertions.assertEquals(0, response.getBody().getNumber());
    }


    private ResponseEntity<TestPage<Object>> getUsers(String url) {
        return restTemplate.exchange(url, HttpMethod.GET,
                null, new ParameterizedTypeReference<TestPage<Object>>() {
                });
    }

}
