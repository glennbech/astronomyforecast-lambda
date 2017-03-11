package com.glennbech.astronomyforecast;

public class AstronomyForecastRequest {

    private long timeStamp;
    private float lat, lon;
    private int timeZone;

    public AstronomyForecastRequest(float lat, float lon, int timeZoneOffset) {
        this.lat = lat;
        this.lon = lon;
        this.timeZone = timeZoneOffset;
        this.timeStamp = System.currentTimeMillis();
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public AstronomyForecastRequest() {
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public int getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(int timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public String toString() {
        return "AstronomyForecastRequest{" +
                "lat=" + lat +
                ", lon=" + lon +
                ", timeZone=" + timeZone +
                '}';
    }
}