public class Student {
 private String name;
private boolean kursvo;

    public Student(String name, boolean kursvo) {
        this.name = name;
        this.kursvo = kursvo;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", kursvo=" + kursvo +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isKursvo() {
        return kursvo;
    }

    public void setKursvo(boolean kursvo) {
        this.kursvo = kursvo;
    }
}
