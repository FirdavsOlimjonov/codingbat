package ai.ecma.codingbat.entity;

import lombok.Getter;
import lombok.Setter;
import ai.ecma.codingbat.entity.template.AbsLongEntity;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(
        columnNames = {"user_id", "problem_id"}))
public class UserProblem extends AbsLongEntity {
    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Problem problem;

    @Column(columnDefinition = "text")
    private String solution;

    @Column(nullable = false)
    private Boolean solved;

}