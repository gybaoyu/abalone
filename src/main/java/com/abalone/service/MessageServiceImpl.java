package com.abalone.service;

import com.abalone.dao.MessageRepository;
import org.springframework.data.domain.PageRequest;
import com.abalone.po.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Page<Message> getMessageList(int pageNum, int pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        PageRequest request = new PageRequest(pageNum,pageSize,sort);
        Pageable pageable = request;
        Page<Message> messages = messageRepository.findAll(pageable);

        return messages;
    }
}
