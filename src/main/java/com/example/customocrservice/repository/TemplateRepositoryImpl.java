package com.example.customocrservice.repository;

import com.example.customocrservice.domain.Template;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Repository
public class TemplateRepositoryImpl implements TemplateRepository {

    private final HashMap<UUID, Template> templateHashMap = new HashMap<>();

    @Override
    public Template save(Template template) {
        template.setId(UUID.randomUUID());
        templateHashMap.put(template.getId(), template);
        return template;
    }

    @Override
    public Template findById(String id) {
        return templateHashMap.get(UUID.fromString(id));
    }

    @Override
    public List<Template> findAll() {
        return new ArrayList<>(templateHashMap.values());
    }
}
