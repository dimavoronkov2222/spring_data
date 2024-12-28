package com.example.autobase.mapper;
import com.example.autobase.dto.DriverDTO;
import com.example.autobase.model.Driver;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface DriverMapper {
    DriverDTO toDto(Driver driver);
    Driver toEntity(DriverDTO driverDTO);
}