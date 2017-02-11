package vo;

import entity.Language;

/**
 * Created by raychen on 16/6/9.
 */
public class LanguageJoinVO {
    public String name;
    public long size;

    public LanguageJoinVO(Language language) {
        this.name = language.getLanguage();
        this.size = language.getSize();
    }
}
