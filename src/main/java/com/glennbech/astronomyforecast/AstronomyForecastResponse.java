package com.glennbech.astronomyforecast;

import com.glennbech.astronomyforecast.metapi.Percentage;
import com.glennbech.astronomyforecast.metapi.Time;

import java.text.SimpleDateFormat;
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
    private Percentage lowClouds;
    private Percentage mediumClouds;
    private float dewPoint;
    private String dewPointUnit;
    private Percentage highClouds;
    private String forecastFrom;

    public LocalDateTime getStartOfHour() {
        return startOfHour;
    }

    ;

    public void setStartOfHour(LocalDateTime startOfHour) {
        this.startOfHour = startOfHour;
    }

    public static HourlyForecast fromTime(Time time) {
        HourlyForecast fc = new HourlyForecast();

        fc.setForecastFrom(SimpleDateFormat.getDateTimeInstance().format(time.getFrom()));
        fc.setStartOfHour(LocalDateTime.ofInstant(time.getFrom().toInstant(), ZoneId.systemDefault()));
        fc.setCloudyness(time.getLocation().getCloudiness());
        fc.setHighClouds(time.getLocation().getHighClouds());

        fc.setLowClouds(time.getLocation().getLowClouds());
        fc.setMediumClouds(time.getLocation().getMediumClouds());
        fc.setDewPoint(time.getLocation().getDewpointTemperature().getValue());
        fc.setDewPointUnit(time.getLocation().getDewpointTemperature().getUnit());

        return fc;
    }

    public void setCloudyness(Percentage cloudyness) {
        this.cloudyness = cloudyness;
    }

    public Percentage getCloudyness() {
        return cloudyness;
    }


    public void setHighClouds(Percentage highClouds) {
        this.highClouds = highClouds;
    }

    public void setLowClouds(Percentage lowClouds) {
        this.lowClouds = lowClouds;
    }

    public Percentage getLowClouds() {
        return lowClouds;
    }

    public void setMediumClouds(Percentage mediumClouds) {
        this.mediumClouds = mediumClouds;
    }

    public Percentage getMediumClouds() {
        return mediumClouds;
    }

    public void setDewPoint(float dewPoint) {
        this.dewPoint = dewPoint;
    }

    public float getDewPoint() {
        return dewPoint;
    }

    public void setDewPointUnit(String dewPointUnit) {
        this.dewPointUnit = dewPointUnit;
    }

    public String getDewPointUnit() {
        return dewPointUnit;
    }

    public void setForecastFrom(String forecastFrom) {
        this.forecastFrom = forecastFrom;
    }

    public String getForecastFrom() {
        return forecastFrom;
    }
}



