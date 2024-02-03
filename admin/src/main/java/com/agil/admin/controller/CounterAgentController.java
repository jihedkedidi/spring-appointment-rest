package com.agil.admin.controller;


import com.agil.admin.model.CounterAgent;
import com.agil.admin.service.CounterAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("api/counterAgents/")
public class CounterAgentController {
    @Autowired
    CounterAgentService counterAgentService;
    @GetMapping("")
    public List<CounterAgent> getAllCounterAgents(){
        return counterAgentService.getAllCounterAgents();
    }

}
