package com.glennbech.astronomyforecast;


import com.glennbech.astronomyforecast.metapi.Time;
import com.glennbech.astronomyforecast.metapi.WeatherData;
import com.luckycatlabs.sunrisesunset.SunriseSunsetCalculator;
import com.luckycatlabs.sunrisesunset.dto.Location;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import javax.xml.bind.JAXBContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

public class WeatherDataService {

    public static final int CLEAR_TRESHOLD = 4;

    public List<Time> findDarkHoursWithClearSky(float lat, float lon) throws Exception {
        WeatherData data = getWeatherData(lat, lon);
        List<Time> forecasts = data.getProduct().getTimeList().stream()
                .filter(time -> time.getFrom().after(new Date()))
                .filter(time -> time.getLocation().getCloudiness() != null)
                .filter(time -> isInDarkness(time.getTo(), lat, lon))
                .filter(time -> time.getLocation().getCloudiness().getPercentage() < CLEAR_TRESHOLD)
                .collect(Collectors.toList());
        return forecasts;
    }

    private WeatherData getWeatherData(float lat, float lon) throws Exception {
        Serializer serializer = new Persister();
        URL url;
        try {
            url = new URL("http://api.met.no/weatherapi/locationforecastlts/1.3/?lat=" + lat + ";lon=" + lon);
            JAXBContext jaxbContext = JAXBContext.newInstance(WeatherData.class) ;
            return (WeatherData) jaxbContext.createUnmarshaller().unmarshal(url.openStream());
        } catch (MalformedURLException e) {
            throw new IllegalStateException(e);
        }
    }

    public static boolean isInDarkness(Date date, float lat, float lon) {

        final Calendar theDate = Calendar.getInstance();
        theDate.setTime(date);

        Date midnight = midnighOf(theDate).getTime();
        Calendar tomorrow = (Calendar) theDate.clone();
        tomorrow.roll(Calendar.DAY_OF_MONTH, 1);
        Date midnightTomorrow = midnighOf(tomorrow).getTime();

        SunriseSunsetCalculator calc = makeCalculator(lat, lon);
        Calendar astronomicalSunRise = calc.getAstronomicalSunriseCalendarForDate(theDate);
        Calendar astronomicalSunSet = calc.getAstronomicalSunsetCalendarForDate(theDate);
        return (astronomicalSunRise != null && date.after(midnight) && date.before(astronomicalSunRise.getTime())) ||
                (astronomicalSunSet != null && date.after(astronomicalSunSet.getTime()) && date.before(midnightTomorrow));
    }

    private static Calendar midnighOf(Calendar ofWhatDay) {
        Calendar date = new GregorianCalendar();
        date.set(Calendar.YEAR, ofWhatDay.get(Calendar.YEAR));
        date.set(Calendar.MONTH, ofWhatDay.get(Calendar.MONTH));
        date.set(Calendar.DAY_OF_MONTH, ofWhatDay.get(Calendar.DAY_OF_MONTH));
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        return date;
    }

    private static SunriseSunsetCalculator makeCalculator(float lat, float lon) {
        Location l = new Location(lat, lon);
        return new SunriseSunsetCalculator(l, TimeZone.getDefault());
    }


    public static void main(String[] args) throws Exception {
        WeatherDataService wds = new WeatherDataService();
        System.out.println(wds.getWeatherData(10f,50f));
    }
}
