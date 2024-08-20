package com.venus.platform.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private Long id;
    private String name;
    private String description;
    private ProgrammingLanguageDTO programmingLanguage;
    private TeamDTO team;
    private PersonDTO createdBy;
    private String repositoryUrl;
    private String projectUrl;
    private Boolean active;
    private LocalDateTime createdAt;
}
