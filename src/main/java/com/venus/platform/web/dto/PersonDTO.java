package com.venus.platform.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private Long id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
    private Boolean active;
    private LocalDateTime createdAt;
    private TeamDTO team;
    private RoleDTO role;
}
