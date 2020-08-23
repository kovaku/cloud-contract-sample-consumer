package com.github.kovaku.consumer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureStubRunner(ids = {"com.github.kovaku:cloud-contract-sample-producer:+:stubs:6565"},
		stubsMode = StubRunnerProperties.StubsMode.LOCAL)
@TestPropertySource(properties = "fibonacci.server.url=http://localhost:6565")
public class CloudContractSampleConsumerApplicationTests {

	@Autowired
	public TestRestTemplate testRestTemplate;

	@Test
	public void testBothHappyAndErrorPath() {
		//Given
		Set<Integer> request = Set.of(10, 101);
		HttpEntity<Set<Integer>> entity = new HttpEntity<>(request);

		//When
		ResponseEntity<Map<Integer, String>> responseEntity = testRestTemplate.exchange("/fibonacci", HttpMethod.POST, entity, new ParameterizedTypeReference<>() {});

		//Then
		assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
		assertThat(responseEntity.getBody().get(10), equalTo("55"));
		assertThat(responseEntity.getBody().get(101), equalTo("Bad Request"));
	}

}
