package org.cc11001100.utils;

import org.junit.Test;

/**
 * @author CC11001100
 */
public class SimpleScriptParserTest {

    @Test
    public void testParseUrl(){

        SimpleScriptParser.parseUrl(new String[]{
                "http://foo.com/{{1..10..1}}.html",
                "http://foo.com/{{20..30}}.html",
                "http://foo.com/1024.html"
        }).forEach(System.out::println);

    }

}
