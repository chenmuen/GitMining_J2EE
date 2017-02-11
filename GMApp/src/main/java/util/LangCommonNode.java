package util;

import entity.LangCommon;

/**
 * Created by raychen on 16/6/14.
 */
public class LangCommonNode {
    public String name;
    public LangCommonNode father;
    public boolean visited;
    public int v;

    public LangCommonNode(String name) {
        this.name = name;
        this.father = this;
        this.visited = false;
        this.v = -1;
    }
}
