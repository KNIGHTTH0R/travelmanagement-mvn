
package com.mauwahid.tm.travelmgt.repository.api.opsigo.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Routes",
    "Adult",
    "Child",
    "Infant",
    "FareType",
    "PreferredAirlines"
})
public class FlightAvailReq {

    @JsonProperty("Routes")
    private List<RouteOps> routes = null;
    @JsonProperty("Adult")
    private Integer adult;
    @JsonProperty("Child")
    private Integer child;
    @JsonProperty("Infant")
    private Integer infant;
    @JsonProperty("FareType")
    private String fareType;
    @JsonProperty("PreferredAirlines")
    private List<Integer> preferredAirlines = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Routes")
    public List<RouteOps> getRoutes() {
        return routes;
    }

    @JsonProperty("Routes")
    public void setRoutes(List<RouteOps> routes) {
        this.routes = routes;
    }

    @JsonProperty("Adult")
    public Integer getAdult() {
        return adult;
    }

    @JsonProperty("Adult")
    public void setAdult(Integer adult) {
        this.adult = adult;
    }

    @JsonProperty("Child")
    public Integer getChild() {
        return child;
    }

    @JsonProperty("Child")
    public void setChild(Integer child) {
        this.child = child;
    }

    @JsonProperty("Infant")
    public Integer getInfant() {
        return infant;
    }

    @JsonProperty("Infant")
    public void setInfant(Integer infant) {
        this.infant = infant;
    }

    @JsonProperty("FareType")
    public String getFareType() {
        return fareType;
    }

    @JsonProperty("FareType")
    public void setFareType(String fareType) {
        this.fareType = fareType;
    }

    @JsonProperty("PreferredAirlines")
    public List<Integer> getPreferredAirlines() {
        return preferredAirlines;
    }

    @JsonProperty("PreferredAirlines")
    public void setPreferredAirlines(List<Integer> preferredAirlines) {
        this.preferredAirlines = preferredAirlines;
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
