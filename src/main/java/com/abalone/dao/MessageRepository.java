package com.abalone.dao;

import com.abalone.po.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * @Author: gavy
 * CreateTime: 2020-06-13-09-31
 */

public interface MessageRepository extends JpaRepository<Message,Long> {

    Message findByReply(int reply);
}
