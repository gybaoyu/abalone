package com.abalone.web;

import com.abalone.po.Link;
import com.abalone.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: gavy
 * CreateTime: 2020-06-26-09-50
 */
@Controller
public class LinkController {
    @Autowired
    private LinkService linkService;

    @RequestMapping("/link")
    public String link(Model model) {
        List<Link>links = linkService.showLinks();
        model.addAttribute("links",links);
        return "link";
    }

    @RequestMapping(value = "/addLink", method = RequestMethod.POST)
    public String addLink(@RequestParam String name,
                          @RequestParam String href,
                          @RequestParam String image,
                          @RequestParam String description,
                          @RequestParam String email) {
        if (!name.equals("") && !href.equals("") && !image.equals("")) {
            Link link = new Link(name, description, href, image, email, 0);
            linkService.addLink(link);
            return "success";
        } else {
            return "redirect:link";
        }
    }
}
