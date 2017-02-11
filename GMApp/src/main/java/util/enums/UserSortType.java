package util.enums;

/**
 * Created by chenm on 2016/5/8.
 */
public enum UserSortType {
    GENERAL,
    FOLLOWER,
    REPO;

    public String getOrderColumn() {
        switch (this) {
            case FOLLOWER:
                return "followersCount";
            case REPO:
                return "publicRepo";
            default:
                return null;
        }
    }
}
