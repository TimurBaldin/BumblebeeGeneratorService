package com.rufus.bumblebee.tables;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TEST_DATA", schema = "REPOSITORY")
@Getter
@Setter
@ToString
public class TestData implements Serializable {

    @Id
    @SequenceGenerator(name = "row_id", sequenceName = "repository.row_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "row_id")
    @Column(name = "ID", unique = true, nullable = false)
    private int id;

    @Column(name = "VALUE")
    private String value;

    @Column(name = "CONTAINER_REF")
    private Long containerRef;

}
