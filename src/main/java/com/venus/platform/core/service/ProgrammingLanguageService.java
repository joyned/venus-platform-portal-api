package com.venus.platform.core.service;

import com.venus.platform.core.entity.ProgrammingLanguage;

import java.util.List;

public interface ProgrammingLanguageService {
    List<ProgrammingLanguage> findAll();
    ProgrammingLanguage findById(Long id);
    ProgrammingLanguage save(ProgrammingLanguage programmingLanguage);
}
