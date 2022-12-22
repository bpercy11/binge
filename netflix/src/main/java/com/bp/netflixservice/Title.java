package com.bp.netflixservice;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "titles")
public class Title {
    @Id
    @SequenceGenerator(
            name = "title_id_sequence",
            sequenceName = "title_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "title_id_sequence"
    )
    private Long id;

    @Column(unique = true)
    private String title;
    private String titleType;
    private int year;
    private String platform;
    private LocalDateTime createdAt;

}
