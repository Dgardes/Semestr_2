#include <iostream>
#include <list>
#include <windows.h>
using namespace std;

void printList(const string& label, const list<int>& L) {
    cout << label << ": ";
    for (int x : L) cout << x << " ";
    cout << endl;
}

int main() {
    SetConsoleCP(1251); SetConsoleOutputCP(1251);
    list<int> L;

    // 1. Демонстрація додавання елементів
    L.push_back(10); L.push_back(20); // додавання в кінець
    L.push_front(123); L.push_front(5); // додавання на початок 
    printList("Після додавання (push_back та push_front)", L);

    // 2. Демонстрація базової інформації
    cout << "Перший елемент (front()): " << L.front() << endl;
    cout << "Останній елемент (back()): " << L.back() << endl;
    cout << "Розмір списку (size()): " << L.size() << endl;

    // 3. Сортування та реверс
    L.sort(); printList("Після сортування (sort())", L);
    L.reverse(); printList("Після реверсу (reverse())", L);

    // 4. Видалення елементів
    L.pop_front(); printList("Після видалення з початку (pop_front())", L);
    L.pop_back(); printList("Після видалення з кінця (pop_back())", L);

    L.clear(); cout << "Список порожній (empty())? " << (L.empty() ? "Так" : "Ні") << endl;

    return 0;
}