import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        HashMap<String, Group> college = new HashMap<>();
        Student student = new Student("Oleg", true);
        Student student1 = new Student("Alic", true);
        Student student2 = new Student("Oleg1", false);
        Student student3 = new Student("Oleg4", false);
        HashMap<Integer, Student> students1 = new HashMap<>();
        students1.put(1, student);
        students1.put(2, student1);
        HashMap<Integer, Student> students2 = new HashMap<>();
        students2.put(1, student2);
        students2.put(2, student3);

        Group group = new Group("DFG", (byte) 3, students1);
        Group group1 = new Group("DFD", (byte) 4, students2);
        college.put("IPZ", group);
        college.put("IPZ1", group1);

        Iterator<Map.Entry<String, Group>> iterator = college.entrySet().iterator();
        while (iterator.hasNext())
        {
            Iterator<Map.Entry<Integer, Student>> iteratorStuds = iterator.next().getValue().getStd().entrySet().iterator();
            while (iteratorStuds.hasNext()) {
                Map.Entry<Integer, Student> entry = iteratorStuds.next();
                if (!entry.getValue().isKursvo()) {
                    iteratorStuds.remove();
                }
            }
        }
        for (Map.Entry<String, Group> entry : college.entrySet())
        {
            System.out.println(entry.toString());
        }
    }
}