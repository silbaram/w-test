package com.we.assignment.crawling.service.impl;

import com.we.assignment.common.util.CommonUtil;
import com.we.assignment.crawling.service.CrawlingService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;


@Service
public class CrawlingServiceImpl implements CrawlingService {

    /**
     * HTML 문서 내용을 가져오기
     * @param urlStr HTML 문서 내용을 가져오기 위한 url
     * @return HTML 문서
     */
    @Override
    public Optional<String> getTargetUrlHtml(String urlStr) {

        try {
            Connection conn = Jsoup.connect(CommonUtil.encodeUrl(urlStr));

            Document document = conn.get();

            return Optional.of(document.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
