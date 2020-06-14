package com.abalone.po;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: gavy
 * CreateTime: 2020-06-13-09-24
 */
@Entity
@Table(name = "t_message")
public class Message {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String email;
    private String ask_text;
    private String answer_text;
    @Temporal(TemporalType.TIMESTAMP)
    private Date ask_time;
    @Temporal(TemporalType.TIMESTAMP)
    private Date answer_time;
    private Integer reply;

    public Message(){

    }

    public Message(String username,String email, String ask_text, Date ask_time, Integer reply) {
        this.username = username;
        this.email = email;
        this.ask_text = ask_text;
        this.ask_time = ask_time;
        this.reply = reply;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAsk_text() {
        return ask_text;
    }

    public void setAsk_text(String ask_text) {
        this.ask_text = ask_text;
    }

    public String getAnswer_text() {
        return answer_text;
    }

    public void setAnswer_text(String answer_text) {
        this.answer_text = answer_text;
    }

    public Date getAsk_time() {
        return ask_time;
    }

    public void setAsk_time(Date ask_time) {
        this.ask_time = ask_time;
    }

    public Date getAnswer_time() {
        return answer_time;
    }

    public void setAnswer_time(Date answer_time) {
        this.answer_time = answer_time;
    }

    public Integer getReply() {
        return reply;
    }

    public void setReply(Integer reply) {
        this.reply = reply;
    }

    public Message(Long id, String username, String ask_text, String answer_text, Date ask_time, Date answer_time, Integer reply) {
        this.id = id;
        this.username = username;
        this.ask_text = ask_text;
        this.answer_text = answer_text;
        this.ask_time = ask_time;
        this.answer_time = answer_time;
        this.reply = reply;
    }
}
