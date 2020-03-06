package suanfa.builder;

/**
 * @author taosh
 * @create 2020-03-05 15:19
 */
public class Test {
    public static void main(String[] args) {
//        CourseBuilder builder = new CourseBuilder();
//
//        builder.addName("java课程");
//        builder.addHomework("作业");
//        builder.addNote("笔记");
//        builder.addPPT("ppt");
//        builder.addVideo("视频");

        CourseBuilderTwo builderTwo = new CourseBuilderTwo()
                .addName("设计模式")
                .addPPT("【PPT 课件】")
                .addVideo("【回放视频】")
                .addNote("【课堂笔记】")
                .addHomework("【课后作业】");

        System.out.println(builderTwo.build());
    }
}
