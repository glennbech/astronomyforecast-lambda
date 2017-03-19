package com.glennbech.astronomyforecast;


import com.glennbech.astronomyforecast.metapi.Time;
import com.glennbech.astronomyforecast.metapi.WeatherData;
import com.luckycatlabs.sunrisesunset.SunriseSunsetCalculator;
import com.luckycatlabs.sunrisesunset.dto.Location;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

public class WeatherDataService {

    public static final int CLEAR_TRESHOLD = 4;

    public List<Time> findDarkHoursWithClearSky(double lat, double lon, TimeZone timeZone) {
        WeatherData data = getWeatherData(lat, lon);
        return data.getProduct().getTimeList().stream()
                .filter(time -> time.getFrom().after(new Date()))
                .filter(time -> time.getLocation().getCloudiness() != null)
                .filter(time -> isInDarkness(time.getTo(), lat, lon, timeZone))
                .collect(Collectors.toList());
    }

    private WeatherData getWeatherData(double lat, double lon) {
        URL url;
        try {
            url = new URL("http://api.met.no/weatherapi/locationforecastlts/1.3/?lat=" + lat + ";lon=" + lon);
            JAXBContext jaxbContext = JAXBContext.newInstance(WeatherData.class);
            return (WeatherData) jaxbContext.createUnmarshaller().unmarshal(url.openStream());
        } catch (IOException | JAXBException e) {
            throw new IllegalStateException(e);
        }
    }

    public static boolean isInDarkness(Date date, double lat, double lon, TimeZone timeZone) {

        final Calendar theDate = Calendar.getInstance();
        theDate.setTime(date);

        Date midnight = midnighOf(theDate).getTime();
        Calendar tomorrow = (Calendar) theDate.clone();
        tomorrow.roll(Calendar.DAY_OF_MONTH, 1);
        Date midnightTomorrow = midnighOf(tomorrow).getTime();

        SunriseSunsetCalculator calc = makeCalculator(lat, lon, timeZone);
        Calendar astronomicalSunRise = calc.getAstronomicalSunriseCalendarForDate(theDate);
        Calendar astronomicalSunSet = calc.getAstronomicalSunsetCalendarForDate(theDate);

        return (date.after(midnight) && date.before(astronomicalSunRise.getTime())) ||
                (date.after(astronomicalSunSet.getTime()) && date.before(midnightTomorrow));
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

    private static SunriseSunsetCalculator makeCalculator(double lat, double lon, TimeZone timeZone) {
        Location l = new Location(lat, lon);
        return new SunriseSunsetCalculator(l, timeZone);
    }


    public static void main(String[] args) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
        System.out.println(isInDarkness(formatter.parse("2017-01-01 06:00:00"), 78, 15, TimeZone.getTimeZone("CET")));

    }
}
