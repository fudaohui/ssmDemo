package com.fdh.demo.controller;

import com.fdh.demo.kafka.Consumer;
import com.fdh.demo.kafka.Producer;
import com.fdh.demo.service.PersonService;
import com.fdh.demo.utils.KafkaSendMsgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;
//    @Autowired
//    private Producer producer;
//
//    @Autowired
//    private Consumer consumer;

//    @RequestMapping("/selectPerson")
//    @ResponseBody
//    public Person selectPerson(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//        request.setCharacterEncoding("utf-8");
//        response.setCharacterEncoding("utf-8");
//
//        long personId = Long.parseLong(request.getParameter("id"));
//        Person person = personService.findPersonById(personId);
//        return person;
//    }

    @RequestMapping("/pruducer")
    public void pruducer(HttpServletRequest request, HttpServletResponse response) throws IOException {

        for (int i = 0; i < 100; i++) {
            KafkaSendMsgUtils.sendMessage("test1",i+"","hello kafka");
        }

    }

    @RequestMapping("/customer")
    public void customer(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        new Thread(consumer).start();
    }
}
