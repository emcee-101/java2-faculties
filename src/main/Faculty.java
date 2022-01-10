package main;

public class Faculty {

    public Faculty(String decan, String name, String[] courses) {
        this.decan = decan;
        this.name = name;
        this.courses = courses;
    }

    private String decan;
    private String name;
    private String[] courses;
    public static void main(String[] args) {
        System.out.println("hello world!");
    }

    public String getDecan() {
        return decan;
    }

    public void setDecan(String decan) {
        this.decan = decan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getCourses() {
        return courses;
    }

    public void setCourses(String[] courses) {
        this.courses = courses;
    }
}