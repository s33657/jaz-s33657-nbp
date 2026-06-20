package org.example.jazs33657nbp;


import org.example.jazs33657nbp.model.Rate;
import org.example.jazs33657nbp.model.Root;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
public class CurrencyService {

    private final NbpApiClient nbpApiClient;

    public CurrencyService(NbpApiClient nbpApiClient) {
        this.nbpApiClient = nbpApiClient;
    }

    public Root getCurrency(String currency, LocalDate startDate, LocalDate endDate){
        return nbpApiClient.callNbpApi(currency,startDate,endDate);

    }

    public double getAvgForDateRange(String currency, LocalDate startDate, LocalDate endDate){
        Root root = nbpApiClient.callNbpApi(currency,startDate,endDate);
        List<Rate> rates = root.getRates();
        double tempVariable = 0;
        for (Rate rate : rates) {
            tempVariable += rate.getMid();
        }
        return tempVariable / rates.size();
    }
}

