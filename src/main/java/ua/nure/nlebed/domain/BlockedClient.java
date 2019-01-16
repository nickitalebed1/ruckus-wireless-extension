package ua.nure.nlebed.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class BlockedClient {

    @JsonProperty
    private String id;

    @JsonProperty("mac")
    private String mac;

    @JsonProperty("description")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockedClient that = (BlockedClient) o;
        return Objects.equals(mac, that.mac);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mac);
    }
}
