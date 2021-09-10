package core.selector;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SelectorUnit Class
 */
public class SelectorUnit {
    /**
     * Table name
     */
    private String table;
    /**
     * Table's attribute name
     */
    private String attribute;

    /**
     * Attribute's value.
     */
    private String value;

    /**
     * Regex for selector
     * string:string:(string,number).
     */
    private final static String SELECTOR_PATTERN = "([^\\d\\W]+):([^\\d\\W]+):([\\w]+)";
    private final static Pattern pattern = Pattern.compile(SELECTOR_PATTERN);

    /**
     *
     * @param table {@link SelectorUnit#table}
     * @param attribute {@link SelectorUnit#attribute}
     * @param value {@link SelectorUnit#value}
     */
    private SelectorUnit(String table, String attribute, String value){
        this.table = table;
        this.attribute = attribute;
        this.value = value;
    }

    public String getTable() {
        return table;
    }

    public String getAttribute() {
        return attribute;
    }

    public String getValue() {
        return value;
    }


    /**
     * SelectorUnit builder
     * @param table {@link SelectorUnit#table}
     * @param attribute {@link SelectorUnit#attribute}
     * @param value {@link SelectorUnit#value}
     * @return {@link SelectorUnit}
     */
    public static SelectorUnit builder(String table, String attribute, String value){
        return new SelectorUnit(table, attribute, value);
    }

    /**
     *
     * @param expression Selector expression {@link SelectorUnit#SELECTOR_PATTERN}.
     * @return {@link SelectorUnit}
     */
    public static SelectorUnit builder(String expression){
        return toSelector(expression);
    }

    /**
     * Whether the expression is valid or not.
     * @param expression Selector expression {@link SelectorUnit#SELECTOR_PATTERN}
     * @return boolean
     */
    public static boolean isValidSelector(String expression){
        return expression.matches(SELECTOR_PATTERN);
    }

    /**
     * Transform an expression to {@link SelectorUnit}
     * @param expression Selector expression {@link SelectorUnit#SELECTOR_PATTERN}
     * @return {@link SelectorUnit}
     */
    private static SelectorUnit toSelector(String expression){

        Matcher matcher = pattern.matcher(expression);

        if(matcher.find()){
            String table = matcher.group(1);
            String attribute = matcher.group(2);
            String value = matcher.group(3);

            return builder(table, attribute, value);
        }

        return null;
    }

    @Override
    public String toString() {
        return "SelectorUnit{"
                + table + ':'
                + attribute + ':'
                + value+
                '}';
    }
}
