package com.spring.boot.dao;


import com.spring.boot.model.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PersonMapper {
    
    int insert(Person person);

    int count();

    Person selectByName(@Param("name") String name);

}
