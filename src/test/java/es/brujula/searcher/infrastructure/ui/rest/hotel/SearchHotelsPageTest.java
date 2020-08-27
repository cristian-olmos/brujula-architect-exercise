package es.brujula.searcher.infrastructure.ui.rest.hotel;

import es.brujula.searcher.application.query.hotel.search.SearchHotelsQueryHandler;
import es.brujula.searcher.domain.hotel.model.Hotel;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class SearchHotelsPageTest {

    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String HOTEL_ID = "hotelId";
    private static final String CATEGORY_ID = "1";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String USERNAME_VALUE = "test";
    private static final String PASWWORD_VALUE = "12345";
    private static final String HTTP_LOCALHOST = "http://localhost:";
    private static final String V_1_AUTH_AUTHENTICATE = "/v1/auth/authenticate";
    private static final String AUTHORIZATION = "Authorization";
    private static final String V_1_HOTELS = "/v1/hotels";
    private static final String DATA = "data";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private SearchHotelsQueryHandler searchHotelsQueryHandler;

    @Test
    @WithMockUser
    public void createUser_validParams_200() throws Exception {
        doReturn(getHotelsResponse()).when(searchHotelsQueryHandler).handle(any());

        List<String> authorization = getAuthorization();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(AUTHORIZATION, authorization.get(0));

        ResponseEntity<Map> response = restTemplate.exchange(
                new URL(HTTP_LOCALHOST + port + V_1_HOTELS).toString(), HttpMethod.GET, new HttpEntity<Object>(headers), Map.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, List<Map<String, String>>> body = response.getBody();
        Assert.assertEquals(body.get(DATA).get(0).get("name"), NAME);
        Assert.assertEquals(body.get(DATA).get(0).get("address"), ADDRESS);
        Assert.assertEquals(body.get(DATA).get(0).get("id"), HOTEL_ID);
        Assert.assertEquals(body.get(DATA).get(0).get("category"), CATEGORY_ID);
    }

    private List<String> getAuthorization() throws MalformedURLException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap();
        map.add(USERNAME, USERNAME_VALUE);
        map.add(PASSWORD, PASWWORD_VALUE);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity(map, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(
                new URL(HTTP_LOCALHOST + port + V_1_AUTH_AUTHENTICATE).toString(), request, Map.class);
        return response.getHeaders().get(AUTHORIZATION);
    }

    private List<Hotel> getHotelsResponse() {
        return Arrays.asList(Hotel.create(HOTEL_ID, NAME, ADDRESS, CATEGORY_ID));
    }

}
