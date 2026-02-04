import java.util.ArrayList;

public class Bus
{
    private Driver driver;
    private ArrayList<Pasadjir> pas;

    {
        this.pas = new ArrayList<>();
    }

    public void addPas(Pasadjir pasadjir)
    {
        this.pas.add(pasadjir);
    }

    public void addById(Pasadjir pasadjir, int index)
    {
        this.pas.add(index, pasadjir);
    }

    public void removeByIndex (int index)
    {
        this.pas.remove(index);
    }

    public void editByIndex(Pasadjir pasadjir,int index)
    {
        this.pas.set(index, pasadjir);
    }

    public void findPas(String name)
    {

    }
}
