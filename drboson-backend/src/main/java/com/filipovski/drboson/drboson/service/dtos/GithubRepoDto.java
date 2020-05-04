package com.filipovski.drboson.drboson.service.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GithubRepoDto {
    private String name;

    @JsonProperty("full_name")
    private String fullName;

    private String owner;

    @JsonProperty("html_url")
    private String htmlURL;

    private String description;

    @JsonIgnore
    private Boolean exists;

    @JsonProperty("owner")
    private void unpackNested(Map<String, Object> owner) {
        this.owner = (String) owner.get("login");
    }
}
