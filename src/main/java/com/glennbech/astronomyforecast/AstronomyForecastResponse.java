package com.glennbech.astronomyforecast;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AstronomyForecastResponse {

    private long timestamp = System.currentTimeMillis();
    private long timestamp2 = System.currentTimeMillis();
    private List<HourlyForecast> forecasts = new ArrayList<>();

    public long getTimestamp2() {
        return timestamp2;
    }

    public void setTimestamp2(long timestamp2) {
        this.timestamp2 = timestamp2;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<HourlyForecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<HourlyForecast> forecasts) {
        this.forecasts = forecasts;
    }
}

class HourlyForecast {

    private LocalDateTime startOfHour;

    public LocalDateTime getStartOfHour() {
        return startOfHour;
    }

    public void setStartOfHour(LocalDateTime startOfHour) {
        this.startOfHour = startOfHour;
    }
}



