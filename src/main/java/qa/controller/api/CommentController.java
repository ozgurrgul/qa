package qa.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import qa.dto.comment.CommentCreateDTO;
import qa.dto.post.PostCreateDTO;
import qa.service.CommentService;
import qa.service.PostService;
import qa.utils.ControllerUtils;

import javax.validation.Valid;

/**
 * Created by ozgur on 7/29/17.
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public Object create(@Valid @RequestBody CommentCreateDTO commentCreateDTO, Authentication a) {
        return commentService.create(commentCreateDTO, ControllerUtils.getUser(a));
    }

}
