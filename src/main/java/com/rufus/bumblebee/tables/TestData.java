package com.rufus.bumblebee.tables;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "test_data", schema = "repository")
@Getter
@Setter
@ToString
public class TestData implements Serializable {

    @Id
    @SequenceGenerator(name = "row_id", sequenceName = "repository.row_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "row_id")
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "value")
    private String value;

    @Column(name = "container_ref")
    private Long containerRef;

}
