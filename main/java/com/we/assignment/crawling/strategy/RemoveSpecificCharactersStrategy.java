package com.we.assignment.crawling.strategy;

import com.we.assignment.crawling.code.HtmlReplaceTypeEnum;
import com.we.assignment.crawling.service.RemoveSpecificCharactersService;
import com.we.assignment.crawling.service.impl.RemoveSpecificCharactersHtmlServiceImpl;
import com.we.assignment.crawling.service.impl.RemoveSpecificCharactersSpecialServiceImpl;
import com.we.assignment.crawling.service.impl.RemoveSpecificCharactersTextServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class RemoveSpecificCharactersStrategy {

    @Qualifier("removeSpecificCharactersHtmlServiceImpl")
    @Autowired
    private RemoveSpecificCharactersHtmlServiceImpl removeSpecificCharactersHtmlService;

    @Qualifier("removeSpecificCharactersTextServiceImpl")
    @Autowired
    private RemoveSpecificCharactersTextServiceImpl removeSpecificCharactersTextService;

    @Qualifier("removeSpecificCharactersSpecialServiceImpl")
    @Autowired
    private RemoveSpecificCharactersSpecialServiceImpl removeSpecificCharactersSpecialService;


    public RemoveSpecificCharactersService resolve(String  type) {
        if (HtmlReplaceTypeEnum.HTML.name().equals(type)) {

            return removeSpecificCharactersHtmlService;
        } else if (HtmlReplaceTypeEnum.TEXT.name().equals(type)) {

            return removeSpecificCharactersTextService;
        } else if (HtmlReplaceTypeEnum.SPECIAL.name().equals(type)) {

            return removeSpecificCharactersSpecialService;
        }

        return null;
    }
}
