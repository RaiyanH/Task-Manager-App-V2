package com.tmav2.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

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
    private String taskStatus;
    private String taskTitle;
    private String taskDescription;
    private int taskPriority;
    private LocalDate taskDeadline;
//    @ManyToOne
//    private User taskAssignee;
}
