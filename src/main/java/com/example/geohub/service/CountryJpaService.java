package com.example.geohub.service;

import com.example.geohub.model.*;
import com.example.geohub.repository.*;

import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class CountryJpaService implements CountryRepository {
    @Autowired
    public CountryJpaRepository countryJpaRepository;

    @Override
    public ArrayList<Country> getCountries() {
        List<Country> countriesList = countryJpaRepository.findAll();
        ArrayList<Country> countries = new ArrayList<>(countriesList);
        return countries;
    }

    @Override
    public Country addCountry(Country country) {
        countryJpaRepository.save(country);
        return country;
    }

    @Override
    public Country getCountryById(int countryId) {
        try {
            Country country = countryJpaRepository.findById(countryId).get();
            return country;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Country updateCountry(int countryId, Country country) {
        try {
            Country newCountry = countryJpaRepository.findById(countryId).get();
            if (country.getCountryName() != null) {
                newCountry.setCountryName(country.getCountryName());
            }
            if (country.getCurrency() != null) {
                newCountry.setCurrency(country.getCurrency());
            }
            if (country.getPopulation() > 0) {
                newCountry.setPopulation(country.getPopulation());
            }
            if (country.getLatitude() != null) {
                newCountry.setLatitude(country.getLatitude());
            }
            if (country.getLongitude() != null) {
                newCountry.setLongitude(country.getLongitude());
            }
            countryJpaRepository.save(newCountry);
            return newCountry;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteCountry(int countryId) {
        try {
            countryJpaRepository.deleteById(countryId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

}