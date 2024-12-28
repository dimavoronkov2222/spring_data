package com.example.autobase.mapper;
import com.example.autobase.dto.RequestDTO;
import com.example.autobase.model.Request;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface RequestMapper {
    RequestDTO toDto(Request request);
    Request toEntity(RequestDTO requestDTO);
}