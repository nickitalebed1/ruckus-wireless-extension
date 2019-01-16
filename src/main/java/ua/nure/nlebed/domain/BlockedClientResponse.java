package ua.nure.nlebed.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class BlockedClientResponse {

    @JsonProperty("list")
    private List<BlockedClient> blockedClients;
}
