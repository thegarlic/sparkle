package thegarlic.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import thegarlic.forum.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
