package ai.ecma.codingbat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ai.ecma.codingbat.entity.template.AbsTitleIntegerEntity;
import ai.ecma.codingbat.util.CommonUtils;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"title"}))
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public final class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(unique = true, nullable = false)
    private String url;

    @JsonIgnore
    @OneToMany(mappedBy = "language")
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    private List<Section> sections;

    public Language(String title) {
        setTitle(title);
    }

    public Language(String title, String url) {
        setTitle(title);
        this.url = url;
    }

    public Language(String title, String url, Integer id) {
        this(title, url);
        setId(id);
    }


    private void setUrl() {
        this.url = CommonUtils.makeUrl(getTitle());
    }


    public void setTitle(String title) {
        this.title = title;
        setUrl();
    }
}
