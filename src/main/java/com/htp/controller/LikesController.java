package com.htp.controller;

import com.htp.controller.requests.likes.LikesCreateRequest;
import com.htp.controller.requests.users.UserCreateRequest;
import com.htp.entity.likes.Likes;
import com.htp.entity.users.Users;
import com.htp.service.likes.LikesService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/likes")
@CrossOrigin(origins = {"http://localhost:4200"})
public class LikesController {

    private final LikesService likesService;
    private final ConversionService conversionService;

    @PostMapping("/addlike")
    @Transactional(rollbackFor = {Exception.class})
    public ResponseEntity<?> createNewLike(@RequestBody @Valid LikesCreateRequest request) {
        likesService.addLikeForThisItemFromCurrentUser(conversionService.convert(request, Likes.class));
        return ResponseEntity.ok("Like was successfully created");
    }

    @DeleteMapping("/deletelike")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> deleteUser(@RequestParam Long id, @RequestHeader("Authorization") String authHeader) {
        likesService.deleteLikeForThisItemFromCurrentUser(id, authHeader);
        return ResponseEntity.ok("Like was deleted");
    }

    @GetMapping("/ishavelike")
    public ResponseEntity<?> isUserOwnerOrAdmin(@RequestParam Long id, @RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(likesService.isItemHasLikeFromCurrentUser(id, authHeader));
    }
}
