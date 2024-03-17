package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    @Transactional(readOnly = true)
    public EventDTO insert(EventDTO eventDTO) {
        Event newEvent = new Event();
        convertEvent(eventDTO, newEvent);
        newEvent = repository.save(newEvent);
        return new EventDTO(newEvent);
    }

    public Event convertEvent(EventDTO eventDTO, Event newEvent) {

        newEvent.setName(eventDTO.getName());
        newEvent.setDate(eventDTO.getDate());
        newEvent.setUrl(eventDTO.getUrl());
        newEvent.setCity(new City(eventDTO.getCityId(), newEvent.getName()));
        return newEvent;
    }
}
