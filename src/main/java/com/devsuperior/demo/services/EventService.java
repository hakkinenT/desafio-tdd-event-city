package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.EventRepository;
import com.devsuperior.demo.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Transactional
    public EventDTO update(Long id, EventDTO dto){
        try {
            Event event = eventRepository.getReferenceById(id);
            event.setName(dto.getName());
            event.setUrl(dto.getUrl());
            event.setDate(dto.getDate());
            event.setCity(new City(dto.getCityId(), null));

            event = eventRepository.save(event);
            return new EventDTO(event);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso com id = " + id + " não existe");
        }
    }
}
