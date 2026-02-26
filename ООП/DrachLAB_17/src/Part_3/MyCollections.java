package Part_3;

import java.util.Collections;
import java.util.List;

public class MyCollections
{
    public static List<Integer> sortList(List<Integer> list)
    {
        Collections.sort(list);
        return list;
    }

    public static List<Integer> sortAndReverseList(List<Integer> list)
    {
        Collections.reverse( sortList(list) );
        return list;
    }

    public static int findMax(List<Integer> list)
    {
        return Collections.max(list);
    }

    public static int findMin(List<Integer> list)
    {
        return Collections.min(list);
    }

    public static List<Integer> shuffleList(List<Integer> list)
    {
        Collections.shuffle(list);
        return list;
    }

    public static List<Integer> swap(List<Integer> list, int idx_1, int idx_2)
    {
        Collections.swap(list, idx_1, idx_2);
        return list;
    }
}
