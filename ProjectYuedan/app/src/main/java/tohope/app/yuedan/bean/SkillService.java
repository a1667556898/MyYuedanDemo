package tohope.app.yuedan.bean;

/**
 * Created by Administrator on 2017/4/13.
 */

public class SkillService {
    private String name;
    private int image;

    public Boolean getNameIsSelect() {
        return nameIsSelect;
    }

    public void setNameIsSelect(Boolean nameIsSelect) {
        this.nameIsSelect = nameIsSelect;
    }

    private Boolean nameIsSelect;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
