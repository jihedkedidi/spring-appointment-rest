package com.agil.admin.dto;

import com.agil.admin.model.CounterAgent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CounterAgentRequest {

    private CounterAgent counterAgent;
}
