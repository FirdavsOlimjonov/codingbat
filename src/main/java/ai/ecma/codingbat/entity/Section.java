package ai.ecma.codingbat.entity;

import ai.ecma.codingbat.util.CommonUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ai.ecma.codingbat.entity.template.AbsTitleIntegerEntity;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
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
    private Integer maxRate;

    @ManyToOne(optional = false)
    private Language language;

    public Section(String title, String url) {
        this.url = url;
        super.setTitle(title);
    }


    public Section(String title, String url, Integer id) {
        this.url = url;
        super.setTitle(title);
        this.setId(id);
    }

    public Section(String title, String url, Language language) {
        this.url = url;
        super.setTitle(title);
        this.language = language;
    }

    public Section(String title, String description, Integer maxRate, Integer id, Language language) {
        setTitle(title);
        setUrl(title);
        this.description = description;
        this.maxRate = maxRate;
        this.setId(id);
        this.language = language;

    }

    public void setUrl(String title) {
        this.url = CommonUtils.makeUrl(title);
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
        setUrl(title);

    }
}
