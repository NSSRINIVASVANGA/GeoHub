package com.example.geohub.service;

import com.example.geohub.model.*;
import com.example.geohub.repository.*;

import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class CityJpaService implements CityRepository {
    @Autowired
    public CityJpaRepository cityJpaRepository;

    @Autowired
    public CountryJpaRepository countryJpaRepository;

    @Override
    public ArrayList<City> getCities() {
        List<City> citiesList = cityJpaRepository.findAll();
        ArrayList<City> cities = new ArrayList<>(citiesList);
        return cities;
    }

    @Override
    public City addCity(City city) {
        try {
            Country country = city.getCountry();
            int countryId = country.getCountryId();
            Country newCountry = countryJpaRepository.findById(countryId).get();
            city.setCountry(newCountry);
            cityJpaRepository.save(city);
            return city;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong countryId");
        }
    }

    @Override
    public City getCityById(int cityId) {
        try {
            City city = cityJpaRepository.findById(cityId).get();
            return city;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public City updateCity(int cityId, City city) {
        try {
            City newCity = cityJpaRepository.findById(cityId).get();
            if (city.getCityName() != null) {
                newCity.setCityName(city.getCityName());
            }
            if (city.getPopulation() >= 0) {
                newCity.setPopulation(city.getPopulation());
            }
            if (city.getLatitude() != null) {
                newCity.setLatitude(city.getLatitude());
            }
            if (city.getLongitude() != null) {
                newCity.setLongitude(city.getLongitude());
            }
            if (city.getCountry() != null) {
                Country country = city.getCountry();
                int countryId = country.getCountryId();
                Country newCountry = countryJpaRepository.findById(countryId).get();
                newCity.setCountry(newCountry);
            }
            cityJpaRepository.save(newCity);
            return newCity;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteCity(int cityId) {
        try {
            cityJpaRepository.deleteById(cityId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public Country getCityCountry(int cityId) {
        try {
            City city = cityJpaRepository.findById(cityId).get();
            return city.getCountry();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}