package util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenm on 2016/5/8.
 */
public class ParamContainer {
    private final Map<String, Object> map;

    public ParamContainer() {
        this.map = new HashMap<String, Object>();
    }

    public ParamContainer(Map<String, Object> paraMap){
        if(paraMap.isEmpty()){
            this.map = new HashMap<String, Object>();
        } else {
            this.map = paraMap;
        }
    }
    //===================================put========================================
    /**
     * 添加表单参数，该方法应当在第一阶段时调用
     * @param key 表单键
     * @param value 表单原始值
     */
    public void put(String key, Object value){
        if(value!=null){
            map.put(key, value);
        }
    }

    public void put(String key, String value){
        if(value!=null && !value.trim().equals("")){
            map.put(key, value);
        }
    }

    public boolean has(String key) {
        return map.get(key)!=null;
    }

    //===================================get========================================
    /**
     * 按照bool返回表单值，调用此方法前应当验证isBoolean
     * 只有true和false作为布尔值，不区分大小写
     * @param key 表单键
     * @return
     */
    public boolean getB(String key){
        String value = _valueOf(key);
        if(value==null){
            throw new NumberFormatException();
        }
        if(value.toLowerCase().equals("true")){
            return true;
        }
        if(value.toLowerCase().equals("false")){
            return false;
        }
        throw new ParamEmptyException();
    }

    /**
     * 按照int返回表单值，调用此方法前应当验证isInteger（或类似正则表达式）
     * @param key 表单值
     * @return
     */
    public int getI(String key){
        String value = _valueOf(key);
        try{
            return Integer.parseInt(value, 10);
        } catch(NumberFormatException e){
            throw new ParamEmptyException();
        }
    }

    /**
     * 按照long返回表单值，调用此方法前应当验证isLong（或类似正则）
     * @param key 表单键
     * @return
     */
    public long getL(String key){
        String value = _valueOf(key);
        try{
            return Long.parseLong(value, 10);
        }catch(NumberFormatException e){
            throw new ParamEmptyException();
        }
    }

    /**
     * 按照字符串返回表单值
     * @param key 表单键
     * @return
     */
    public String getS(String key) {
        return _valueOf(key);
    }


    private String _valueOf(String key){
        Object value = this.map.get(key);
        if(value==null){
            return null;
        }
        return value.toString();
    }

    private Object _obj_valueOf(String key){
        Object value = this.map.get(key);
        return value;
    }
}
