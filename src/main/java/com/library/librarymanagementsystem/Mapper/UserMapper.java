package com.library.librarymanagementsystem.Mapper;

import com.library.librarymanagementsystem.Dto.UserDto;
import com.library.librarymanagementsystem.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDto userToUserDto(User user);


}
