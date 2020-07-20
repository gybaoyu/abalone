package com.abalone.web.admin;


import com.abalone.po.Message;
import com.abalone.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminMessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/messages")
    public String messages(Model model){
        List<Message> messages = messageService.sortMessage();
        model.addAttribute("messages",messages);
        return "/admin/messages";
    }

    @GetMapping("/messages/{id}/delete")
    public String delete(@PathVariable Long id, Model model) {
        messageService.deleteMessage(id);
        model.addAttribute("message", "删除成功");
        return "redirect:/admin/messages";
    }


}
