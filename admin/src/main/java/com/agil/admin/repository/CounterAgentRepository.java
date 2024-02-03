package com.agil.admin.repository;

import com.agil.admin.model.Appointment;
import com.agil.admin.model.CounterAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CounterAgentRepository extends JpaRepository<CounterAgent,Long> {



}
