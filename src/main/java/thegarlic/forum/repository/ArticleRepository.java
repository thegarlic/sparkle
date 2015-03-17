package thegarlic.forum.repository;

import org.springframework.data.repository.CrudRepository;
import thegarlic.forum.domain.Article;

public interface ArticleRepository extends CrudRepository<Article, Long>{
}
