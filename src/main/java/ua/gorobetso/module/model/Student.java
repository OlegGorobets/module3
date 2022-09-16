package ua.gorobetso.module.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Student extends Person {
    @Column(name = "date_of_admission")
    private Date dateOfAdmission;
}
