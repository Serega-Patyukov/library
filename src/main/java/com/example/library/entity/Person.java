package com.example.library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(unique = true)
    @Size(min = 3, message = "минимум 3 символа.")
    private String name;
    @Min(value = 1900, message = "год рождения должен быть больше 1900")
    private Integer year;
    @OneToMany(fetch = FetchType.EAGER,
            mappedBy = "person",
            cascade = CascadeType.ALL)
    private List<Book> bookList = new ArrayList<>();
}
