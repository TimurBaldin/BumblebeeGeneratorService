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
    private List<TestData> data;

    @Column(name = "CLIENT_REF")
    private Long clientRef;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<TestData> getData() {
        return data;
    }

    public void setData(List<TestData> data) {
        this.data = data;
    }

    public Long getClientRef() {
        return clientRef;
    }

    public void setClientRef(Long clientRef) {
        this.clientRef = clientRef;
    }

}
