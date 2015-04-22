package thegarlic.forum.service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import thegarlic.forum.domain.Article;
import thegarlic.forum.domain.Board;
import thegarlic.forum.dto.ArticlePageView;
import thegarlic.forum.dto.view.ArticleDto;
import thegarlic.forum.dto.view.BoardDto;
import thegarlic.forum.exception.DefaultException;
import thegarlic.forum.repository.ArticleRepository;
import thegarlic.forum.repository.BoardRepository;

@Slf4j
@Service
public class BoardServiceImpl extends BaseServiceImpl {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private BoardRepository boardRepository;
    
    private Board getBoard(String boardName) {
        Board board = boardRepository.findByName(boardName);

        if (board == null) {
            log.debug("{} 게시판 조회 실패! exception 발생", boardName);
            throw new DefaultException(String.format("게시판을 찾을 수 없습니다. [boardName : %s]", boardName), HttpStatus.NOT_FOUND);
        }

        return board;
    }
    
    private Article getArticle(Long articleId, Board board) {
        Article article = articleRepository.findByIdAndBoard(articleId, board);

        if (article == null)
            throw new DefaultException(String.format("게시글을 찾을 수 없습니다. [ID : %d]", articleId), HttpStatus.NOT_FOUND);

        return article;
    }
    
    private Article getArticleWithIncreaseReadCount(Long articleId, Board board) {
        Article article = this.getArticle(articleId, board);
        article.setReadCount(article.getReadCount() + 1);

        return articleRepository.save(article);
    }
    
    public ArticlePageView getBoardPage(String boardName, PageRequest pageRequest) {
        
        ModelMapper mapper = new ModelMapper();
        
        mapper.addConverter(new Converter<DateTime, String>() {
            @Override
            public String convert(MappingContext<DateTime, String> context) {
                return context.getSource() == null ? "" : context.getSource().withZone(DateTimeZone.UTC).toString();
            }
        });
        
        mapper.addMappings(new PropertyMap<Article, ArticleDto>() {
            @Override
            protected void configure() {
                skip().setComments(null);
                skip().setText(null);
                map().setCommentCount(source.getComments().size());
            }
        });
        
        Board board = this.getBoard(boardName);
        
        Page<Article> articles = articleRepository.findByBoard(board, pageRequest);
        
        BoardDto boardDto = mapper.map(board, BoardDto.class);

        List<ArticleDto> articleDtoList = articles.getContent().stream()
            .map(article -> {
                return mapper.map(article, ArticleDto.class);
            })
            .collect(Collectors.toList());
        
        return new ArticlePageView(boardDto, new PageImpl<>(articleDtoList, pageRequest, articles.getTotalElements()));
    }
    
    public ArticleDto getArticleDto(String boardName, Long articleId) {

        ModelMapper mapper = new ModelMapper();

        mapper.addConverter(new Converter<DateTime, String>() {
            @Override
            public String convert(MappingContext<DateTime, String> context) {
                return context.getSource() == null ? "" : context.getSource().withZone(DateTimeZone.UTC).toString();
            }
        });

        Board board = this.getBoard(boardName);
        Article article = this.getArticleWithIncreaseReadCount(articleId, board);
                
        return mapper.map(article, ArticleDto.class);
    }

    public ArticleDto saveArticle(String boardName, Article param) {

        ModelMapper mapper = new ModelMapper();

        mapper.addConverter(new Converter<DateTime, String>() {
            @Override
            public String convert(MappingContext<DateTime, String> context) {
                return context.getSource() == null ? "" : context.getSource().withZone(DateTimeZone.UTC).toString();
            }
        });
        
        Board board = this.getBoard(boardName);
        param.setBoard(board);
        Article article = articleRepository.save(param);
        
        return mapper.map(article, ArticleDto.class);
    }
    
    public ArticleDto modifyArticle(String boardName, Long articleId, Article param) {

        ModelMapper mapper = new ModelMapper();

        mapper.addConverter(new Converter<DateTime, String>() {
            @Override
            public String convert(MappingContext<DateTime, String> context) {
                return context.getSource() == null ? "" : context.getSource().withZone(DateTimeZone.UTC).toString();
            }
        });
        
        Board board = getBoard(boardName);
        Article article = getArticle(articleId, board);

        article.setAuthor(param.getAuthor());
        article.setTitle(param.getTitle());
        article.setText(param.getText());

        article = articleRepository.save(article);
        
        return mapper.map(article, ArticleDto.class);
    }
    
    
}
