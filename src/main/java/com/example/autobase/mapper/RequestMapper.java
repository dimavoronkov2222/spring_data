package com.example.autobase.mapper;
import com.example.autobase.dto.RequestDTO;
import com.example.autobase.model.Request;
import org.springframework.stereotype.Component;
@Component
public class RequestMapper {
    public Request toEntity(RequestDTO requestDTO) {
        Request request = new Request();
        request.setId(requestDTO.getId());
        request.setDestination(requestDTO.getDestination());
        request.setWeight(requestDTO.getWeight());
        request.setCargoType(requestDTO.getCargoType());
        request.setCompleted(requestDTO.isCompleted());
        return request;
    }
    public RequestDTO toDTO(Request request) {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setId(request.getId());
        requestDTO.setDestination(request.getDestination());
        requestDTO.setWeight(request.getWeight());
        requestDTO.setCargoType(request.getCargoType());
        requestDTO.setCompleted(request.isCompleted());
        return requestDTO;
    }
}