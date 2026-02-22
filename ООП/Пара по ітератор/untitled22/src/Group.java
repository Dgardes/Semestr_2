import java.util.HashMap;

public class Group {
   private   String pib;
   private  byte kurs;

    private HashMap <Integer, Student> std = new HashMap<Integer, Student>();

    @Override
    public String toString() {
        return "Group{" +
                "pib='" + pib + '\'' +
                ", kurs=" + kurs +
                ", std=" + std +
                '}';
    }

    public Group(String pib, byte kurs, HashMap<Integer, Student> std) {
        this.pib = pib;
        this.kurs = kurs;
        this.std = std;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public byte getKurs() {
        return kurs;
    }

    public void setKurs(byte kurs) {
        this.kurs = kurs;
    }

    public HashMap<Integer, Student> getStd() {
        return std;
    }

    public void setStd(HashMap<Integer, Student> std) {
        this.std = std;
    }
}
