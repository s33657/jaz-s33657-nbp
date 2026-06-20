package org.example.jazs33657nbp;

import org.example.jazs33657nbp.model.Root;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Component
public class NbpApiClient {

    private final RestTemplate restTemplate;

    public NbpApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Root callNbpApi(String currency, LocalDate startDate, LocalDate endDate) {
        String url = "http://api.nbp.pl/api/exchangerates/rates/A/"
                + currency + "/" + startDate + "/" + endDate + "/?format=json";

        Root response = restTemplate.getForObject(url, Root.class);

        if (response == null) {
            throw new RuntimeException("Brak danych z NBP dla waluty: " + currency);
        }

        return response;
    }
}