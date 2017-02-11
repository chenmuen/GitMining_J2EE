package vo;

import entity.Punch;

/**
 * Created by raychen on 16/5/12.
 */
public class PunchVO {
    public String repoFullName;
    public int day;
    public int hour;
    public int commits;

    public PunchVO(Punch punch) {
        this.repoFullName = punch.getRepoFullName();
        this.day = punch.getDay();
        this.hour = punch.getHour();
        this.commits = punch.getCommits();
    }

    public PunchVO(int day, int hour, int commits) {
        this.day = day;
        this.hour = hour;
        this.commits = commits;
    }
}
