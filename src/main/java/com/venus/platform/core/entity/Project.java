package com.venus.platform.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "programming_language_id")
    private ProgrammingLanguage programmingLanguage;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Person createdBy;

    @Column(name = "repository_url", nullable = false)
    private String repositoryUrl;

    @Column(name = "project_url", nullable = false)
    private String projectUrl;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "project")
    private List<ProjectScope> scopes;

    @OneToMany(mappedBy = "project")
    private List<ProjectVersion> versions;

    @OneToMany(mappedBy = "project")
    private List<ApprovalWorkflow> workflows;
}

