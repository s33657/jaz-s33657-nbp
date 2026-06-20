package org.example.jazs33657nbp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class CurrencyRestController {

    private final CurrencyService currencyService;

    public CurrencyRestController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/avg")
    public double getAverageRate(
            @RequestParam String currency,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    )    {
        return currencyService.getAvgForDateRange(currency, startDate, endDate);
    }

}
