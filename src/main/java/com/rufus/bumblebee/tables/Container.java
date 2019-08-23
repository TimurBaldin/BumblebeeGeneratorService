package com.rufus.bumblebee.tables;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CONTAINERS", schema = "REPOSITORY")
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Container{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", data=" + data +
                ", clientRef=" + clientRef +
                ", updateDate=" + updateDate +
                '}';
    }

}
