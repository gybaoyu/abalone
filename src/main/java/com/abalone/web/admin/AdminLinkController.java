package com.abalone.web.admin;

import com.abalone.po.Link;
import com.abalone.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @Author: gavy
 * CreateTime: 2020-06-26-13-18
 */
@Controller
@RequestMapping("/admin")
public class AdminLinkController {

    @Autowired
    private LinkService linkService;

    @RequestMapping("/links")
    public String link(Model model){
        List<Link>links =  linkService.showLinks();
        model.addAttribute("links",links);
        return "admin/links";
    }

    @GetMapping("/links/{id}/success")
    public String success(@PathVariable Long id, RedirectAttributes attributes){
        linkService.examine(linkService.getLinkById(id));
        attributes.addAttribute("message","恭喜，操作成功！");
        return "redirect:/admin/links";
    }

    @GetMapping("/links/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        linkService.deleteLink(id);
        attributes.addAttribute("message","删除成功!");
        return "redirect:/admin/links";
    }
}
