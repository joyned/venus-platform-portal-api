package com.venus.platform.web.mapper;

import com.venus.platform.core.entity.Person;
import com.venus.platform.web.dto.PersonDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonDTO toDTO(Person person);
    Person toEntity(PersonDTO personDTO);
    List<PersonDTO> toDTO(List<Person> person);
    List<Person> toEntity(List<Person> person);
}
