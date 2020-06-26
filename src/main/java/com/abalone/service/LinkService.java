package com.abalone.service;

import com.abalone.po.Link;

import java.util.List;

/**
 * @Author: gavy
 * CreateTime: 2020-06-26-09-44
 */
public interface LinkService {
    List<Link>showLinks();
    //添加到暂存区(暂时未审核的友链)
    void addLink(Link link);
    //审核友链
    void examine(Link link);
    List<Link> getAllNotSucceedLink();
    //通过id查找link
    Link getLinkById(Long id);
    //删除Link
    void deleteLink(Long id);
}
