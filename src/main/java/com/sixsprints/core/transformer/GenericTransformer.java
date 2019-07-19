
package com.sixsprints.core.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.sixsprints.core.dto.PageDto;

public abstract class GenericTransformer<ENTITY, DTO> {

  public abstract DTO toDto(ENTITY entity);

  public abstract ENTITY toDomain(DTO dto);

  public List<DTO> toDto(List<ENTITY> entities) {
    List<DTO> dtos = new ArrayList<>();
    for (ENTITY entity : entities) {
      DTO dto = toDto(entity);
      if (dto != null) {
        dtos.add(dto);
      }
    }
    return dtos;
  }

  public PageDto<DTO> pageEntityToPageDtoDto(Page<ENTITY> page) {
    PageDto<DTO> pageDto = pageAnyToPageDtoDto(page);
    pageDto.setContent(toDto(page.getContent()));
    return pageDto;
  }

  public PageDto<DTO> pageDtoToPageDtoDto(Page<DTO> page) {
    PageDto<DTO> pageDto = pageAnyToPageDtoDto(page);
    pageDto.setContent(page.getContent());
    return pageDto;
  }

  private PageDto<DTO> pageAnyToPageDtoDto(Page<?> page) {
    PageDto<DTO> pageDto = new PageDto<>();
    pageDto.setCurrentPageSize(page.getNumberOfElements());
    pageDto.setCurrentPage(page.getNumber());
    pageDto.setTotalElements(page.getTotalElements());
    pageDto.setTotalPages(page.getTotalPages());
    return pageDto;
  }
}