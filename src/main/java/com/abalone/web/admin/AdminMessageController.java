package com.abalone.web.admin;


import com.abalone.po.Blog;
import com.abalone.po.Message;
import com.abalone.service.BlogService;
import com.abalone.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/admin")
public class AdminMessageController {

    static String picture;
    static String htmlHead;
    static String htmlFoot;
    static String htmlAll;

    @Autowired
    private BlogService blogService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private JavaMailSender sender;

    @GetMapping("/messages")
    public String messages(Model model) {
        List<Message> messages = messageService.sortMessage();
        model.addAttribute("messages", messages);
        return "admin/messages";
    }

    @GetMapping("/messages/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes model) {
        messageService.deleteMessage(id);
        model.addAttribute("message", "删除成功");
        return "redirect:admin/messages";
    }

    @GetMapping("/messages/{id}/answer")
    public String answer(@PathVariable Long id, Model model) {
        Message message = messageService.getMessage(id);
        message.setReply(1);
        model.addAttribute("userMessage", message);
        return "admin/answer";
    }

    /**
     * @param to      收件人
     * @param content 内容
     * @param model   单纯的model
     * @return 单纯的跳转页面而已啦
     */
    @PostMapping("/answer")
    public String mailAnswer(@RequestParam String to,
                             @RequestParam Long userId,
                             @RequestParam String content,
                             RedirectAttributes model) throws MessagingException {
        List<Blog> blogs = blogService.getAll();
        Random random = new Random();
        Message mge = messageService.getMessage(userId);
        picture = blogs.get(random.nextInt(blogs.size())).getFirstPicture();
        htmlHead = "<img width=\"60%\" src=\"" + picture + "\">\n" +
                "<h2>您好,首先感谢您在Abalone上留言,以下是您在Abalone的留言信息</h2><br/>" +
                mge.getUsername() + "留言: <br/>" + mge.getAsk_text() +
                "<br/>博主回复: <br/>" + content;
        htmlFoot = "<h4>感谢您对本站的关注,常来Abalone看看哦(*^_^*)</h4>";
        htmlAll = htmlHead + content + htmlFoot;
        MimeMessage msg = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
        helper.setTo(to);
        helper.setFrom("958324611@qq.com");
        helper.setText(htmlAll, true);
        helper.setSubject("来自Abalone的一则回复消息");//邮件标题
        sender.send(msg);
        model.addFlashAttribute("message", "回复成功");
        return "redirect:/admin/messages";
    }
}
