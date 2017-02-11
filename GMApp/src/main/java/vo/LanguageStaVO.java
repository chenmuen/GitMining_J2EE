package vo;

import entity.Language;

/**
 * Created by raychen on 16/5/12.
 */
public class LanguageStaVO {
    private String repo_fullname;
    private String language_name;
    private double rate;
    public long value;

    public LanguageStaVO(Language language, double rate, long value) {
        this.repo_fullname = language.getRepoFullName();
        this.language_name = language.getLanguage();
        this.rate = rate;
        this.value = value;
    }

    public LanguageStaVO(String language_name, double rate, long value){
        this.language_name = language_name;
        this.rate = rate;
        this.value = value;
    }

    public String getRepo_fullname() {
        return repo_fullname;
    }

    public void setRepo_fullname(String repo_fullname) {
        this.repo_fullname = repo_fullname;
    }

    public String getLanguage_name() {
        return language_name;
    }

    public void setLanguage_name(String language_name) {
        this.language_name = language_name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
