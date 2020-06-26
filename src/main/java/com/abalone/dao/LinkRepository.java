package com.abalone.dao;

import com.abalone.po.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * @Author: gavy
 * CreateTime: 2020-06-26-09-40
 */
public interface LinkRepository extends JpaRepository<Link,Long> {
    List<Link> findAllBySuccess(int success);
    Link findById(Long id);
}
