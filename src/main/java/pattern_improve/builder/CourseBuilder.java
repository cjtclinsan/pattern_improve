package pattern_improve.builder;

/**
 * @author taosh
 * @create 2020-03-05 15:17
 */
public class CourseBuilder {
    private Course course = new Course();

    public void addName(String name){
        course.setName(name);
    }

    public void addPPT(String ppt){
        course.setPpt(ppt);
    }

    public void addVideo(String video) { course.setVideo(video);}
    public void addNote(String note) { course.setNote(note); }
    public void addHomework(String homework) { course.setHomework(homework); }

    public Course build(){
        return course;
    }
}
