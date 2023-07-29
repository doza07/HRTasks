package ru.doza.json.ticekt;

public enum CityAndTimeZone {

    VVO ("Владивосток", "+10"),
    TLV ("Тель-Авив", "+3");

    private String name;
    private String timezone;

    CityAndTimeZone(String name, String timezone){
        this.name = name;
        this.timezone = timezone;
    }

    public String getName() {
        return name;
    }
    public String getTimezone() {
        return timezone;
    }

}
