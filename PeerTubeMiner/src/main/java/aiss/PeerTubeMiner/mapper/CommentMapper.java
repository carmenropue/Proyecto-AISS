package aiss.PeerTubeMiner.mapper;

import aiss.PeerTubeMiner.model.peerTube.Comment;
import aiss.PeerTubeMiner.model.videominer.VMComment;

public class CommentMapper {
    public static VMComment toVMComment(Comment comment) {

        VMComment vm = new VMComment();

        vm.setId(String.valueOf(comment.getId()));
        vm.setText(comment.getText());
        vm.setCreatedOn(comment.getCreatedAt());

        return vm;
    }
}
