package util;

/**
 * Created by raychen on 16/6/14.
 */
public class LangCommonLine {
    public LangCommonNode x;
    public LangCommonNode y;
    public int value;
    public int next;

    public LangCommonLine(LangCommonNode x, LangCommonNode y, int value, int next) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.next = next;
    }
}
