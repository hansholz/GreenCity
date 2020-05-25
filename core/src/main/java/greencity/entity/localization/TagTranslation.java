package greencity.entity.localization;

import greencity.entity.Language;
import greencity.entity.Tag;
import javax.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tags_translations")
public class TagTranslation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Language language;

    @Column(nullable = false, unique = true, length = 20)
    private String name;

    @ManyToOne
    private Tag tag;
}
