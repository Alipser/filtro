package riwi.filtro.domain.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
// import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
// import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
// import lombok.ToString;
import riwi.filtro.utils.enums.StatusEnum;

@Entity(name = "surveys")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 255, nullable = false)
    private String title;
    @Column(nullable = true, columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private LocalDateTime creationDate;
    private StatusEnum status;

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private User creator;

}
