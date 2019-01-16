package ua.nure.nlebed.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RuckusWirelessClient {

    @JsonProperty("clientMac")
    private String clientMac;

    @JsonProperty("apMac")
    private String apMac;

}
