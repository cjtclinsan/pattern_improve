package pattern_improve.builder;

import lombok.Data;

/**
 * @author taosh
 * @create 2020-03-05 15:15
 */
@Data
public class Course {
    private String name;
    private String ppt;
    private String video;
    private String note;
    private String homework;

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", ppt='" + ppt + '\'' +
                ", video='" + video + '\'' +
                ", note='" + note + '\'' +
                ", homework='" + homework + '\'' +
                '}';
    }
}
