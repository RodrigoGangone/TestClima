package com.example.rodrigo_gangone.testclima.Model;

import java.util.ArrayList;
import java.util.List;

public class FiveDaysWeatherDataDetail{

    public int cod;
    public double message;
    public Integer cnt;
    public List<CityDaysDetail> list = new ArrayList<>();
    public City city;

}
