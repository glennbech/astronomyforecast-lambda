package com.glennbech.astronomyforecast;

import java.time.LocalDateTime;

public class AstronomyForecastHandler {

    public AstronomyForecastResponse getAstronmyForecast(AstronomyForecastRequest request) {
        final AstronomyForecastResponse resp = new AstronomyForecastResponse();
        HourlyForecast hfc = new HourlyForecast();
        hfc.setStartOfHour(LocalDateTime.now());
        resp.getForecasts().add(hfc);
        return resp;
    }

}
