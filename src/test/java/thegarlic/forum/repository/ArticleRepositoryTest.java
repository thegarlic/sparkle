package thegarlic.forum.repository;


import com.google.common.collect.Iterators;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import thegarlic.forum.Application;
import thegarlic.forum.domain.Article;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ArticleRepositoryTest {
    @Autowired
    ArticleRepository articleRepository;

    List<Article> dummyArticles;

    @Test
    public void 게시물_추가_삭제_테스트() {
        articleRepository.deleteAll();
        assertThat(Iterators.size(articleRepository.findAll().iterator()), is(0));

        articleRepository.save(dummyArticles.get(0));
        articleRepository.save(dummyArticles.get(1));

        assertThat(Iterators.size(articleRepository.findAll().iterator()), is(2));
    }


    @Test
    public void 게시물_읽기_수정_테스트() {
        Article article = dummyArticles.get(1);
        articleRepository.save(article);

        //TODO JPA일 경우 테스트는 어떻게 하는지 확인할 것.
        //솔직히 테스트가 무의미해 보이는데 이걸 테스트하는것은 옳은 일인가??
        assertThat(article, is(articleRepository.findOne(article.getId())));

        article.setText("changedText");
        article.setWriteDate(new Date());
        article.setAuthor("changedAuthor");
        article.setTitle("changedTitle");

        articleRepository.save(article);
        Article target = articleRepository.findOne(article.getId());
        assertThat(target.getAuthor(), is(article.getAuthor()));
        assertThat(target.getTitle(), is(article.getTitle()));
        assertThat(target.getWriteDate(), isAlmostSameDate(article.getWriteDate()));
    }

    //database에서 저장될 경우, format이나 1000ms이하의 시간은 삭제된 채로 저장됨.
    //때문에 아래와 같이 매쳐를 신규로 지정. 가독성을 높임.
    public static org.hamcrest.Matcher<Date> isAlmostSameDate(final Date input) {
        return new TypeSafeMatcher<Date>() {
            @Override
            public void describeTo(Description description) {
                description.appendText(input.toString());
            }

            @Override
            protected boolean matchesSafely(Date value) {
                long result = value.getTime() - input.getTime();
                if (result < 0) result *= 1;

                if (result < 1000) return true;
                else return false;
            }
        };
    }


    @Before
    public void setDummyArticles() {
        dummyArticles = new ArrayList<Article>();

        dummyArticles.add(new Article(new Date(), "writer0", "title0", "text0"));
        dummyArticles.add(new Article(new Date(), "writer1", "title1", "text1"));
        dummyArticles.add(new Article(new Date(), "writer2", "title2", "text2"));
        dummyArticles.add(new Article(new Date(), "writer3", "title3", "text3"));
        dummyArticles.add(new Article(new Date(), "writer4", "title4", "text4"));
    }


}