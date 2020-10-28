package com.we.assignment.crawling.service.impl;

import com.we.assignment.crawling.service.RemoveSpecificCharactersService;
import org.springframework.stereotype.Service;


@Service
public class RemoveSpecificCharactersSpecialServiceImpl implements RemoveSpecificCharactersService {

    @Override
    public String removeSpecificCharacters(String text) {
        return text.replaceAll("[^0-9a-zA-Z\\s]|\\p{Z}", "");
    }
}
