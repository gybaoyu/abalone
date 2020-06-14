package com.abalone.service;

import com.abalone.dao.MessageRepository;
import com.abalone.po.Comment;
import com.abalone.po.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author: gavy
 * CreateTime: 2020-06-13-09-33
 */
@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message isReply(int reply) {
        Message message = messageRepository.findByReply(reply);
        return message;
    }

    @Override
    public List<Message> sortMessage() {
        List<Message> messages = messageRepository.findAll();
        return messages;
    }

    @Override
    @Transactional
    public Message getMessage(Long id) {
        return messageRepository.findOne(id);
    }

    @Override
    @Transactional
    public void deleteMessage(Long id) {
        messageRepository.delete(id);
    }
}
