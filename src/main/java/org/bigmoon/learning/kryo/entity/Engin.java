package org.bigmoon.learning.kryo.entity;

/**
 * 汽车发动机
 */
public class Engin {

    String band;
    Integer cylinder;

    public Engin(String band, Integer cylinder) {
        this.band = band;
        this.cylinder = cylinder;
    }

    public Engin() {
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public Integer getCylinder() {
        return cylinder;
    }

    public void setCylinder(Integer cylinder) {
        this.cylinder = cylinder;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Engin{");
        sb.append("band='").append(band).append('\'');
        sb.append(", cylinder=").append(cylinder);
        sb.append('}');
        return sb.toString();
    }
}
