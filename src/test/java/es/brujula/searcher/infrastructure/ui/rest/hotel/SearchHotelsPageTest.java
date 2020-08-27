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
        headers.add("Authorization", authorization.get(0));

        ResponseEntity<Map> response = restTemplate.exchange(
                new URL("http://localhost:" + port + "/v1/hotels").toString(), HttpMethod.GET, new HttpEntity<Object>(headers), Map.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, List<Map<String, String>>> body = response.getBody();
        Assert.assertEquals(body.get("data").get(0).get("name"), NAME);
        Assert.assertEquals(body.get("data").get(0).get("address"), ADDRESS);
        Assert.assertEquals(body.get("data").get(0).get("id"), HOTEL_ID);
        Assert.assertEquals(body.get("data").get(0).get("category"), CATEGORY_ID);
    }

    private List<String> getAuthorization() throws MalformedURLException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("username", "test");
        map.add("password", "12345");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(
                new URL("http://localhost:" + port + "/v1/auth/authenticate").toString(), request, Map.class);
        return response.getHeaders().get("Authorization");
    }

    private List<Hotel> getHotelsResponse() {
        return Arrays.asList(Hotel.create(HOTEL_ID, NAME, ADDRESS, CATEGORY_ID));
    }

}
