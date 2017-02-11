package util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by raychen on 16/6/8.
 */
public class LanguageRelation {
    public List<String> categories;
    public List<RelationSingleNode> values;
    public List<RelationLine> links;

    public LanguageRelation() {
        this.categories = new LinkedList<>();
        this.values = new LinkedList<>();
        this.links = new LinkedList<>();
    }
}
