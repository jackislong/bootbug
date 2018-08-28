package org.spring.springboot.dao;

import java.io.Serializable;

public class CountrylanguageEntity extends CountrylanguageEntityKey implements Serializable {
    private String isofficial;

    private Float percentage;

    public String getIsofficial() {
        return isofficial;
    }

    public void setIsofficial(String isofficial) {
        this.isofficial = isofficial == null ? null : isofficial.trim();
    }

    public Float getPercentage() {
        return percentage;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }
}