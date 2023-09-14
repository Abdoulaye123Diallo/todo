package sn.ept.git.seminaire.cicd.models;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import sn.ept.git.seminaire.cicd.utils.SizeMapping;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@EqualsAndHashCode (callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "acicd_tags")
@DynamicUpdate
public final class Tag extends BaseEntity implements Serializable {

    @NotBlank
    @Size(min = SizeMapping.Name.MIN, max = SizeMapping.Name.MAX)
    @Column(unique = true)
    private String name;


    @Size(min = SizeMapping.Description.MIN, max = SizeMapping.Description.MAX)
    private String description;


    @ToString.Exclude
    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private Set<Todo> todos = new HashSet<>();
}