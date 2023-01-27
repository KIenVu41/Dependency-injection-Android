package com.vts.di.data.mapper;

import com.vts.di.data.entity.UserEntity;
import com.vts.di.model.User;
import com.vts.di.model.builder.UserBuilder;
import com.vts.di.model.builder.UserConcreteBuilder;

public class UserEntity2UserModel implements Mapper<UserEntity, User>{

    @Override
    public User map(UserEntity input) {
        UserBuilder userConcreteBuilder = new UserConcreteBuilder()
                .setId(input.getId())
                .setName(input.getName())
                .setUsername(input.getUsername())
                .setEmail(input.getEmail())
                .setPhone(input.getPhone())
                .setWebsite(input.getWebsite());

        return userConcreteBuilder.build();
    }
}
