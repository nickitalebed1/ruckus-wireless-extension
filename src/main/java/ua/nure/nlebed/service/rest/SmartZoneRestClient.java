package ua.nure.nlebed.service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SmartZoneRestClient {

    private final RestTemplate restTemplate;

    @Autowired
    public SmartZoneRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
