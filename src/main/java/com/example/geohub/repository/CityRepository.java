package com.example.geohub.repository;

import com.example.geohub.model.*;
import java.util.*;

public interface CityRepository {
    ArrayList<City> getCities();

    City addCity(City city);

    City getCityById(int cityId);

    City updateCity(int cityId, City city);

    void deleteCity(int cityId);

    Country getCityCountry(int cityId);
}