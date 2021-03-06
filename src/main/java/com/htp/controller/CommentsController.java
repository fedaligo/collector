package com.htp.controller;

import com.htp.controller.convert.comments.CommentsCreateRequestConverter;
import com.htp.controller.requests.comments.CommentsCreateRequest;
import com.htp.entity.comments.Comments;
import com.htp.service.comments.CommentsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/comments")
@CrossOrigin(origins = {"*"})
public class CommentsController {

    private final CommentsService commentsService;
    private final CommentsCreateRequestConverter converter;

    @GetMapping("/allcomments")
    public List<Comments> getAllComments() {
        return commentsService.findAllComments();
    }

    @GetMapping("/getcommentsbyitemid")
    public List<Comments> getCommentsById(@RequestParam Long itemId) {
        return commentsService.findCommentsByItemId(itemId);
    }

    @GetMapping("/getusersnamesbyitem")
    public List<String> getCommentsUsersNamesByItemId(@RequestParam Long itemId) {
        return commentsService.findCommentsUsersNamesByItemId(itemId);
    }

    @PostMapping("/addcomment")
    @Transactional(rollbackFor = {Exception.class})
    public ResponseEntity<?> createNewComment(@RequestBody @Valid CommentsCreateRequest request) {
        commentsService.addCommentForThisItemFromCurrentUser(converter.convert(request));
        return ResponseEntity.ok("Comment was successfully created");
    }

    @DeleteMapping("/deletecomment")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> deleteUser(@RequestParam Long id) {
        commentsService.deleteCommentForThisItemFromCurrentUser(id);
        return ResponseEntity.ok("Comment was deleted");
    }
}
