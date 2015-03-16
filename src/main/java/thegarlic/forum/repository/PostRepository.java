package thegarlic.forum.repository;

import org.springframework.data.repository.CrudRepository;
import thegarlic.forum.domain.Post;

public interface PostRepository extends CrudRepository<Post, Long>{
}
