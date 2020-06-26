package com.abalone.service;

import com.abalone.dao.LinkRepository;
import com.abalone.po.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: gavy
 * CreateTime: 2020-06-26-09-46
 */

@Service
public class LinkServiceImpl implements LinkService {
    @Autowired
    private LinkRepository linkRepository;

    @Override
    public List<Link> showLinks() {
        //获取所有友链
        return linkRepository.findAll();
    }

    @Override
    public void addLink(Link link) {
        if (link!=null){
            link.setSuccess(0);//默认为0,还未审核
            link.setCreate_time(new Date());//设置createTime
            linkRepository.save(link);
        }
    }

    @Override
    public void examine(Link link) {
        link.setSuccess(1);//默认为0,设置为1表示审核通过
        linkRepository.save(link);
    }

    @Override
    public List<Link> getAllNotSucceedLink() {
        List<Link>links = linkRepository.findAllBySuccess(0);
        return links;
    }

    @Override
    public Link getLinkById(Long id) {
        return linkRepository.findById(id);
    }

    @Override
    public void deleteLink(Long id) {
        linkRepository.delete(id);
    }
}
