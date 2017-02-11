package util.enums;

/**
 * Created by raychen on 16/6/16.
 */
public enum AnalyType {
    STAR,FORK,ISSUE,CONTRIBUTOR,REPO_SCORE,SIZE,
    FOLLOWER,REPO_STAR,REPOS,USER_SCORE,
    USERS,REPOS_LANG,LANG_SCORE;

    @Override
    public String toString() {
        switch (this){
            case STAR: return "starsCount";
            case FORK: return "forksCount";
            case ISSUE: return "issuesCount";
            case CONTRIBUTOR: return "contributorsCount";
            case REPO_SCORE: return "score";
            case SIZE: return "size";
            case FOLLOWER: return "followersCount";
            case REPO_STAR: return "repoStarCount";
            case REPOS: return "publicRepo";
            case USER_SCORE: return "score";
            case USERS: return "userNum";
            case REPOS_LANG: return "repoNum";
            case LANG_SCORE: return "score";
            default: return "default";
        }
    }
}
