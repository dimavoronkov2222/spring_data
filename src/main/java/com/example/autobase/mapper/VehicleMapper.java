package com.example.autobase.mapper;
import com.example.autobase.dto.VehicleDTO;
import com.example.autobase.model.Vehicle;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface VehicleMapper {
    VehicleDTO toDto(Vehicle vehicle);
    Vehicle toEntity(VehicleDTO vehicleDTO);
}