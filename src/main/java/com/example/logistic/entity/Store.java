package com.example.logistic.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "store")
@Getter
@Setter
@AllArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String store_name;
    private double fullness;
    private double currentf;
    public Store(){};
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "store")
    private List<Good> goods;

    public String getCurrentFreePlace()
    {
        return String.format("%.2f", this.fullness - this.currentf);
    }
}
