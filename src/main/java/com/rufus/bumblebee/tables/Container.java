package com.rufus.bumblebee.tables;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "containers", schema = "repository")
public class Container {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @SequenceGenerator(name = "container_id", sequenceName = "container_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "container_id")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CREATION_DATE")
    private Date date;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "DATA_REF")
    private List<StringTableBufer> data;

}
