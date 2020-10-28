package com.we.assignment.crawling.service.impl;

import com.we.assignment.crawling.service.RemoveSpecificCharactersService;
import org.springframework.stereotype.Service;


@Service
public class RemoveSpecificCharactersHtmlServiceImpl implements RemoveSpecificCharactersService {

    @Override
    public String removeSpecificCharacters(String text) {
        return text.replaceAll("<(/)?([a-zA-Z\uAC00-\uD7A3]*)(\\s[a-zA-Z\uAC00-\uD7A3]*=[^>]*)?(\\s)*(/)?>", "");
    }
}
