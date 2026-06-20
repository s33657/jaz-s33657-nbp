package org.example.jazs33657nbp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name ="Currency" , description = "Operations about currency")
public class CurrencyRestController {

    private final CurrencyService currencyService;
    private final ExchangeRepository exchangeRepository;

    public CurrencyRestController(CurrencyService currencyService, ExchangeRepository exchangeRepository) {
        this.currencyService = currencyService;
        this.exchangeRepository = exchangeRepository;
    }

    @Operation(summary = "Download the average currency rates", description = "Returns the average mid rate for the given currency within a date range from the NBP API")
    @GetMapping("/avg")
    public double getAverageRate(
            @RequestParam String currency,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    )    {
        return currencyService.getAvgForDateRange(currency, startDate, endDate);
    }

    @Operation(summary = "Download the currency exchange logs", description = "Returns the currency exchange logs from the database")
    @GetMapping("/logs")
    public List<Exchange> getLogs() {
        return exchangeRepository.findAll();
    }

}
