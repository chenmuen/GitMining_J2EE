package controller.uitities;

import entity.Language;
import entity.LanguageYearStat;
import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.python.modules.math;
import service.evaluate.Evaluate;
import service.evaluate.Rank;
import service.utilities.CommonLists;
import util.LanguageRelation;
import util.LanguageTrends;
import vo.LanguageVO;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by raychen on 16/5/15.
 */
public class CommonUtil {

    public static Map<Integer, List<LanguageVO>> rankedLangs;

    public static JSONArray getPageBox(int sum, int current){
        JSONArray pageBox = new JSONArray();
        if (current > 6){
            pageBox.add(1);
            pageBox.add(2);
            pageBox.add("...");
            pageBox.add(current-2);
            pageBox.add(current-1);
        }else
            for (int i = 0; i < current-1; i++) {
                pageBox.add(i+1);
            }
        pageBox.add(current);
        if (sum - current > 5){
            pageBox.add(current+1);
            pageBox.add(current+2);
            pageBox.add("...");
            pageBox.add(sum-1);
            pageBox.add(sum);
        }else
            for (int i = current; i < sum; i++) {
                pageBox.add(i+1);
            }
        return pageBox;
    }

    public static Map<Integer, List<LanguageVO>> getRankedLangs(List<LanguageVO> langs, Map<Integer, List<LanguageYearStat>> allStats){
        if (rankedLangs == null){
            Map<Integer, List<LanguageVO>> rankedLangs_temp = new HashMap<>();
            String[] years = CommonLists.years;
            for (int i = 1; i < years.length; i++) {
                int year = Integer.parseInt(years[i]);
                Evaluate.calculateLangSizeOfYear(langs, allStats.get(year), year);
                Rank.rankLanguage(langs, year);

                List<LanguageVO> newVOs = new LinkedList<>();
                langs.forEach(languageVO -> newVOs.add(languageVO));
                rankedLangs_temp.put(year, newVOs);
            }
            Rank.rankLanguage(langs, -1);
            List<LanguageVO> newVOs = new LinkedList<>();
            langs.forEach(languageVO -> newVOs.add(languageVO));
            rankedLangs_temp.put(-1, newVOs);
            rankedLangs = rankedLangs_temp;
        }
        return rankedLangs;
    }

    public static JSONObject getTrendsSectors(LanguageTrends trends){

        JSONObject ranking = new JSONObject();
        JSONArray langName = new JSONArray();
        JSONArray langYear = new JSONArray();
        JSONArray langData = new JSONArray();
        langName.add(trends.langName);
        ranking.put("langName", langName);

        String[] years = CommonLists.years;
        for (int i = 1; i < years.length; i++) {
            int year = Integer.parseInt(years[i]);
            if (trends.rates.containsKey(year) && trends.rates.get(year)!=0){
                langYear.add(year);
                langData.add(trends.rates.get(year));
            }
        }
        ranking.put("langYear", langYear);
        ranking.put("langData", langData);

        return ranking;
    }

    public static JSONObject getTrendsAll(List<LanguageTrends> allTrends){
        JSONObject ret = new JSONObject();
        JSONArray yearArray = new JSONArray();
        JSONArray names = new JSONArray();
        JSONArray datas = new JSONArray();

        String[] years = CommonLists.years;
        for (int i = 1; i < years.length; i++) {
            int year = Integer.parseInt(years[i]);
            yearArray.add(year);
        }
        allTrends.forEach(trends -> {
            names.add(trends.langName);
            JSONObject data = new JSONObject();
            data.put("name", trends.langName);
            data.put("type","line");
            JSONArray data_s = new JSONArray();
            for (int i = 3; i < years.length; i++) {
                int year = Integer.parseInt(years[i]);
                if (trends.rates.containsKey(year)) data_s.add(trends.rates.get(year));
                else data_s.add(0);
            }
            data.put("data", data_s);
            datas.add(data);
        });

        ret.put("langYears", yearArray);
        ret.put("langName", names);
        ret.put("langData", datas);
        return ret;
    }

    public static JSONObject getTotalRelationFromRelation(LanguageRelation relation){
        return getRelationFromRelation(relation,"ALL");
    }

    public static JSONObject getSingleRelationFromRelation(LanguageRelation relation){
        return getRelationFromRelation(relation,"Single");
    }

    public static JSONObject getRelationFromRelation(LanguageRelation relation,String type){
        JSONObject ret = new JSONObject();

        JSONArray categories = new JSONArray();
        JSONArray relationNodes = new JSONArray();
        JSONArray relationLinks = new JSONArray();

        relation.values.forEach(node -> {
//            {name:'Java',value:700,category:0,symbolSize:20,label:{normal:{show:true}}}
            JSONObject data = new JSONObject();
            data.put("value", node.value);
            data.put("name", node.name);
//            data.put("symbolSize", math.sqrt(node.symbolSize)*5);
            if (type.equals("ALL"))
                data.put("symbolSize", math.pow(node.symbolSize,0.5)*6);
            else
                data.put("symbolSize", math.pow(node.symbolSize,0.5)*9);
            data.put("category", node.category);
            JSONObject label = new JSONObject();
            JSONObject normal = new JSONObject();
            if (node.value > 9){
                normal.put("show", "true");
            }
            label.put("normal", normal);
            normal.put("formatter", "{b}");
            data.put("label", label);

            relationNodes.add(data);
        });
        relation.categories.forEach(s -> {
            JSONObject obj = new JSONObject();
            obj.put("name", s);
            categories.add(obj);
        });
        relation.links.forEach(line -> {
            JSONObject obj = new JSONObject();
            obj.put("source", line.source);
            obj.put("target", line.target);
            relationLinks.add(obj);
        });

        ret.put("categories", categories);
        ret.put("relationLinks", relationLinks);
        ret.put("relationNodes", relationNodes);

        return ret;
    }
}
