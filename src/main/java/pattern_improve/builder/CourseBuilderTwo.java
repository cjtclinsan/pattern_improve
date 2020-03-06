package pattern_improve.builder;

import lombok.Data;

/**
 * @author taosh
 * @create 2020-03-05 15:24
 */
public class CourseBuilderTwo {
    private Course course = new Course();

    public CourseBuilderTwo addName(String name){
        course.setName(name);
        return this;
    }

    public CourseBuilderTwo addPPT(String ppt){
        course.setPpt(ppt);
        return this;
    }

    public CourseBuilderTwo addVideo(String video) {
        course.setVideo(video);
        return this;
    }

    public CourseBuilderTwo addNote(String note) {
        course.setNote(note);
        return this;
    }

    public CourseBuilderTwo addHomework(String homework) {
        course.setHomework(homework);
        return this;
    }

    public Course build(){
        return this.course;
    }

    @Data
    public class Course{
        private String name;
        private String ppt;
        private String video;
        private String note;
        private String homework;

        @Override public String toString() {
            return "CourseBuilder{" + "name='" + name + '\'' + ", ppt='" + ppt + '\'' + ", video='" + video + '\'' + ", note='" + note + '\'' + ", homework='" + homework + '\'' + '}';
        }
    }
}
