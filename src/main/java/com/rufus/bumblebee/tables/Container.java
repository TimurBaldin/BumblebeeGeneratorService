package com.rufus.bumblebee.tables;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CONTAINERS", schema = "REPOSITORY")
@Getter
@Setter
@ToString
public class Container {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @SequenceGenerator(name = "container_id", sequenceName = "repository.container_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "container_id")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CREATION_DATE")
    private Date date;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CONTAINER_REF")
    private List<TestData> data;

    @Column(name = "CLIENT_REF")
    private Long clientRef;

    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CONTAINER_REF")
    private List<Report> reports;

}
