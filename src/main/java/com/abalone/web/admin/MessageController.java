package com.abalone.web.admin;

import com.abalone.po.Tag;
import com.abalone.service.MessageService;
import com.abalone.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



//@Controller
//@RequestMapping("/admin")
public class MessageController {

//    @Autowired
//    private MessageService messageService;



//    @GetMapping("/messages/answer")
//    public String answer(Model model) {
//        model.addAttribute("message", new Message());
//        return "admin/messages-input";
//    }
//
//    @GetMapping("/messages/{id}/input")
//    public String editInput(@PathVariable Long id, Model model) {
//        model.addAttribute("tag", messageService.getMessage(id));
//        return "admin/messages-input";
//    }



//    @GetMapping("/messages/{id}/delete")
//    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
//        messageService.deleteMessage(id);
//        attributes.addFlashAttribute("message", "删除成功");
//        return "redirect:/admin/messages";
//    }


}
