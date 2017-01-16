package org.isen.guess;

import static org.junit.Assert.assertTrue;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class ServletTest extends JettyHarness {



    @Test
    public void itCanAccesMainPage() throws Exception {
    }

    @Test
    public void itCanParameterizeTheMessage() throws Exception {
        String result = get(HELLO_SERVLET_URI + "?name=John"); //http://localhost:9090/app/hello?name=John
        assertTrue(StringUtils.isNotEmpty(result));
        assertTrue(result.contains("John"));
    }

    @Test
    public void itCanRememberTheNameBetweenTwoCalls() throws Exception {
        String result = get(HELLO_SERVLET_URI);
        assertTrue(result.contains("World"));

        result = get(HELLO_SERVLET_URI + "?name=John");  //http://localhost:9090/app/hello?name=John
        assertTrue(result.contains("John"));

        result = get(HELLO_SERVLET_URI);
        assertTrue(result.contains("John"));  //http://localhost:9090/app/hello

    }
}
