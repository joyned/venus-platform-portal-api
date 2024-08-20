package com.venus.platform.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectVersionDTO {
    private Long id;
    private String name;
    private String releaseNotes;
    private LocalDateTime releaseDate;
    private Long projectId;
}
