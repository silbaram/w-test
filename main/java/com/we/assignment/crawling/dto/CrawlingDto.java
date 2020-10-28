package com.we.assignment.crawling.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrawlingDto {
    private String crawlingTargetUrl;
    private String tagReplaceType;
    private int bundleUnit;

    private String shareData;
    private String remainderData;
}
