package com.glennbech.astronomyforecast;

import com.amazonaws.services.lambda.runtime.Context;
import com.glennbech.astronomyforecast.metapi.Time;
import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AstronomyForecastHandler {

    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {

        final ReadContext ctx = JsonPath.parse(inputStream);
        Map<String, String> params = ctx.read("$.queryStringParameters");

        context.getLogger().log("params " + params);
        final Double latParam = Double.valueOf(params.get("lat"));
        final Double lonPram = Double.valueOf(params.get("lon"));
        final int timeZone = Integer.valueOf(params.get("timeZone"));

        context.getLogger().log("lat is " + latParam + ", lon is " + lonPram + ", tz " + timeZone);

        final AstronomyForecastResponse resp = new AstronomyForecastResponse();
        final WeatherDataService service = new WeatherDataService();
        final List<Time> darkTimes;

        try {
            darkTimes = service.findDarkHoursWithClearSky(latParam, lonPram, timeZone);
        } catch (Exception e) {
            context.getLogger().log(e.getMessage());
            return;
        }
        final List<HourlyForecast> hourlyForecasts = darkTimes
                .stream()
                .map(HourlyForecast::fromTime)
                .collect(Collectors.toList());
        resp.setHorlyForecasts(hourlyForecasts);
        String s = new Gson().toJson(resp);
        LambdaResponse lambdaResponse = new LambdaResponse("200", new HashMap<>(), s);
        s = new Gson().toJson(lambdaResponse);
        outputStream.write(s.getBytes());
        outputStream.flush();
        outputStream.close();
    }
}