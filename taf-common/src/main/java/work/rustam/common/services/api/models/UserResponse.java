package work.rustam.common.services.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "data"
})
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {

    @JsonProperty("data")
    public SingleUserInfo data;
}