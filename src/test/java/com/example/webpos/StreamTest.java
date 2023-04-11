package com.example.webpos;

import lombok.ToString;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class StreamTest {
    static class Comment{
        private Long id;

        public Long getId() {
            return id;
        }

        public Comment(Long id) {
            this.id = id;
        }
    }

    static class CommentLikes{
        private Long commentId;

        public Long getCommentId() {
            return commentId;
        }

        public CommentLikes(Long commentId) {
            this.commentId = commentId;
        }
    }

    @ToString
    static class CommentResponse{
        private Long id;
        private boolean isLike;

        public void setLike(boolean val) {
            this.isLike = val;
        }

        public Long getId() {
            return id;
        }

        public boolean isLike() {
            return isLike;
        }

        public CommentResponse(Long id) {
            this.id = id;
        }
    }

    @Test
    void 다중_STREAM() {
        List<Comment> comments = List.of(new Comment(1L), new Comment(2L), new Comment(10L));
        List<CommentLikes> commentLikes = List.of(new CommentLikes(2L), new CommentLikes(3L));

        List<CommentResponse> commentResponses = comments.stream().map(comment -> new CommentResponse(comment.getId())).toList();

        commentResponses.stream().filter(commentResponse -> commentLikes.stream().anyMatch(commentLikes1 -> commentResponse.getId().equals(commentLikes1.getCommentId()))).forEach(commentResponse -> commentResponse.setLike(true));

        for (CommentResponse commentRespons : commentResponses) {
            System.out.println(commentRespons);
        }
    }

    @Test
    void List_삭제() {

        Comment comment1 = new Comment(1L);
        Comment comment2 = new Comment(2L);
        Comment comment3 = new Comment(3L);

        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment1);
        commentList.add(comment2);
        commentList.add(comment3);

        System.out.println(commentList);

        commentList.remove(comment2);

        System.out.println(commentList);

    }
}
