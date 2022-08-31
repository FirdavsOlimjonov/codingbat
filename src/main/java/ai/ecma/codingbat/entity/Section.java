package ai.ecma.codingbat.entity;

import lombok.Getter;
import lombok.Setter;
import ai.ecma.codingbat.entity.template.AbsTitleIntegerEntity;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"title", "language_id"}),
        @UniqueConstraint(columnNames = {"url", "language_id"}),
}
)
public class Section extends AbsTitleIntegerEntity {

    @Column(nullable = false)
    private String url;

    @Column(nullable = false, columnDefinition = "text")
    private String description;

    @Column(nullable = false)
    private Byte maxRate;

    @ManyToOne(optional = false)
    private Language language;

}
