/*
    package com.agil.admin.model;

    import lombok.*;
    import org.hibernate.mapping.ToOne;

    import javax.persistence.*;

    @Data
    @Entity
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public class AgentGuichet {
        @Id
        @GeneratedValue(strategy =GenerationType.IDENTITY)
        private Long id;

        */
/*@ManyToMany(targetEntity = User.class,cascade = CascadeType.ALL)
        @JoinColumn(name = "user_id",referencedColumnName = "id")*//*

        @ManyToOne
        @MapsId
        @JoinColumn(name = "userId")
        private Long userId ;


        */
/*@OneToOne(targetEntity = Product.class,cascade = CascadeType.ALL)
        @JoinColumn(name = "product_id",referencedColumnName = "id")*//*

        */
/*@OneToOne
        @MapsId
        @JoinColumn(name = "productId")
        private Product productId ;

        @OneToOne(targetEntity = Guichet.class,cascade = CascadeType.ALL)
        @JoinColumn(name = "guichet_id",referencedColumnName = "id")
        private Guichet guichetId ;*//*

        @Column(name = "localite_agent")
        private String localiteAgent;


    }
*/
