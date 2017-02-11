package vo;

import java.util.List;

/**
 * Created by raychen on 16/5/12.
 */
public class ContributorCommitsVO {
    private List<CommitVO> commits;
    private String loginName;

    public ContributorCommitsVO(List<CommitVO> commits, String loginName) {
        this.commits = commits;
        this.loginName = loginName;
    }

    public List<CommitVO> getCommits() {
        return commits;
    }

    public void setCommits(List<CommitVO> commits) {
        this.commits = commits;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
