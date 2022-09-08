package ai.ecma.codingbat.entity;

import ai.ecma.codingbat.util.CommonUtils;
import lombok.*;
import ai.ecma.codingbat.entity.template.AbsTitleIntegerEntity;
import org.springframework.boot.CommandLineRunner;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
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


    private void setUrl(String title) {
        this.url = CommonUtils.makeUrl(title);
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
        setUrl(title);

    }
}
