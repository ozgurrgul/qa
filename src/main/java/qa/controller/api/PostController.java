package qa.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import qa.dto.auth.AuthLoginDTO;
import qa.dto.post.PostCreateDTO;
import qa.dto.post.PostUpdateDTO;
import qa.service.PostService;
import qa.utils.ControllerUtils;

import javax.validation.Valid;

/**
 * Created by ozgur on 7/29/17.
 */
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public Object create(@Valid @RequestBody PostCreateDTO postCreateDTO, Authentication a) {
        return postService.create(postCreateDTO, ControllerUtils.getUser(a));
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Object update(@Valid @RequestBody PostUpdateDTO postCreateDTO, Authentication a) {
        return postService.update(postCreateDTO, ControllerUtils.getUser(a));
    }

}
