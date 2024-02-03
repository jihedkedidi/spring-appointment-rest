package com.agil.admin.repository;

import com.agil.admin.model.Appointment;
import com.agil.admin.model.EStatus;
import com.agil.admin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT a FROM Appointment a INNER JOIN CounterAgent c ON a.location = c.location AND a.product.id = c.product.id AND c.user.id=:agentId")
    List<Appointment> getAppointmentsWithMatchingCounterAgents(@Param("agentId") Long agentId);

    @Query("SELECT a FROM Appointment a WHERE a.status =:status ")
    List<Appointment> getAppointmentsWithStatusPending(@Param("status") EStatus status);

    @Modifying
    @Query("DELETE FROM Appointment a WHERE a.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);


    @Query("SELECT a FROM Appointment a WHERE a.user.id = :userId")
    List<Appointment> getAppointmentsWithMatchingUser(@Param("userId") Long userId);

    @Query("SELECT a FROM Appointment a WHERE a.date = :date " )
    List<Appointment> getAppointmentsWithMatchingDateLocProd(@Param("date") LocalDate date );

}

