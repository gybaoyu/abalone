package com.abalone.service;

import com.abalone.po.Blog;
import com.abalone.po.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author: gavy
 * CreateTime: 2020-06-13-09-33
 */
public interface MessageService {
    Message isReply(int reply);
    List<Message> sortMessage();
    Message getMessage(Long id);
    void deleteMessage(Long id);
    Page<Message> getMessageList(int pageNum, int pageSize);
}
