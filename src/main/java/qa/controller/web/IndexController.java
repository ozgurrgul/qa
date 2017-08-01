package qa.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import qa.service.PostService;

/**
 * Created by ozgur on 7/28/17.
 */

@Controller
public class IndexController {

    @Autowired
    PostService postService;

    @RequestMapping("/")
    public String test(Model model, Pageable pageable) {
        model.addAttribute("title", "Anasayfa");
        model.addAttribute("posts", postService.listIndexPosts(pageable));
        return "index";
    }

    @RequestMapping("/p/{postId}")
    public String post(Model model, @PathVariable("postId") String postId) {
        model.addAttribute("title", "Single");
        model.addAttribute("post", postService.getPostById(postId));
        return "post";
    }

}
