package org.bigmoon.learning.kryo.entity;

public class Car {

    String band;
    Double displacement;
    Double price;
    Engin engin;

    public Car(String band, Double displacement, Double price, Engin engin) {
        this.band = band;
        this.displacement = displacement;
        this.price = price;
        this.engin = engin;
    }

    public Car() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Car{");
        sb.append("band='").append(band).append('\'');
        sb.append(", displacement=").append(displacement);
        sb.append(", price=").append(price);
        sb.append(", engin=").append(engin);
        sb.append('}');
        return sb.toString();
    }

    public Engin getEngin() {
        return engin;
    }

    public void setEngin(Engin engin) {
        this.engin = engin;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public Double getDisplacement() {
        return displacement;
    }

    public void setDisplacement(Double displacement) {
        this.displacement = displacement;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}