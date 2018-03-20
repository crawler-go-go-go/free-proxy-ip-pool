package org.cc11001100.utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

/**
 * 用来解释分析此项目用到的自定义脚本语言
 *
 * @author CC11001100
 */
public class SimpleScriptParser {

    /**
     * 用来将url展开
     * <p>
     * 1. 会将此类的url展开
     * 2. 不支持一个字符串有多个生成序列，因为还没想好应该叠加还是同步
     * <p>
     * raw:
     * http://www.foo.bar/{{1..2..1}}.html
     * http://www.foo.bar/{{1..2}}.html
     * <p>
     * to:
     * http://www.foo.bar/1.html
     * http://www.foo.bar/2.html
     *
     * 序列只能递增
     *
     * @param urls
     * @return
     */
    public static List<String> parseUrl(String[] urls) {
        Pattern extractRangeSyntax = Pattern.compile("\\{\\{\\s*(\\d+\\.\\.\\d+\\.\\.\\d+)\\s*}}|\\{\\{\\s*(\\d+\\.\\.\\d+)\\s*}}");
        return Arrays.stream(urls).flatMap(url -> {
            Matcher matcher = extractRangeSyntax.matcher(url);
            if (matcher.find()) {
				Set<String> res = new LinkedHashSet<>();
                int group = matcher.group(1) !=null ? 1 : 2;
                String[] range = matcher.group(group).split("\\.\\.");
                int begin = Integer.parseInt(range[0]); // begin
                int end = Integer.parseInt(range[1]);  // end
                int step = range.length == 3 ? Integer.parseInt(range[2]) : 1;  // step
                while (begin <= end) {
                    String finalUrl = url.substring(0, matcher.start()) + begin + url.substring(matcher.end());
                    res.add(finalUrl);
                    begin += step;
                }
                return res.stream();
            } else {
                return Collections.singleton(url).stream();
            }
        }).collect(toList());
    }

}
