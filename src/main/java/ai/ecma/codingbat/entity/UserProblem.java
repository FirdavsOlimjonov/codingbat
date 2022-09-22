package ai.ecma.codingbat.entity;

import lombok.Getter;
import lombok.Setter;
import ai.ecma.codingbat.entity.template.AbsLongEntity;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(
        columnNames = {"user_id", "problem_id"}))
public class UserProblem extends AbsLongEntity {

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Problem problem;

    @Column(columnDefinition = "text")
    private String solution;

    @Column(nullable = false)
    private Boolean solved;

}