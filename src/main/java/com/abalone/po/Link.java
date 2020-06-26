package com.abalone.po;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: gavy
 * CreateTime: 2020-06-26-09-22
 */
@Entity
@Table(name = "t_link")
public class Link {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private String link;
    private String image;
    @Temporal(TemporalType.TIMESTAMP)
    private Date create_time;
    private String email;
    private Integer success;

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", image='" + image + '\'' +
                ", create_time=" + create_time +
                ", email='" + email + '\'' +
                ", success=" + success +
                '}';
    }

    public Link(){
    }

    public Link(String name, String description, String link, String image, Date create_time, String email, Integer success) {
        this.name = name;
        this.description = description;
        this.link = link;
        this.image = image;
        this.create_time = create_time;
        this.email = email;
        this.success = success;
    }

    public Link(Long id, String name, String description, String link, String image, Date create_time, String email, Integer success) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.link = link;
        this.image = image;
        this.create_time = create_time;
        this.email = email;
        this.success = success;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }
}
