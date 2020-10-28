package com.we.assignment.strategy;

import com.we.assignment.crawling.code.HtmlReplaceTypeEnum;
import com.we.assignment.crawling.strategy.RemoveSpecificCharactersStrategy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RemoveSpecificCharactersStrategyTest {

    @Autowired
    private RemoveSpecificCharactersStrategy removeSpecificCharactersStrategy;


    @Test
    public void html_삭제_테스트() {
        String removeText = removeSpecificCharactersStrategy.resolve(HtmlReplaceTypeEnum.HTML.name()).removeSpecificCharacters("<html><head><head></body><가나다>가나다라<span>마바</span></body></html>");

        Assert.assertEquals(removeText, "가나다라마바");
    }

    @Test
    public void text_삭제_테스트() {
        String removeText = removeSpecificCharactersStrategy.resolve(HtmlReplaceTypeEnum.TEXT.name()).removeSpecificCharacters("가나다 <a>마바사</a>");

        Assert.assertEquals(removeText, "가나다 a마바사/a");
    }

    @Test
    public void 영문_숫자_이외_삭제_테스트() {
        String removeText = removeSpecificCharactersStrategy.resolve(HtmlReplaceTypeEnum.SPECIAL.name()).removeSpecificCharacters("12233sff 가나다 <a>마바사!!@#$%^&*(12345)_[-]</a>");

        Assert.assertEquals(removeText, "12233sffa12345a");
    }
}
