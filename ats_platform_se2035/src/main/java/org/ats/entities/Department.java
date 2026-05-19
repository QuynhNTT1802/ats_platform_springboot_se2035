package org.ats.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(indexes = {@Index(name = "UNIX_NAME", columnList = "department_name")},
        name = "departments")
@NoArgsConstructor@AllArgsConstructor
@Setter@Getter
@ToString
public class Department extends  BaseEntity{

    @Id
    @Column(name = "dept_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "department_name", unique = true, nullable = false, columnDefinition = "VARCHAR(255)")
    private String departmentName;

    private String description;

    @OneToMany(mappedBy = "department")
    private Set<Job> jobs;
}
