package ua.gorobetso.module.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Grade {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @ToString.Exclude
    private Student student;
    @OneToOne
    @JoinColumn(name = "subject_code", referencedColumnName = "code")
    private Subject subject;
    int value;
}
