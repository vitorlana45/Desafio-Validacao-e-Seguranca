package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional(readOnly = true)
    public Page<EventDTO> findAll (Pageable pageable) {
        Page<Event> eventList = repository.findAll(pageable);
        return eventList.map(list -> new EventDTO(list));
    }

    public Event convertEvent(EventDTO eventDTO, Event newEvent) {

        newEvent.setName(eventDTO.getName());
        newEvent.setDate(eventDTO.getDate());
        newEvent.setUrl(eventDTO.getUrl());
        newEvent.setCity(new City(eventDTO.getCityId(), newEvent.getName()));
        return newEvent;
    }
}
