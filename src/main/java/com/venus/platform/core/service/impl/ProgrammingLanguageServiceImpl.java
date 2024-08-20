package com.venus.platform.core.service.impl;

import com.venus.platform.core.entity.ProgrammingLanguage;
import com.venus.platform.core.repository.ProgrammingLanguageRepository;
import com.venus.platform.core.service.ProgrammingLanguageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProgrammingLanguageServiceImpl implements ProgrammingLanguageService {

    private final ProgrammingLanguageRepository programmingLanguageRepository;

    @Override
    public List<ProgrammingLanguage> findAll() {
        return programmingLanguageRepository.findAll();
    }

    @Override
    public ProgrammingLanguage findById(Long id) {
        return programmingLanguageRepository.findById(id).orElse(null);
    }

    @Override
    public ProgrammingLanguage save(ProgrammingLanguage programmingLanguage) {
        if (Objects.isNull(programmingLanguage.getId()) || programmingLanguage.getId() == 0) {
            programmingLanguage.setCreatedAt(LocalDateTime.now());
        }
        return programmingLanguageRepository.save(programmingLanguage);
    }
}
