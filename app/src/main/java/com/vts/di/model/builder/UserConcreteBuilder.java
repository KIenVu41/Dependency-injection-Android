package com.vts.di.model.builder;

import com.vts.di.model.User;

public class UserConcreteBuilder implements UserBuilder {
    private int id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;

    @Override
    public UserBuilder setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public UserBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public UserBuilder setWebsite(String website) {
        this.website = website;
        return this;
    }

    @Override
    public User build() {
        return new User(id, name, username, email, phone, website);
    }
}
