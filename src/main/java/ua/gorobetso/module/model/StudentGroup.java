package ua.gorobetso.module.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student_group")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class StudentGroup {
    @Id
    private String title;
    @OneToMany
    @JoinColumn(name = "student_group_title", referencedColumnName = "title")
    @ToString.Exclude
    private List<Student> students;
}
