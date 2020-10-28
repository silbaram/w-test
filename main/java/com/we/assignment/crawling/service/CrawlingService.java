package com.we.assignment.crawling.service;

import java.util.Optional;

public interface CrawlingService {

    Optional<String> getTargetUrlHtml(String url);
}
