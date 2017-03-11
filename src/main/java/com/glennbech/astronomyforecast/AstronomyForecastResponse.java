package com.glennbech.astronomyforecast;

import com.glennbech.astronomyforecast.metapi.Percentage;
import com.glennbech.astronomyforecast.metapi.Time;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class AstronomyForecastResponse {

    private long timeStamp = System.currentTimeMillis();
    private List<HourlyForecast> horlyForecasts;
    private AstronomyForecastRequest request;

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<HourlyForecast> getHorlyForecasts() {
        return horlyForecasts;
    }

    public void setHorlyForecasts(List<HourlyForecast> horlyForecasts) {
        this.horlyForecasts = horlyForecasts;
    }

    public AstronomyForecastRequest getRequest() {
        return request;
    }

    public void setRequest(AstronomyForecastRequest request) {
        this.request = request;
    }
}

class HourlyForecast {

    private LocalDateTime startOfHour;
    private Percentage cloudyness;

    public LocalDateTime getStartOfHour() {
        return startOfHour;
    }

    public void setStartOfHour(LocalDateTime startOfHour) {
        this.startOfHour = startOfHour;
    }

    public static HourlyForecast fromTime(Time time) {
        HourlyForecast fc = new HourlyForecast();
        fc.setStartOfHour(LocalDateTime.ofInstant(time.getFrom().toInstant(), ZoneId.systemDefault()));
        fc.setCloudyness(time.getLocation().getCloudiness());
        return fc;
    }

    public void setCloudyness(Percentage cloudyness) {
        this.cloudyness = cloudyness;
    }

    public Percentage getCloudyness() {
        return cloudyness;
    }
}



