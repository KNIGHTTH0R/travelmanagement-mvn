
package com.mauwahid.tm.travelmgt.repository.api.opsigo.json;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Origin",
    "Destination",
    "DepartDate"
})
public class RouteOps {

    @JsonProperty("Origin")
    private String origin;
    @JsonProperty("Destination")
    private String destination;
    @JsonProperty("DepartDate")
    private String departDate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Origin")
    public String getOrigin() {
        return origin;
    }

    @JsonProperty("Origin")
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @JsonProperty("Destination")
    public String getDestination() {
        return destination;
    }

    @JsonProperty("Destination")
    public void setDestination(String destination) {
        this.destination = destination;
    }

    @JsonProperty("DepartDate")
    public String getDepartDate() {
        return departDate;
    }

    @JsonProperty("DepartDate")
    public void setDepartDate(String departDate) {
        this.departDate = departDate;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
