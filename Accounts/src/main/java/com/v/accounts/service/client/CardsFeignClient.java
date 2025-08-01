package com.v.accounts.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.v.accounts.dto.CardsDto;

import jakarta.validation.constraints.Pattern;

@FeignClient(name="cards",url = "http://cards:9000", fallback = CardsFallback.class)
public interface CardsFeignClient {

	@GetMapping(value="/api/fetch", consumes="json")
	public ResponseEntity<CardsDto> fetchCardDetails(@RequestParam String mobileNumber);

}
