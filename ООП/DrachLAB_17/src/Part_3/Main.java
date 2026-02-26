package Part_3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        List<Integer> myArrList = new ArrayList<>();
        for(int i = 1; i <= 10; i++) myArrList.add(i);

        List<Integer> myLinkList = new LinkedList<>();
        for(int i = 1; i <= 10; i++) myLinkList.add(i);

        System.out.println("Перемішування:");
        myArrList = MyCollections.shuffleList(myArrList);
        myLinkList = MyCollections.shuffleList(myLinkList);
        System.out.println("ArrayList: " + myArrList);
        System.out.println("LinkedList: " + myLinkList);

        System.out.println("сортування:");
        myArrList = MyCollections.sortList(myArrList);
        myLinkList = MyCollections.sortList(myLinkList);
        System.out.println("ArrayList: " + myArrList);
        System.out.println("LinkedList: " + myLinkList);

        System.out.println("зворотнє сортування:");
        myArrList = MyCollections.sortAndReverseList(myArrList) ;
        myLinkList = MyCollections.sortAndReverseList(myLinkList);
        System.out.println("ArrayList: " + myArrList);
        System.out.println("LinkedList: " + myLinkList);

        System.out.println("пошук найбільшого / найменшого:");
        int max = MyCollections.findMax(myArrList);
        int min = MyCollections.findMin(myLinkList);
        System.out.println("ArrayList (max): " + max);
        System.out.println("LinkedList (min): " + min);

        System.out.println("свап двох індексів (2 і 8):");

        System.out.println("до свапу:");
        System.out.println("ArrayList: " + myArrList);
        System.out.println("LinkedList: " + myLinkList);

        myArrList = MyCollections.swap(myArrList, 2, 8) ;
        myLinkList = MyCollections.swap(myLinkList, 2, 8);

        System.out.println("після свапу:");
        System.out.println("ArrayList: " + myArrList);
        System.out.println("LinkedList: " + myLinkList);

    }
}
