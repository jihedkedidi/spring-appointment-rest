package com.agil.admin.dto;

import com.agil.admin.model.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppointmentRequest {
    private Appointment appointment;

}
