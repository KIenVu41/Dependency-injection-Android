package com.vts.di.model.builder;

import com.vts.di.model.User;

public interface UserBuilder {
    UserBuilder setId(int id);
    UserBuilder setName(String name);
    UserBuilder setUsername(String username);
    UserBuilder setEmail(String email);
    UserBuilder setPhone(String phone);
    UserBuilder setWebsite(String website);
    User build();
}
