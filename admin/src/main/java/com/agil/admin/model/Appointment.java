    package com.agil.admin.model;


    import lombok.*;

    import javax.persistence.*;
    import javax.transaction.Transactional;
    import java.time.LocalDate;
    @Data
    @Entity
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter

    @Table(name = "appointments")
    @Transactional
    public class Appointment {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private LocalDate date;
        private String heure;
        private String minutes;
        private String location;
        //@Convert(converter = EnumToIntValue.class)
        private EStatus status;

        @OneToOne(targetEntity = User.class)
        @JoinColumn(name = "user_id",referencedColumnName = "id")
        private User user;
        @ManyToOne(targetEntity = Product.class)
        @JoinColumn(name = "product_id",referencedColumnName = "id")
        private Product product;

    }
