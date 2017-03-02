package com.glennbech.astronomyforecast.metapi;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

public class Time {

    private Date from;

    private Date to;

    private Location location;


    @XmlElement(name = "location")
    public Location getLocation() {
        return location;
    }


    @XmlAttribute(name = "from")
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getFrom() {
        return from;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    @XmlAttribute(name = "to")
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Time{" +
                "from=" + from +
                ", to=" + to +
                ", location=" + location +
                '}';
    }
}

