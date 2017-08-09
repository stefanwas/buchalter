package com.stefan.buchalter.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/status")
public class StatusController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatusController.class);

    @RequestMapping(method= RequestMethod.GET)
    public String status() {
        LOGGER.info("Status OK.");
        return "OK!";
    }

}
