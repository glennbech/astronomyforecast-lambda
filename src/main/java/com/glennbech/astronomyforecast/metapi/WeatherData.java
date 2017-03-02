package com.glennbech.astronomyforecast.metapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="weatherdata")
@XmlAccessorType(XmlAccessType.NONE)
public class WeatherData {

    @XmlElement(name="product")
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "product=" + product +
                '}';
    }
}
