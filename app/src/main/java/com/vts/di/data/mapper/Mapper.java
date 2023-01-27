package com.vts.di.data.mapper;

public interface Mapper<I, O> {
    O map(I input);
}
