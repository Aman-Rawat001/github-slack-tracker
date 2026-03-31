package com.Infohedron.assessment.entity;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class CommitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private String commitId;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
