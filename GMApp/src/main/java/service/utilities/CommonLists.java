package service.utilities;

import entity.Language;
import vo.LanguageJoinVO;
import vo.LanguageVO;

import java.util.*;

/**
 * Created by raychen on 16/5/15.
 */
public class CommonLists {
    public static String[] categories = {"All","App","Framework","Json","Library","Linux","Mac","Windows","Plugin","Server","Web","Template"};
    public static String[] languages = {"All","Java","C","C++","Objective-C","Perl","Python","PHP","Ruby","JavaScript","Html","CSS", "Shell"};
    public static String[] years = {"All","2008","2009","2010","2011","2012","2013","2014","2015", "2016"};
    public static String[] campanys = {"Google", "Twitter", "Github", "Facebook", "Mozilla", "Red Hat", "Heroku", "Shopify", "Microsoft"};
//    public static String[] lang_categories = {"All", "Objective-Oriented", "Functional", "Imperative", "Structured", "Multi-paradigm", "Procedural", "Concurrent"};
//    public static String[] lang_apps = {"All", "Website", "Mobile Application", "Web Application", "Server"};

    public static void filterLan(List<LanguageJoinVO> languagesList){
        languagesList.forEach(language -> {
            boolean find = false;
            for (int j = 0; j < languages.length; j++) {
                if (languages[j].equals(language.name)){
                    find = true;
                    break;
                }
            }
            if (!find) language.name = "others";
        });
    }

    public static List<String> filterCom(List<Object> list){
        List<String> companies = new LinkedList<>();
        list.forEach(obj -> {
            String com = ((String) obj).toLowerCase();
            boolean find = false;
            for (int i = 0; i < campanys.length; i++) {
                String company = campanys[i].toLowerCase();
                if (com.contains(company)) {
                    companies.add(campanys[i]);
                    find = true;
                    break;
                }
            }
            //if (!find) companies.add("Others");
        });
        return companies;
    }

    public static List<String> getCommonStrings(List<String> tags1, List<String> tags2){
        Set<String> s1 = new HashSet<>(tags1);
        Set<String> s2 = new HashSet<>(tags2);
        s1.retainAll(s2);
        return new LinkedList<>(s1);
    }

    public static double getRelationScore(double repos, double tags){
        if (repos==0) return tags*5;
        return (1-1/repos)*10000 + tags*5;
    }

}
