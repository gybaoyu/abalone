package com.abalone.web;

import com.abalone.service.BiliService;
import com.abalone.service.BlogService;
import com.abalone.service.TagService;
import com.abalone.service.TypeService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private BiliService biliService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("types", typeService.listTypeTop(6));
        model.addAttribute("tags", tagService.listTagTop(10));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(8));
        /*一下是b站提供的api*/
        String json1 = biliService.readJSON("https://api.bilibili.com/x/space/upstat?mid=411498228");//这个有likes,view
        String json2 = biliService.readJSON("https://api.bilibili.com/x/relation/stat?vmid=411498228&jsonp=jsonp");//这个有follower
        JSONObject jo1 = JSONObject.fromObject(json1);
        JSONObject jo2 = JSONObject.fromObject(json2);
        //检测是否可以获得value值(自己去打印看看)
        String view = jo1.getJSONObject("data").getJSONObject("archive").get("view").toString();
        String likes = jo1.getJSONObject("data").get("likes").toString();
        String follower = jo2.getJSONObject("data").get("follower").toString();
        request.setAttribute("view",view);
        request.setAttribute("likes",likes);
        request.setAttribute("follower",follower);
        return "index";
    }

    @RequestMapping("/mobile/index")
    public String mobileIndex(@PageableDefault(size = 999999, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                              Model model) {
        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("types", typeService.listTypeTop(6));
        model.addAttribute("tags", tagService.listTagTop(10));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(8));
        return "mobile/index";
    }

    @GetMapping("mobile/mobile/blog/{id}")
    public String mobileBlog(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.getAndConvert(id));
        return "mobile/blog";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.getAndConvert(id));
        return "blog";
    }

    @GetMapping("/footer/newblog")
    public String newblogs(Model model) {
        model.addAttribute("newblogs", blogService.listRecommendBlogTop(3));
        return "_fragments :: newblogList";
    }
}
