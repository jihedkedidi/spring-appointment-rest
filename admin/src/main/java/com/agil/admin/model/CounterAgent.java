package com.agil.admin.model;


import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Table(name = "counter_agents")
@Transactional
public class CounterAgent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = User.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @ManyToOne(targetEntity = Product.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Product product;

    private String location ;



}
