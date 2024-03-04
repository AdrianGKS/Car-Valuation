package br.com.adrian.carvaluation.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DetailedVehicle (
        @JsonAlias("Valor")
        String value,
        @JsonAlias("Marca")
        String brand,
        @JsonAlias("Modelo")
        String model,
        @JsonAlias("AnoModelo")
        Integer year,
        @JsonAlias("Combustível")
        String fuel
) {
}
