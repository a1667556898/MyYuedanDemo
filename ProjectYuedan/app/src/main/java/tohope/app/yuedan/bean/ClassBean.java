package tohope.app.yuedan.bean;

import java.util.List;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by Administrator on 2017/4/7.
 */

public class ClassBean {
    private String title;
    private int images;
    private List<String> items;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }
}
