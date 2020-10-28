package com.we.assignment.crawling;

import com.we.assignment.crawling.service.CrawlingService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CrawlingServiceTest {

    @Autowired
    public CrawlingService crawlingService;


    @Test
    public void html_가져오기_파라미터없음() {
        Optional<String> htmlString = crawlingService.getTargetUrlHtml("https://www.google.com");

        Assert.assertNotEquals("", htmlString.orElse(""));
    }

    @Test
    public void html_가져오기_파라미터있음() {
        Optional<String> htmlString = crawlingService.getTargetUrlHtml("https://www.google.com/search?q=사람인");
        System.out.println("htmlString : " + htmlString.orElse(""));
        Assert.assertNotEquals("", htmlString.orElse(""));
    }
}
