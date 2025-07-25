//package com.v.gatewayserver.security;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Service;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.reactive.function.BodyInserters;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import com.v.gatewayserver.config.AppAuthProperties;
//
//import lombok.extern.slf4j.Slf4j;
//
//import java.time.Instant;
//import java.util.Map;
//
//@Slf4j
//@Service
//public class TokenManager {
//	
//	 private final JwtTokenGeneratorService jwtService;
//	    private final WebClient webClient;
//
//	public TokenManager(
//	        JwtTokenGeneratorService jwtService,
//	        WebClient.Builder webClientBuilder,
//	        AppAuthProperties authProperties // custom config holder
//	    ) {
//	        this.jwtService = jwtService;
//	        this.webClient = webClientBuilder.build();
//	        this.tokenUrl = authProperties.getTokenUrl();
//	        this.clientId = authProperties.getClientId();
//	    }
//
//    private String accessToken;
//    private Instant expiryTime;
//    private String tokenUrl;
//
//    private String clientId;
//
//
//    public synchronized String getAccessToken() {
//        if (accessToken == null || Instant.now().isAfter(expiryTime.minusSeconds(30))) {
//            fetchNewToken();
//        }
//        return accessToken;
//    }
//
//    private void fetchNewToken() {
//        String assertion = jwtService.generateSignedJwt();
//
//        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
//        formData.add("grant_type", "client_credentials");
//        formData.add("client_id", clientId);
//        formData.add("client_assertion_type", "urn:ietf:params:oauth:client-assertion-type:jwt-bearer");
//        formData.add("client_assertion", assertion);
//
//        Map<String, Object> response = webClient.post()
//                .uri(tokenUrl)
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .body(BodyInserters.fromFormData(formData))
//                .retrieve()
//                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
//                .block();
//
//        if (response != null && response.containsKey("access_token")) {
//            this.accessToken = (String) response.get("access_token");
//            Integer expiresIn = (Integer) response.get("expires_in");
//            this.expiryTime = Instant.now().plusSeconds(expiresIn);
//            log.info("Fetched new access token, valid for {} seconds", expiresIn);
//        } else {
//            throw new RuntimeException("Failed to fetch token from Keycloak.");
//        }
//    }
//}
