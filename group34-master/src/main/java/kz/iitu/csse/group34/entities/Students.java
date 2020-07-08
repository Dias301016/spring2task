package kz.iitu.csse.group34.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_students")
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;;

    @Column(name = "yearOfAddmission")
    private int yearOfAddmission;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Groups> groups;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Courses> courses;
}