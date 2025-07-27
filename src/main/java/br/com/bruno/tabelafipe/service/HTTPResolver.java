package br.com.bruno.tabelafipe.service;

import br.com.bruno.tabelafipe.infrastructure.ApiException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class HTTPResolver {

    public String getApiResponse(String url) {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> res = null;

        try {
            res = client.send(req, HttpResponse.BodyHandlers.ofString());

            if (res.statusCode() == 429)
                throw new ApiException("Limite de requisições excedido: " + res.body());

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return res.body();
    }
}
