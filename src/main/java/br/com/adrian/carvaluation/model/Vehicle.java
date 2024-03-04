package br.com.adrian.carvaluation.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Vehicle (
        @JsonAlias("codigo")
        String code,

        @JsonAlias("nome")
        String name
) {
}
