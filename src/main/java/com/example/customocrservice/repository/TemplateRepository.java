package com.example.customocrservice.repository;

import com.example.customocrservice.domain.Template;

import java.util.List;

public interface TemplateRepository {
    Template save(Template template);

    Template findById(String id);
    List<Template> findAll();
}
