package com.facts.catfacts.models;


import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @SequenceGenerator(
            name="rating_sequence",
            sequenceName = "rating_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rating_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    private Long factId;

    private Long count;


}
