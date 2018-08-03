package hession_issue;

public class Student extends User {

    // 和父类拥有相同到字段名
    private int userId;

    private String desc;

    public Student(String desc) {
        this.desc = desc;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Student{" +
                "userId=" + userId +
                ", desc='" + desc + '\'' +
                ", userName='" + getUserName() + '\'' +
                '}';
    }
}
