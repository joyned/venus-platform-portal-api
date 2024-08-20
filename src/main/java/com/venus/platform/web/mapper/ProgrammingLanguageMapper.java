package com.venus.platform.web.mapper;

import com.venus.platform.core.entity.ProgrammingLanguage;
import com.venus.platform.web.dto.ProgrammingLanguageDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ProgrammingLanguageMapper {

    ProgrammingLanguage toEntity(ProgrammingLanguageDTO programmingLanguage);
    ProgrammingLanguageDTO toDTO(ProgrammingLanguage programmingLanguage);
    List<ProgrammingLanguage> toEntity(List<ProgrammingLanguageDTO> programmingLanguage);
    List<ProgrammingLanguageDTO> toDTO(List<ProgrammingLanguage> programmingLanguage);
}
