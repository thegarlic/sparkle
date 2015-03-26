package thegarlic.forum.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import thegarlic.forum.domain.Article;
import thegarlic.forum.domain.Board;

public interface ArticleRepository extends JpaRepository<Article, Long> {
	Page<Article> findByBoard(Board board, Pageable pageable);
	Page<Article> findByBoardBoardName(String boardName, Pageable pageable);
}

