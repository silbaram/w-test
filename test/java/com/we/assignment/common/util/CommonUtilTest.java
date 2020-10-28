package com.we.assignment.common.util;

import com.we.assignment.crawling.code.SortTypeEnum;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CommonUtilTest {

    @Test
    public void 한글파라미터_RUL_인코딩_테스트() {
        String encodeUrl= CommonUtil.encodeUrl("https://www.google.com/search?q=그림");

        Assert.assertEquals(encodeUrl, "https://www.google.com/search?q=%EA%B7%B8%EB%A6%BC");
    }

    @Test
    public void 문자열중_문자만_올림차순_정렬_테스트() {
        String sortString = CommonUtil.textOrderByAsc(SortTypeEnum.CHAR.name(), "Aa24591233345aABbbbCCcCcdDdD");

        Assert.assertEquals(sortString, "AAaaBbbbCCCccDDdd");
    }

    @Test
    public void 문자열중_숫자만_올림차순_정렬_테스트() {
        String sortString = CommonUtil.textOrderByAsc(SortTypeEnum.NUMBER.name(), "Aa24591233345aABbbbCCcCcdDdD");

        Assert.assertEquals(sortString, "12233344559");
    }

    @Test
    public void 문자_숫자_교차로_합치기_테스트() {
        String htmlStr = "Aa24591233345aABbbbCCcCcdDdD";
        String charSortString = CommonUtil.textOrderByAsc(SortTypeEnum.CHAR.name(), htmlStr);
        String numberSortString = CommonUtil.textOrderByAsc(SortTypeEnum.NUMBER.name(), htmlStr);

        String crossText = CommonUtil.crossConverter(charSortString, numberSortString);

        Assert.assertEquals(crossText, "A1A2a2a3B3b3b4b4C5C5C9ccDDdd");
    }

    @Test
    public void 숫자가_적을때_교차로_합치기_테스트() {
        String charSortString = "Aa";
        String numberSortString = "1";

        String crossText = CommonUtil.crossConverter(charSortString, numberSortString);

        Assert.assertEquals(crossText, "A1a");
    }

    @Test
    public void 문자가_적을때_교차로_합치기_테스트() {
        String charSortString = "b";
        String numberSortString = "12";

        String crossText = CommonUtil.crossConverter(charSortString, numberSortString);

        Assert.assertEquals(crossText, "b12");
    }

    @Test
    public void 문자_숫자가_같을때_교차로_합치기_테스트() {
        String charSortString = "bc";
        String numberSortString = "12";

        String crossText = CommonUtil.crossConverter(charSortString, numberSortString);

        Assert.assertEquals(crossText, "b1c2");
    }

    @Test
    public void 문자_나머지_있는_묶은_테스트() {
        String crossText = "b1c2";
        Map<String, String> actualMap = new HashMap<>();
        actualMap.put("share", "b1c");
        actualMap.put("remainder", "2");

        Map bundleMap = CommonUtil.bundleSubString(crossText, 3);

        Assert.assertTrue(actualMap.equals(bundleMap));
    }

    @Test
    public void 문자_나머지_없는_묶은_테스트() {
        String crossText = "b1c2";
        Map<String, String> actualMap = new HashMap<>();
        actualMap.put("share", "b1c2");
        actualMap.put("remainder", "");

        Map bundleMap = CommonUtil.bundleSubString(crossText, 2);

        Assert.assertTrue(actualMap.equals(bundleMap));
    }
}