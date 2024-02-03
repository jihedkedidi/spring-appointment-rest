package com.agil.admin.service;

import com.agil.admin.dto.CounterAgentRequest;
import com.agil.admin.model.Appointment;
import com.agil.admin.model.CounterAgent;
import com.agil.admin.repository.CounterAgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CounterAgentService {

    @Autowired
    CounterAgentRepository counterAgentRepository;


    public List<CounterAgent> getAllCounterAgents(){
        return counterAgentRepository.findAll();
    }


}
