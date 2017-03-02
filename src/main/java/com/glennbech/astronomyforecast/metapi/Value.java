package com.glennbech.astronomyforecast.metapi;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by glennbech on 20.09.2016.
 */
public class Value {

    private float value;
    private String unit;

    @XmlAttribute(name = "value")
    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @XmlAttribute(name = "unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Value{" +
                "value=" + value +
                ", unit='" + unit + '\'' +
                '}';
    }
}
