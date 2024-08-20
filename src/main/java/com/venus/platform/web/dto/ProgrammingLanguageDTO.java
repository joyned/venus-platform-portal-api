package com.venus.platform.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgrammingLanguageDTO {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Boolean active;
    @NotNull
    private String templateLink;
    private LocalDateTime createdAt;
}
