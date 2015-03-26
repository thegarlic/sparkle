package thegarlic.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import thegarlic.forum.Const;
import thegarlic.forum.domain.Article;
import thegarlic.forum.domain.Board;
import thegarlic.forum.exception.DefaultException;
import thegarlic.forum.repository.ArticleRepository;
import thegarlic.forum.repository.BoardRepository;

@RestController
@RequestMapping("{boardName}")
public class BoardController {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getArticles (
			@PathVariable("boardName") String boardName, 
			@RequestParam(value = "p", defaultValue = "0") int page,
			@RequestParam(value = "s", defaultValue = Const.ELEMENT_SIZE_PER_PAGE) int size) {
		
		Board board = boardRepository.findByBoardName(boardName);
		if(board == null) {
			throw new DefaultException(String.format("[%s] 게시판을 찾을 수 없습니다.", boardName));
		}
		
		page = page == 0 ? page : page - 1;
		
		PageRequest pageRequest = new PageRequest(page, size);
		
		Page<Article> articles = articleRepository.findByBoard(board, pageRequest);
		return new ResponseEntity<>(articles, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addBoard(@PathVariable("boardName") String boardName) {
		Board board = boardRepository.save(new Board(boardName));
		return new ResponseEntity<>(board, HttpStatus.CREATED);
	}
}
