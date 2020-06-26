package com.abalone.web;

import com.abalone.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: gavy
 * CreateTime: 2020-06-26-09-50
 */
@Controller
public class LinkController {
    @Autowired
    private LinkService linkService;

    @RequestMapping("/link")
    public String link(){
        return "link";
    }
}
