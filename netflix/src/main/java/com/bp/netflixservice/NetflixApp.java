package com.bp.netflixservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
})
public class NetflixApp {
    private final String BASE_URL = "https://unogs-unogs-v1.p.rapidapi.com/search/titles?order_by=date&type=movie";
    public static void main(String[] args) {
        SpringApplication.run(NetflixApp.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
//        NetflixResponse[] titles;
//
//        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
//        interceptors.add(new HeaderRequestInterceptor("X-RapidAPI-Key", "900dbcd665msh77c52b06e9f0209p118b89jsn34e17bf8a909"));
//        interceptors.add(new HeaderRequestInterceptor("X-RapidAPI-Host", "unogs-unogs-v1.p.rapidapi.com"));
//        restTemplate.setInterceptors(interceptors);
//        ResponseEntity<NetflixResponse[]> responseEntity =
//                restTemplate.getForEntity(BASE_URL, NetflixResponse[].class);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://unogs-unogs-v1.p.rapidapi.com/search/titles?order_by=date&type=movie"))
                .header("X-RapidAPI-Key", "900dbcd665msh77c52b06e9f0209p118b89jsn34e17bf8a909")
                .header("X-RapidAPI-Host", "unogs-unogs-v1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//        ObjectMapper objMapper = new ObjectMapper();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(response.body());
        JSONArray jsonArray = (JSONArray) jsonObject.get("results");

        return null;
    }
}
