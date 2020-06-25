package com.abalone.web;

import com.abalone.dao.MessageRepository;
import com.abalone.po.Message;
import com.abalone.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * @Author: gavy
 * CreateTime: 2020-06-13-09-36
 */
@Controller
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private MessageRepository messageRepository;

    @RequestMapping(value = "/message",method = RequestMethod.GET)
    public String messagePage(Model model){
        List<Message>messages = messageService.sortMessage();
        model.addAttribute("messages",messages);
        return "message";
    }

    @RequestMapping(value = "/addMessage",method = RequestMethod.POST)
    public String getMessage(@RequestParam String ask_text,
                             @RequestParam String username,
                             @RequestParam String email){
        Message message = new Message(username,email,ask_text,new Date(),0);
        messageRepository.save(message);
        return "redirect:/message";
    }
}
