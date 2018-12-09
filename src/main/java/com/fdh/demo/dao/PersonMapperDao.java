package com.fdh.demo.dao;

import com.fdh.demo.entity.Person;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface PersonMapperDao {
    Person findPersonById(long id);
}