package org.bigmoon.learning.kryo.entity;

import com.esotericsoftware.kryo.kryo5.serializers.TaggedFieldSerializer;

import java.util.StringJoiner;

public class Car {

    String band;
    Double displacement;
//    Double price;
    public Engin engin;
    private int maxSpeed;

    public Car(String band, Double displacement,  Engin engin) {
        this.band = band;
        this.displacement = displacement;
//        this.price = price;
        this.engin = engin;
    }

    public Car() {
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Car.class.getSimpleName() + "[", "]")
                .add("band='" + band + "'")
                .add("displacement=" + displacement)
                .add("engin=" + engin)
                .add("maxSpeed=" + maxSpeed)
                .toString();
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

//    public Double getPrice() {
//        return price;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}