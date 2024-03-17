package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CityService {

    @Autowired
    private CityRepository repository;


    public List<CityDTO> findAllSortedByName() {
        List<City> cityList = repository.findAll(Sort.by("name"));
        return cityList.stream().map(list -> new CityDTO(list)).toList();
    }

    public CityDTO insert(CityDTO cityDTO) {
        City newCity = new City();
        convertEntity(cityDTO, newCity);
        newCity = repository.save(newCity);
        return new CityDTO(newCity);
    }

    public City convertEntity(CityDTO cityDTO, City city) {
        city.setName(cityDTO.getName());
        return city;
    }


}
