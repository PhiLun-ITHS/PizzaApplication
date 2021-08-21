package com.example.PizzaApplication.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PizzaEndToEndTest {

    @LocalServerPort
    private int port;



    @Test
    void getPizzasReturnsListOfPizzas(){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:"+port+"/"))
                .build();

        var response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).join();

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.body()).isEqualTo("[{\"id\":1,\"name\":\"Margherita\",\"ingredients\":\"Tomatsås, Ost\",\"price\":75}," +
                "{\"id\":2,\"name\":\"Vesuvio\",\"ingredients\":\"Tomatsås, Ost, Skinka\",\"price\":80}," +
                "{\"id\":3,\"name\":\"Capricciosa\",\"ingredients\":\"Tomatsås, Ost, Skinka, Champinjon\",\"price\":85}]");
    }
}