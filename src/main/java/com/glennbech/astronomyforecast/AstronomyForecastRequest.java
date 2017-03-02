package com.glennbech.astronomyforecast;

public class AstronomyForecastRequest {

    private double lat, lon;
    private int timeZone;

    public AstronomyForecastRequest(double lat, double lon, int timeZoneOffset) {
        this.lat = lat;
        this.lon = lon;
        this.timeZone = timeZoneOffset;
    }

    public AstronomyForecastRequest() {
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public int getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(int timeZone) {
        this.timeZone = timeZone;
    }
}
