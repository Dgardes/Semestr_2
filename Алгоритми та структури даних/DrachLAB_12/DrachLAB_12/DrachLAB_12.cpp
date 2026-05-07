#include <iostream>
#include <vector>
#include <stdexcept>
#include <string>
#include <windows.h>

using namespace std;

class MaxPriorityQueue {

private: vector<int> heap;
    void bubbleUp(int i) 
    {
        while (i > 0 && heap[(i - 1) / 2] < heap[i]) 
        {
            swap(heap[i], heap[(i - 1) / 2]);
            i = (i - 1) / 2;
        }
    }

    //метод, що опускає елемент вниз
    void heapify(int i) 
    {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int n = heap.size();

        if (left < n && heap[left] > heap[largest]) largest = left;
        if (right < n && heap[right] > heap[largest]) largest = right;

        if (largest != i) {
            swap(heap[i], heap[largest]);
            heapify(largest);
        }
    }


    // Операція push. додаємо в кінець і піднімаємо вгору
    public: void push(int value) 
    {
        heap.push_back(value);
        bubbleUp(heap.size() - 1);
        cout << "Додано: " << value << endl;
    }

    //операція top. повертає максимум
    int top() 
    {
        if (heap.empty()) throw runtime_error("Черга порожня");
        return heap[0];
    }

    //операція pop. міняємо корінь з останнім, видаляємо останній, робимо heapify
    void pop() 
    {
        if (heap.empty()) return;

        cout << "Вилучено максимум: " << heap[0] << endl;
        heap[0] = heap.back();
        heap.pop_back();

        if (!heap.empty()) 
        {
            heapify(0);
        }
    }

    bool empty() { return heap.empty(); }
    size_t size() { return heap.size(); }
};

int main() 
{
    SetConsoleCP(1251);//кодування для введення
    SetConsoleOutputCP(1251); //кодування для виводу
    MaxPriorityQueue pq;

    cout << "Тестування пріоритетна черга:";
    pq.push(10);
    pq.push(30);
    pq.push(20);
    pq.push(5);
    pq.push(40);

    cout << "Поточний максимум : " << pq.top() << endl;

    cout << "Вилучення елементів за пріоритетом:\n";
    while (!pq.empty()) 
    {
        cout << "Найвищий поточний пріоритет: " << pq.top() << " -> ";
        pq.pop();
    }

    return 0;
}