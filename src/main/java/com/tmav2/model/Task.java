package com.tmav2.model;

import jakarta.persistence.*;
import lombok.*;
//
@Entity
@Table(name = "Tasks")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String TaskTitle;
    private String TaskDescription;
    private int TaskPriority;
}
