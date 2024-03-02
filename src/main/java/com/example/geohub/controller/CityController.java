package com.example.geohub.controller;

import com.example.geohub.model.*;
import com.example.geohub.repository.*;
import com.example.geohub.service.*;

import java.util.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class CityController {
    @Autowired
    public CityJpaService cityJpaService;

    @GetMapping("/countries/cities")
    public ArrayList<City> getCities() {
        return cityJpaService.getCities();
    }

    @PostMapping("/countries/cities")
    public City addCity(@RequestBody City city) {
        return cityJpaService.addCity(city);
    }

    @GetMapping("/countries/cities/{cityId}")
    public City getCityById(@PathVariable("cityId") int cityId) {
        return cityJpaService.getCityById(cityId);
    }

    @PutMapping("/countries/cities/{cityId}")
    public City updateCity(@PathVariable("cityId") int cityId, @RequestBody City city) {
        return cityJpaService.updateCity(cityId, city);
    }

    @DeleteMapping("/countries/cities/{cityId}")
    public void deleteCity(@PathVariable("cityId") int cityId) {
        cityJpaService.deleteCity(cityId);
    }

    @GetMapping("/cities/{cityId}/country")
    public Country getCityCountry(@PathVariable("cityId") int cityId) {
        return cityJpaService.getCityCountry(cityId);
    }
}