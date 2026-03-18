package at.fhv.userservice.presentation.ui.mapper;

import at.fhv.userservice.domain.model.user.User;
import at.fhv.userservice.presentation.ui.dto.UserRequestDTO;
import at.fhv.userservice.presentation.ui.dto.UserResponseDTO;

public class UserMapper {

    public static User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return user;
    }

    public static UserResponseDTO toDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }
}