package org.example.jazs33657nbp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CurrencyRestController {

    private final CurrencyService currencyService;
    private final ExchangeRepository exchangeRepository;

    public CurrencyRestController(CurrencyService currencyService, ExchangeRepository exchangeRepository) {
        this.currencyService = currencyService;
        this.exchangeRepository = exchangeRepository;
    }

    @GetMapping("/avg")
    public double getAverageRate(
            @RequestParam String currency,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    )    {
        return currencyService.getAvgForDateRange(currency, startDate, endDate);
    }

    @GetMapping("/logs")
    public List<Exchange> getLogs() {
        return exchangeRepository.findAll();
    }

}
