package com.microservice.bookdetailsservice.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String publisher;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy ="publisher")
    private List<Book> books=new ArrayList<>();




}
