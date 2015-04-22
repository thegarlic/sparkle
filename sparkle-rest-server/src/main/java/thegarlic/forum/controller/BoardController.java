package thegarlic.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import thegarlic.forum.Const;
import thegarlic.forum.domain.Article;
import thegarlic.forum.dto.ArticlePageView;
import thegarlic.forum.dto.Response;
import thegarlic.forum.dto.view.ArticleDto;
import thegarlic.forum.service.BoardServiceImpl;

@RestController
@RequestMapping("/boards/{boardName}/articles")
public class BoardController {

    @Autowired
    private BoardServiceImpl boardService;

    @RequestMapping(value = "/page/{pageNumber}", method = RequestMethod.GET)
    public ResponseEntity<?> getArticles(
            @PathVariable("boardName") String boardName,
            @PathVariable("pageNumber") int pageNumber,
            @RequestParam(value = "sort.order", defaultValue = "writeDate") String sortOrder,
            @RequestParam(value = "sort.direction", defaultValue = "DESC") Direction sortDirection,
            @RequestParam(value = "page.size", defaultValue = Const.ELEMENT_SIZE_PER_PAGE) int pageSize) {

        PageRequest req = boardService.createPageReauest(pageNumber, pageSize, new Sort.Order(sortDirection, sortOrder));
        ArticlePageView articlePageView = boardService.getBoardPage(boardName, req);

        return Response.of(articlePageView);
    }

    @RequestMapping(value = "/{articleId}", method = RequestMethod.GET)
    public ResponseEntity<?> readArticle(
            @PathVariable("boardName") String boardName,
            @PathVariable("articleId") Long articleId) {

        ArticleDto articleDto = boardService.getArticleDto(boardName, articleId);
        return Response.of(articleDto);
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public ResponseEntity<?> writeArticle(
            @PathVariable("boardName") String boardName,
            @RequestBody Article param) {
        
        ArticleDto articleDto = boardService.saveArticle(boardName, param);
        return Response.of(articleDto, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{articleId}", method = RequestMethod.PUT)
    public ResponseEntity<?> modifyArticle(
            @PathVariable("boardName") String boardName,
            @PathVariable("articleId") Long articleId,
            @RequestBody Article param) {
        
        ArticleDto articleDto = boardService.modifyArticle(boardName, articleId, param);
        return Response.of(articleDto, HttpStatus.OK);
    }
}
