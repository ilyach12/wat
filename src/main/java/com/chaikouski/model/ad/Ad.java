package com.chaikouski.model.ad;

import com.chaikouski.model.user.Owner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Ad {

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long id;
    @ManyToOne
    private Owner owner;
    @OneToOne
    private Mark mark;
    @OneToOne
    private Model model;
    @Column(length = 1000)
    private String description;
    private String date;
    private String image;
    private int views;
}
