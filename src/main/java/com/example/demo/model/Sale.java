package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Sale")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(name = "purchase_item")
    private Purchase purchase_item;

    @Column(name = "count")
    private int count;

    @Column(name = "amount")
    private float amount;


    @Column(name = "purchase_date")
    private Date purchase_date;

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", purchase_item=" + purchase_item +
                ", count=" + count +
                ", amount=" + amount +
                ", purchase_date=" + purchase_date +
                '}';
    }
}
