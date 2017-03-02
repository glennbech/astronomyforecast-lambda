package com.glennbech.astronomyforecast.metapi;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by glennbech on 08.09.2016.
 */
public class Percentage {


    private float percentage;

    @XmlAttribute(name = "percent")
    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "Percentage{" +
                "percentage=" + percentage +
                '}';
    }
}
