package org.example.jazs33657nbp;

import org.example.jazs33657nbp.model.Rate;
import org.example.jazs33657nbp.model.Root;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CurrencyService {

    private final NbpApiClient nbpApiClient;
    private final ExchangeRepository exchangeRepository;

    public CurrencyService(NbpApiClient nbpApiClient, ExchangeRepository exchangeRepository) {
        this.nbpApiClient = nbpApiClient;
        this.exchangeRepository = exchangeRepository;
    }

    public double getAvgForDateRange(String currency, LocalDate startDate, LocalDate endDate) {
        Root root = nbpApiClient.callNbpApi(currency, startDate, endDate);
        List<Rate> rates = root.getRates();
        double tempVariable = 0;
        for (Rate rate : rates) {
            tempVariable += rate.getMid();
        }
        double average = tempVariable / rates.size();

        Exchange exchange = new Exchange();
        exchange.setCurrency(currency);
        exchange.setStartDate(startDate);
        exchange.setEndDate(endDate);
        exchange.setAverageRate(average);
        exchange.setRequestDateTime(LocalDateTime.now());
        exchangeRepository.save(exchange);

        return average;
    }
}