package com.example.customocrservice.service;

import com.example.customocrservice.domain.Template;
import com.example.customocrservice.mapper.TemplateMapper;
import com.example.customocrservice.model.request.TemplateRequestDto;
import com.example.customocrservice.model.response.template.TemplateResponseDto;
import com.example.customocrservice.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TemplateService {

    private final TemplateRepository templateRepository;
    private final TemplateMapper templateMapper;

    public TemplateResponseDto save(TemplateRequestDto templateRequestDto) {
        Template template = templateMapper.mapToDomain(templateRequestDto);
        return templateMapper.mapToDto(templateRepository.save(template));
    }

    public TemplateResponseDto findTemplate(String id) {
        return templateMapper.mapToDto(templateRepository.findById(id));
    }

    public List<TemplateResponseDto> findAll() {
        return templateRepository.findAll().stream()
                .map(templateMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
