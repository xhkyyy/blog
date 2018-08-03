package hession_issue;

import java.util.HashMap;

public class Teacher extends User {

    private HashMap<String, Number> contentMap = new HashMap<String, Number>();

    private Float aFloat;

    public HashMap<String, Number> getContentMap() {
        return contentMap;
    }

    public Float getaFloat() {
        return aFloat;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "contentMap=" + contentMap +
                ", aFloat=" + aFloat +
                '}';
    }

    public void setaFloat(Float aFloat) {
        this.aFloat = aFloat;
    }

}
