package com.example.customocrservice.mapper;


import com.example.customocrservice.domain.Template;
import com.example.customocrservice.model.request.TemplateRequestDto;
import com.example.customocrservice.model.response.template.TemplateResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TemplateMapper {
    Template mapToDomain(TemplateRequestDto templateRequestDto);
    TemplateResponseDto mapToDto(Template template);
}
