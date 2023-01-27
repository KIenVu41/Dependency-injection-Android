package com.vts.di.data.mapper;

import com.vts.di.data.entity.UserEntity;
import com.vts.di.model.User;

public class UserModel2UserEntity implements Mapper<User, UserEntity>{

    @Override
    public UserEntity map(User input) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(input.getId());
        userEntity.setUsername(input.getUsername());
        userEntity.setName(input.getName());
        userEntity.setEmail(input.getEmail());
        userEntity.setPhone(input.getPhone());
        userEntity.setWebsite(input.getWebsite());
        return userEntity;
    }
}
