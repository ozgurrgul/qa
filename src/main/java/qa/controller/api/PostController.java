package qa.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import qa.dto.post.*;
import qa.service.PostService;
import qa.service.PostVoteService;
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

    @Autowired
    PostVoteService postVoteService;

    //@PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public Object create(@Valid @RequestBody PostCreateDTO postCreateDTO, Authentication a) {
        return postService.create(postCreateDTO, ControllerUtils.getUser(a));
    }

    //@PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Object update(@Valid @RequestBody PostUpdateDTO postCreateDTO, Authentication a) {
        return postService.update(postCreateDTO, ControllerUtils.getUser(a));
    }


    //@PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "answer", method = RequestMethod.POST)
    public Object answer(@Valid @RequestBody PostAnswerDTO postAnswerDTO, Authentication a) {
        return postService.answer(postAnswerDTO, ControllerUtils.getUser(a));
    }

    //@PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "vote", method = RequestMethod.POST)
    public Object vote(@Valid @RequestBody PostVoteDTO postVoteDTO, Authentication a) {
        return postVoteService.vote(postVoteDTO, ControllerUtils.getUser(a));
    }

    //@PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "comment", method = RequestMethod.POST)
    public Object create(@Valid @RequestBody CommentCreateDTO commentCreateDTO, Authentication a) {
        return postService.comment(commentCreateDTO, ControllerUtils.getUser(a));
    }

    //@PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "accept", method = RequestMethod.POST)
    public Object create(@Valid @RequestBody AcceptAnswerDTO acceptAnswerDTO, Authentication a) {
        return postService.accept(acceptAnswerDTO, ControllerUtils.getUser(a));
    }

    // To web controller
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Object list() {
        return postService.list();
    }

    // To web controller
    @RequestMapping(value = "revisions", method = RequestMethod.GET)
    public Object revisions(@RequestParam("basePostId") Long basePostId) {
        return postService.revisions(basePostId);
    }

}
