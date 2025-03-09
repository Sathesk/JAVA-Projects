package com.clt.weather_data_api;

import lombok.Data;

import java.util.List;

@Data
public class GeoCodingResponseDTO {
    private List<Location> results;

    @Data
    public static class Location{
        private double latitude;
        private double longitude;
    }
}
