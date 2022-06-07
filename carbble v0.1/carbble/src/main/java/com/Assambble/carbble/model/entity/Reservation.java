package com.Assambble.carbble.model.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "reservation_tbl")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30, nullable = false)
    private String username;

    @Column(length = 50, nullable = false)
    private String car;

    @Column(nullable = false)
    private Date startdate;

    @Column(nullable = false)
    private Date enddate;

    @Column(length = 1, nullable = false)
    private Integer timerange;

    @Column(length = 1, nullable = false)
    private Integer purpose;

    @Column(name = "purpose_detail",columnDefinition = "TEXT", nullable = false)
    private String purposeDetail;


    public Integer getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }



    public String getCar() {
        return car;
    }



    public Date getStartdate() {
        return startdate;
    }



    public Date getEnddate() {
        return enddate;
    }



    public Integer getTimerange() {
        return timerange;
    }



    public Integer getPurpose() {
        return purpose;
    }


    public String getPurposeDetail() {
        return purposeDetail;
    }



    public Reservation(Integer id, String username, String car,
                       Date startdate, Date enddate, int timerange,
                       int purpose, String purposeDetail){

        this.id = id;
        this.username = username;
        this.car = car;
        this.startdate = startdate;
        this.enddate = enddate;
        this.timerange = timerange;
        this.purpose = purpose;
        this.purposeDetail = purposeDetail;
    }

}
