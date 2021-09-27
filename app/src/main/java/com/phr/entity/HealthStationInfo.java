package com.phr.entity;

public class HealthStationInfo {
    private String HealthStationTitle;
    private String HealthStationContent;

    public HealthStationInfo(String healthStationTitle) {
        HealthStationTitle = healthStationTitle;
    }

    public HealthStationInfo(String healthStationTitle, String healthStationContent) {
        HealthStationTitle = healthStationTitle;
        HealthStationContent = healthStationContent;
    }


    public String getHealthStationTitle() {
        return HealthStationTitle;
    }

    public void setHealthStationTitle(String healthStationTitle) {
        HealthStationTitle = healthStationTitle;
    }

    public String getHealthStationContent() {
        return HealthStationContent;
    }

    public void setHealthStationContent(String healthStationContent) {
        HealthStationContent = healthStationContent;
    }
}
