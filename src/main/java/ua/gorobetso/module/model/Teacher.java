package ua.gorobetso.module.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Teacher extends Person {
    @OneToOne
    @JoinColumn(name = "subject_code", referencedColumnName = "code")
    private Subject subject;
}
