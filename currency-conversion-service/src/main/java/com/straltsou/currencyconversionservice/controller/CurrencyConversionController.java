package com.straltsou.currencyconversionservice.controller;

import com.straltsou.currencyconversionservice.bean.CurrencyConversionBean;
import com.straltsou.currencyconversionservice.feign.CurrencyExchangeServiceProxy;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CurrencyExchangeServiceProxy proxy;

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity) {

        Map<String, String> urlVars = new HashMap<>();
        urlVars.put("from", from);
        urlVars.put("to", to);


        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate()
                .getForEntity(
                        "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                        CurrencyConversionBean.class,
                        urlVars);

        val response = responseEntity.getBody();
        val conversionMultiplier = response.getConversionMultiplier();

        return new CurrencyConversionBean(
                response.getId(), from, to, conversionMultiplier,
                quantity, quantity.multiply(conversionMultiplier), response.getPort());
    }

    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrencyFeign(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity) {

        CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);
        val conversionMultiplier = response.getConversionMultiplier();

        logger.info("{}", response);

        return new CurrencyConversionBean(
                response.getId(), from, to,
                conversionMultiplier, quantity,
                quantity.multiply(conversionMultiplier),
                response.getPort());
    }
}
