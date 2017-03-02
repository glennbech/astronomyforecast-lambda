package com.glennbech.astronomyforecast.metapi;


import javax.xml.bind.annotation.XmlElement;

public class Location {


    private Percentage cloudiness;
    private Percentage lowClouds;
    private Percentage mediumClouds;
    private Percentage highClouds;

    private Value humidity;
    private Value temperature;
    private Value dewpointTemperature;


    @XmlElement(name = "cloudiness")
    public Percentage getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(Percentage cloudiness) {
        this.cloudiness = cloudiness;
    }


    public Percentage getLowClouds() {
        return lowClouds;
    }

    public void setLowClouds(Percentage lowClouds) {
        this.lowClouds = lowClouds;
    }

    public Percentage getMediumClouds() {
        return mediumClouds;
    }

    public void setMediumClouds(Percentage mediumClouds) {
        this.mediumClouds = mediumClouds;
    }

    public Percentage getHighClouds() {
        return highClouds;
    }

    public void setHighClouds(Percentage highClouds) {
        this.highClouds = highClouds;
    }

    public Value getHumidity() {
        return humidity;
    }

    public void setHumidity(Value humidity) {
        this.humidity = humidity;
    }

    public Value getTemperature() {
        return temperature;
    }

    public void setTemperature(Value temperature) {
        this.temperature = temperature;
    }

    public Value getDewpointTemperature() {
        return dewpointTemperature;
    }

    public void setDewpointTemperature(Value dewpointTemperature) {
        this.dewpointTemperature = dewpointTemperature;
    }


    @Override
    public String toString() {
        return "Location{" +
                "cloudiness=" + cloudiness +
                ", lowClouds=" + lowClouds +
                ", mediumClouds=" + mediumClouds +
                ", highClouds=" + highClouds +
                ", humidity=" + humidity +
                ", temperature=" + temperature +
                ", dewpointTemperature=" + dewpointTemperature +
                '}';
    }
}
