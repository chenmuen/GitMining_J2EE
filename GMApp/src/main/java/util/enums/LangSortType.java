package util.enums;

/**
 * Created by raychen on 16/6/5.
 */
public enum LangSortType {
    GENERAL,REPOS,DEVS;

    public String getOrderColumn() {
        switch (this) {
            case GENERAL:
                return "";
            case REPOS:
                return "repoNum";
            case DEVS:
                return "userNum";
            default:
                return "null";
        }
    }
}
