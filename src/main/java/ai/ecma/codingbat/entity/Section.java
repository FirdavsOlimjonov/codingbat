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
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;
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
        setTitle(title);
    }


    public Section(String title, String url, Integer id) {
        this.url = url;
        setTitle(title);
        this.setId(id);
    }

    public Section(String title, String url, Language language) {
        this.url = url;
        setTitle(title);
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

    public void setTitle(String title) {
        this.title = title;
        setUrl(title);

    }
}
