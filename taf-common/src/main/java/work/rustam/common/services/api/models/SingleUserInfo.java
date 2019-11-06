package work.rustam.common.services.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "year",
        "color",
        "pantone_value"
})

@ToString
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleUserInfo {

    @JsonProperty("id")
    public Integer id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("year")
    public Integer year;

    @JsonProperty("color")
    public String color;

    @JsonProperty("pantone_value")
    public String pantone_value;
}