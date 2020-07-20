package com.abalone.web;

import com.abalone.dao.MessageRepository;
import com.abalone.po.Message;
import com.abalone.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Iterator;
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

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public String messagePage(Model model,
                              @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Page<Message> messagePage = messageService.getMessageList(pageNum, pageSize);
        model.addAttribute("messagePage", messagePage);
        return "message";
    }

    @RequestMapping(value = "/addMessage", method = RequestMethod.POST)
    public String getMessage(@RequestParam String ask_text,
                             @RequestParam String username,
                             @RequestParam String email) {
        if (!ask_text.equals("") && !username.equals("")) {
            Message message = new Message(username, email, ask_text, new Date(), 0);
            messageRepository.save(message);
        }
        return "redirect:/message";
    }
}
