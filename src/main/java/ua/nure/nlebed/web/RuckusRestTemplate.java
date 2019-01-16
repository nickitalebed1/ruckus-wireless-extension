package ua.nure.nlebed.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.nure.nlebed.builder.QueryForClientsJsonPayloadBuilder;
import ua.nure.nlebed.domain.BlockedClient;
import ua.nure.nlebed.domain.BlockedClientResponse;
import ua.nure.nlebed.domain.RuckusWirelessClient;
import ua.nure.nlebed.domain.RuckusWirelessClientResponse;
import ua.nure.nlebed.domain.RuckusZone;
import ua.nure.nlebed.domain.RuckusZonesResponse;

import java.util.List;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.util.CollectionUtils.isEmpty;

@Component
public class RuckusRestTemplate {

    private final RestTemplate restTemplate;
    private static final String API_URL = "https://109.86.224.31:8445/wsg/api/public/";
    private final QueryForClientsJsonPayloadBuilder jsonPayloadBuilder;

    private static final String BLOCK_PAYLOAD_FORM = "{\n" +
            "  \"blockClientList\": [\n" +
            "%s" +
            "  ],\n" +
            "  \"description\": \"unuathorized\"\n" +
            "}";

    @Autowired
    public RuckusRestTemplate(RestTemplate restTemplate, QueryForClientsJsonPayloadBuilder jsonPayloadBuilder) {
        this.restTemplate = restTemplate;
        this.jsonPayloadBuilder = jsonPayloadBuilder;
    }

    public List<RuckusWirelessClient> queryForCurrentClients() {
        String url = API_URL + "v6_1/query/client";
        String queryForClientsPayload = jsonPayloadBuilder.getBody(null);
        return restTemplate.exchange(url,
                POST, new HttpEntity<>(queryForClientsPayload, getHttpHeaders()), RuckusWirelessClientResponse.class).getBody().getRuckusClients();
    }

    public List<BlockedClient> queryForBlockedClients() {
        String url = API_URL + "v6_1/blockClient/query";
        String queryForClientsPayload = jsonPayloadBuilder.getBody(null);
        return restTemplate.exchange(url,
                POST, new HttpEntity<>(queryForClientsPayload, getHttpHeaders()), BlockedClientResponse.class).getBody().getBlockedClients();
    }

    public void sendBlockUsersRequest(List<RuckusWirelessClient> clientsToBlock) {
        if (isEmpty(clientsToBlock)) {
            return;
        }

        String url = API_URL + "v6_1/blockClient";
        HttpHeaders headers = getHttpHeaders();
        StringBuilder r = new StringBuilder();
        int i = 0;
        for (RuckusWirelessClient s : clientsToBlock) {
            if (i++ == clientsToBlock.size() - 1) {
                r.append("{").append("\"apMac\":\"").append(s.getApMac()).append("\",")
                        .append("\"mac\":\"").append(s.getClientMac()).append("\"").append("}");
                break;
            }
            r.append("{").append("\"apMac\":\"").append(s.getApMac()).append("\",")
                    .append("\"mac\":\"").append(s.getClientMac()).append("\"").append("},");
        }

        restTemplate.exchange(url, POST, new HttpEntity<>(String.format(BLOCK_PAYLOAD_FORM, r.toString()), headers), String.class);
    }

    public void sendUnblock(List<String> ids) {
        String url = API_URL + "v6_1/blockClient/";
        HttpHeaders headers = getHttpHeaders();
        for (String id : ids) {
            restTemplate.exchange(url + id, DELETE, new HttpEntity<>(headers), String.class);
        }
    }

    private List<RuckusZone> getZones() {
        String url = API_URL + "v6_1/rkszones";
        return restTemplate.exchange(url,
                GET, new HttpEntity<>(getHttpHeaders()), RuckusZonesResponse.class).getBody().getRuckusZones();
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        headers.add("Cookie", getSessionCookie());
        return headers;
    }

    private String getSessionCookie() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>("{\"userName\":\"admin\",\"password\":\"523243Zz!\"}", headers);
        ResponseEntity<String> exchange = restTemplate.exchange(API_URL + "v6_1/session",
                POST, entity, String.class);
        return exchange.getHeaders().get("Set-Cookie").get(0);
    }

}
