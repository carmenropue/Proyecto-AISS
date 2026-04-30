package aiss.peertubeminer.mapper;

import aiss.peertubeminer.model.peertube.Comment;
import aiss.peertubeminer.model.videominer.VMComment;

public class CommentMapper {
    public static VMComment toVMComment(Comment comment) {

        VMComment vm = new VMComment();

        vm.setId(String.valueOf(comment.getId()));
        vm.setText(comment.getText());
        vm.setCreatedOn(comment.getCreatedAt());

        return vm;
    }
}
