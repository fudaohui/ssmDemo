package com.fdh.demo.service.impl;

import com.fdh.demo.dao.PersonMapperDao;
import com.fdh.demo.entity.Person;
import com.fdh.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonMapperDao personMapperDao;
    public Person findPersonById(long id) {
        return personMapperDao.findPersonById(id);
    }
}