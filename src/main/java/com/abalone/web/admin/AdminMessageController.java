package com.abalone.web.admin;


import com.abalone.po.Message;
import com.abalone.service.MailService;
import com.abalone.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminMessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private MailService mailService;

    @GetMapping("/messages")
    public String messages(Model model){
        List<Message> messages = messageService.sortMessage();
        model.addAttribute("messages",messages);
        return "/admin/messages";
    }

    @GetMapping("/messages/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes model) {
        messageService.deleteMessage(id);
        model.addAttribute("message", "删除成功");
        return "redirect:/admin/messages";
    }

    @GetMapping("/messages/{id}/answer")
    public String answer(@PathVariable Long id, Model model){
        Message message = messageService.getMessage(id);
        model.addAttribute("userMessage",message);
        return "/admin/answer";
    }

    /**
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @param model 单纯的model
     * @return 单纯的跳转页面而已啦
     */
    @PostMapping("/answer")
    public String mailAnswer(@RequestParam String to,
                             @RequestParam String subject,
                             @RequestParam String content,
                             RedirectAttributes model){
        mailService.sendMimeMessage(to, subject, content);
        model.addAttribute("message", "删除成功");
        return "redirect:/admin/messages";
    }
}
