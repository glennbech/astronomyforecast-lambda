package com.glennbech.astronomyforecast.metapi;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class Product {

    @XmlElement(name="time")
    private List<Time> timeList;

    public List<Time> getTimeList() {
        return timeList;
    }

    @Override
    public String toString() {
        return "Product{" +
                "timeList=" + timeList +
                '}';
    }
}
