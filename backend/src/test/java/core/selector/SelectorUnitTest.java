package core.selector;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SelectorUnitTest {
    @Test
    public void incorrectExpressionShouldReturnFalse(){
        String expression = "123Xaasd:aaa";

        assertEquals(false, SelectorUnit.isValidSelector(expression));
    }
    @Test
    public void incorrectExpressionShouldReturnNull(){
        String expression = "123Xsd@:a";

        SelectorUnit selector = SelectorUnit.builder(expression);

        assertNull(selector);
    }

    @Test
    public void tableAsNumberShouldReturnNull(){
        String expression = "123:attr:123";
        SelectorUnit selector = SelectorUnit.builder(expression);

        assertNull(selector);
    }

    @Test
    public void attributeAsNumberShouldReturnNull(){
        String expression = "table:123:123";

        SelectorUnit selector = SelectorUnit.builder(expression);

        assertNull(selector);
    }

    @Test
    public void valueCanBeAlphabetic(){
        String expression = "table:attr:value";

        SelectorUnit selector = SelectorUnit.builder(expression);

        assertNotNull(selector);
    }

    @Test
    public void valueCanBeNumber(){
        String expression = "table:attr:123";

        SelectorUnit selector = SelectorUnit.builder(expression);

        assertNotNull(selector);
    }

    @Test
    public void expressionShouldBeParsed(){
        String expression = "ens:id:123";
        SelectorUnit selector = SelectorUnit.builder(expression);

        assertEquals("ens", selector.getTable());
        assertEquals("id", selector.getAttribute());
        assertEquals("123", selector.getValue());
    }
}
