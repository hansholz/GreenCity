package greencity.entity;

import greencity.entity.localization.TagTranslation;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tags")
@ToString(exclude = "ecoNews")
@EqualsAndHashCode(exclude = "ecoNews")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<TagTranslation> translations;

    @ManyToMany(mappedBy = "tags")
    private List<EcoNews> ecoNews;
}
