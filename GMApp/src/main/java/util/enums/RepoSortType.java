package util.enums;

/**
 * Created by chenm on 2016/5/8.
 */
public enum RepoSortType {
    GENERAL,
    STAR,
    FORK,
    CONTRIBUTOR;

    public String getOrderColumn(){
        switch (this) {
            case STAR:
                return "starsCount";
            case FORK:
                return "forksCount";
            case CONTRIBUTOR:
                return "contributorsCount";
            default:
                return null;
        }
    }
}
