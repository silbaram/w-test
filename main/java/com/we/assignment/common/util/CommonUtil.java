package com.we.assignment.common.util;

import com.we.assignment.crawling.code.HtmlReplaceTypeEnum;
import com.we.assignment.crawling.code.SortTypeEnum;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CommonUtil {


    /**
     * url중 한글 파라미터를 인코딩
     * @param urlStr url 주소
     * @return 인코딩된 url
     */
    public static String encodeUrl(String urlStr) {

        char[] txtChar = urlStr.toCharArray();
        for (int j = 0; j < txtChar.length; j++) {

            if (txtChar[j] >= '\uAC00' && txtChar[j] <= '\uD7A3') {

                String targetText = String.valueOf(txtChar[j]);

                try {
                    urlStr = urlStr.replace(targetText, URLEncoder.encode(targetText, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }

        return urlStr;
    }


    /**
     * 특정 문자 삭제
     * @param type 삭제타입
     * @param text 삭제요청 문자열
     * @return 삭제결과
     */
    public static String removeSpecificCharacters(String type, String text) {

        if (HtmlReplaceTypeEnum.HTML.name().equals(type)) {

            return text.replaceAll("<(/)?([a-zA-Z\uAC00-\uD7A3]*)(\\s[a-zA-Z\uAC00-\uD7A3]*=[^>]*)?(\\s)*(/)?>", "");
//            return text.replaceAll("<(/)?([!a-zA-Z]*)(\\\\s[a-zA-Z]*=[^>]*)?(\\\\s)*(/)?>", "");

        } else if (HtmlReplaceTypeEnum.TEXT.name().equals(type)) {

            return text.replaceAll("[<>]", "");

        } else if (HtmlReplaceTypeEnum.SPECIAL.name().equals(type)) {

            return text.replaceAll("[^0-9a-zA-Z\\s]|\\p{Z}", "");
        }

        return text;
    }


    /**
     * 문자열을 char별로 오름차순 정렬
     * @param type 숫자, 문자 타입
     * @param text 정렬 할 문자열
     * @return 정렬 결과
     */
    public static String textOrderByAsc(String type, String text) {

        if (SortTypeEnum.NUMBER.name().equals(type)) {

            return Arrays.stream(text.split("")).parallel().filter(c -> Character.isDigit(c.charAt(0))).sorted().collect(Collectors.joining());
        } else if (SortTypeEnum.CHAR.name().equals(type)) {

            return Arrays.stream(text.split("")).parallel().filter(c -> Character.isAlphabetic(c.charAt(0))).sorted((o1, o2) -> {
                int res = o1.compareToIgnoreCase(o2);
                return (res == 0) ? o1.compareTo(o2) : res;
            }).collect(Collectors.joining());
        }

        return text;

    }


    /**
     * 교차출력
     * @param alphabet 알파뱃 문자열
     * @param digit 숫자 문자열
     * @return 교차 결과
     */
    public static String crossConverter(String alphabet, String digit) {
        StringBuilder crossText = new StringBuilder();

        int i = 0;
        for (; i < alphabet.length() && i < digit.length(); i++) {
            crossText.append(alphabet.charAt(i));
            crossText.append(digit.charAt(i));
        }

        // 큰쪽 나머지를 뒤에 붙이기
        if (i < alphabet.length()) {
            crossText.append(alphabet.substring(i));
        } else if (i < digit.length()) {
            crossText.append(digit.substring(i));
        }

        return crossText.toString();
    }


    /**
     * 화면 출력용 몱 / 나머지 구하기
     * @param text 작업용 문자열
     * @param bundleUnit 출력묶음단위
     * @return
     */
    public static Map<String, String> bundleSubString(String text, int bundleUnit) {
        Map<String, String> bundleMap = new HashMap<>();

        int bundleRemainder = text.length() % bundleUnit;
        int bundleCount = text.length() - bundleRemainder;

        bundleMap.put("share", text.substring(0, bundleCount));
        bundleMap.put("remainder", text.substring(bundleCount));

        return bundleMap;
    }
}
