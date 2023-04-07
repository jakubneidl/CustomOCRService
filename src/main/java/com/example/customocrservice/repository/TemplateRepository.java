package com.example.customocrservice.repository;

import com.example.customocrservice.domain.Template;

public interface TemplateRepository {
    Template save(Template template);

    Template findById(String id);
}
