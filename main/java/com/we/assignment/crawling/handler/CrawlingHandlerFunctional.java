package com.we.assignment.crawling.handler;

import com.we.assignment.common.util.CommonUtil;
import com.we.assignment.crawling.code.HtmlReplaceTypeEnum;
import com.we.assignment.crawling.code.SortTypeEnum;
import com.we.assignment.crawling.dto.CrawlingDto;
import com.we.assignment.crawling.service.impl.CrawlingServiceImpl;
import com.we.assignment.crawling.strategy.RemoveSpecificCharactersStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Optional;


@Component
public class CrawlingHandlerFunctional {

    @Autowired
    private CrawlingServiceImpl crawlingService;

    @Autowired
    private RemoveSpecificCharactersStrategy removeSpecificCharactersStrategy;


    /**
     *
     * @param serverRequest
     * @return
     */
    public Mono<ServerResponse> crawlingConvertedHandler(ServerRequest serverRequest) {

        CrawlingDto crawlingDtoMono = new CrawlingDto();
        if (serverRequest.queryParam("crawlingTargetUrl").isPresent()) {
            crawlingDtoMono.setCrawlingTargetUrl(serverRequest.queryParam("crawlingTargetUrl").get());
        } else {
            return ServerResponse.status(HttpStatus.BAD_REQUEST).body(BodyInserters.fromValue("error"));
        }
        if (serverRequest.queryParam("tagReplaceType").isPresent()) {
            crawlingDtoMono.setTagReplaceType(serverRequest.queryParam("tagReplaceType").get());
        } else {
            return ServerResponse.status(HttpStatus.BAD_REQUEST).body(BodyInserters.fromValue("error"));
        }
        if (serverRequest.queryParam("bundleUnit").isPresent()) {
            crawlingDtoMono.setBundleUnit(Integer.parseInt(serverRequest.queryParam("bundleUnit").get()));
        } else {
            return ServerResponse.status(HttpStatus.BAD_REQUEST).body(BodyInserters.fromValue("error"));
        }

        String removeText = null; // 테그또는 특정 기호 삭제 후 문자열

        // 1. 타겟에서 HTML 가져오기
        Optional<String> htmlTextOptional =  crawlingService.getTargetUrlHtml(crawlingDtoMono.getCrawlingTargetUrl());

        if (htmlTextOptional.isPresent()) {
            // 2. tag 제거 또는 text 가져오기
            removeText = removeSpecificCharactersStrategy.resolve(crawlingDtoMono.getTagReplaceType()).removeSpecificCharacters(htmlTextOptional.get());

            // 3. 특수문자 전체 삭제
            removeText = CommonUtil.removeSpecificCharacters(HtmlReplaceTypeEnum.SPECIAL.name(), removeText);

            // 4.1. 영문 오름차순
            String sortString = CommonUtil.textOrderByAsc(SortTypeEnum.CHAR.name(), removeText);

            // 4.2. 숫자 오름차순
            String sortNumber = CommonUtil.textOrderByAsc(SortTypeEnum.NUMBER.name(), removeText);

            // 5. 교차출력
            String crossText = CommonUtil.crossConverter(sortString, sortNumber);

            //6. 출력 묶음단위 처리
            Map bundleMap = CommonUtil.bundleSubString(crossText, crawlingDtoMono.getBundleUnit());

            // 결과 처리
            crawlingDtoMono.setShareData(bundleMap.get("share").toString());
            crawlingDtoMono.setRemainderData(bundleMap.get("remainder").toString());

            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(crawlingDtoMono), CrawlingDto.class);
        } else {
            return ServerResponse.status(HttpStatus.NOT_FOUND).body(BodyInserters.fromValue("error"));
        }
    }
}
