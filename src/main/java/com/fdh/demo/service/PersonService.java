package com.fdh.demo.service;

import com.fdh.demo.entity.Person;

public interface PersonService {
    Person findPersonById(long id);
}