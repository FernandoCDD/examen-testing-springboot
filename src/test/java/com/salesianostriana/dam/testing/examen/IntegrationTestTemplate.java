package com.salesianostriana.dam.testing.examen;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesianostriana.dam.testing.examen.dto.GetDatoMeteoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration-test")
class IntegrationTestTemplate {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
	}

	@Test
	void addDatoMeteo_With201_CREATED(){
		String path = "http://localhost"+port+"/meteo/add";

		GetDatoMeteoDto dto = new GetDatoMeteoDto("Sevilla", LocalDate.now(), 4.0);

		//HttpEntity<GetDatoMeteoDto> objectRequest = new HttpEntity<>();

	}
}





















/*
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration-test")
@Sql(value = "classpath:delete-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(value = "classpath:insert-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class CommentControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private MultiValueMap<String, String> headers;


    @BeforeEach
    public void setup() {
        testRestTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        LoginDto loginDto = new LoginDto("tpetteford0@linkedin.com", "$2a$12$V2STGXRVuoOEqKtAtKZJ3ePwcVAb/GZ7y4NTKhrlZ1MJy6AWiLyXe");
        String userToken = jwtTokenProvider.generateToken(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        System.out.println(userToken);
        headers=new LinkedMultiValueMap<>();
        headers.add("content-type","application/json");
        headers.add("Authorization","Bearer "+ userToken);
    }

    //Fernando
    @Test
    void getCommentByIdWith200_OKResponse(){
        long commentId = 1L;
        long postId = 1L;

        CommentDto commentDto = new CommentDto();
        commentDto.setId(commentId);

        String path = "http://localhost:"+port+"/api/v1/posts/"+postId+"/comments/"+commentId;

        ResponseEntity<CommentDto> expectedResponse = testRestTemplate.getForEntity(path, CommentDto.class);

        assertEquals(HttpStatus.OK, expectedResponse.getStatusCode());
        assertEquals(commentDto.getId(), expectedResponse.getBody().getId());

    }
 */