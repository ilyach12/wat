package com.chaikouski.model.ad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Characteristics {

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long id;
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ad ad;
    private String color;
    private double engineVolume;
    private int countOfCylinders;
    private String yearOfIssue;
}
