package vo;

import entity.SingleLanguage;
import service.utilities.CommonLists;
import util.LanguageScore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by raychen on 16/5/22.
 */
public class LanguageVO {
    public List<String> tags_categories;
    public List<String> tags_apps;
    public String wiki;
    public String quoteContent;
    public String quoteFrom;
    public String helloworld;
    public int repos;
    public int users;
    public String languageName;

    public LanguageScore score;
    public Map<Integer, Long> reposOfYear;
    public Map<Integer, Long> usersOfYear;
    public long allRepos;
    public long allUsers;
    public Map<Integer, Integer> scoreOfYear;

    public SingleLanguage single;

    public LanguageVO(String languageName) {
        this.languageName = languageName;
        this.users = 1000;
        this.repos = 1000;
        init();
    }

    public LanguageVO(SingleLanguage singleLanguage, List<String> tags_categories, List<String> tags_apps) {
        this.languageName = singleLanguage.getLanguage();
        this.users = singleLanguage.getUserNum();
        this.repos = singleLanguage.getRepoNum();
        this.tags_apps = tags_apps;
        this.tags_categories = tags_categories;
        this.helloworld = singleLanguage.getHw();
        this.wiki = singleLanguage.getWiki();

        this.allRepos = this.repos;
        this.allUsers = this.users;
        this.single = singleLanguage;
        init();
        this.score.final_score = (double) singleLanguage.getScore()/10000;
    }

    private void init(){
        this.score = new LanguageScore();
        reposOfYear = new HashMap<>();
        usersOfYear = new HashMap<>();
        scoreOfYear = new HashMap<>();
        String[] years = CommonLists.years;
        for (int i = 1; i < years.length; i++) {
            reposOfYear.put(Integer.parseInt(years[i]), new Long(0));
            usersOfYear.put(Integer.parseInt(years[i]), new Long(0));
        }
//        this.wiki = "(Fake)Java is a general-purpose computer programming language that is concurrent, class-based, object-oriented,[14] and specifically designed to have as few implementation dependencies as possible. It is intended to let application developers \"write once, run anywhere\" (WORA),[15] meaning that compiled Java code can run on all platforms that support Java without the need for recompilation.";
//        this.helloworld = "class HelloWorldAppFake {\n" +
//                "    public static void main(String[] args) {\n" +
//                "        System.out.println(\"Hello World!\"); // Prints the string to the console.\n" +
//                "    }\n" +
//                "}";
//        this.quoteContent = "Typing is no substitute for thinking.(Fake)";
//        this.quoteFrom = "Dartmouth Basic manual, 1964.(Fake)";
    }
}
