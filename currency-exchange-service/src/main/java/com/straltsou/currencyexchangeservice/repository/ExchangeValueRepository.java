package com.straltsou.currencyexchangeservice.repository;

import com.straltsou.currencyexchangeservice.entity.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {

    ExchangeValue findByFromAndTo(String from, String to);
}
