package ai.ecma.codingbat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ai.ecma.codingbat.entity.template.AbsTitleIntegerEntity;
import ai.ecma.codingbat.util.CommonUtils;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"title"}))
@NoArgsConstructor
public final class Language extends AbsTitleIntegerEntity {

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

    private void setUrl() {
        this.url = CommonUtils.makeUrl(super.getTitle());
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
        setUrl();
    }
}
