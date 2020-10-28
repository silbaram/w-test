package com.we.assignment.crawling.service.impl;

import com.we.assignment.crawling.service.RemoveSpecificCharactersService;
import org.springframework.stereotype.Service;


@Service
public class RemoveSpecificCharactersTextServiceImpl implements RemoveSpecificCharactersService {

    @Override
    public String removeSpecificCharacters(String text) {
        return text.replaceAll("[<>]", "");
    }
}
