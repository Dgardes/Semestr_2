#include <iostream>
#include <string>
#include <windows.h>
using namespace std;

struct City {
    string name; int pop; City* next;
};

class CityQueue {
private:
    City* first = nullptr, * last = nullptr;
public:
    bool empty() { return first == nullptr; }

    void push(string name, int pop) {
        City* tmp = new City{ name, pop, nullptr };
        if (empty()) first = last = tmp;
        else { last->next = tmp; last = tmp; }
    }

    void pop() {
        if (empty()) return;
        City* tmp = first; first = first->next;
        if (!first) last = nullptr;
        delete tmp;
    }

    void print() {
        if (empty()) { cout << "Черга порожня.\n"; return; }
        for (City* c = first; c; c = c->next) cout << c->name << " (" << c->pop << " тис.) -> ";
        cout << "NULL\n";
    }

    City* findMax() {
        if (empty()) return nullptr;
        City* maxNode = first;
        for (City* c = first->next; c; c = c->next) if (c->pop > maxNode->pop) maxNode = c;
        return maxNode;
    }

    void deleteUntilMax() {
        City* maxNode = findMax();
        if (!maxNode) return;
        while (first != maxNode) pop();
    }
};

int main() {
    SetConsoleCP(1251); SetConsoleOutputCP(1251);
    CityQueue q; int choice, pop; string name;

    while (true) {
        cout << "\n1. Додати місто\n2. Видалити перше місто\n3. Показати чергу\n4. Знайти найбільше\n5. Видалити ВСІ до найбільшого\n0. Вихід\nДія: ";
        cin >> choice; if (choice == 0) break;
        switch (choice) {
        case 1: cout << "Назва: "; cin >> name; cout << "Населення (тис.): "; cin >> pop; q.push(name, pop); break;
        case 2: q.pop(); cout << "Видалено.\n"; break;
        case 3: q.print(); break;
        case 4: { City* m = q.findMax(); if (m) cout << "Найбільше: " << m->name << " (" << m->pop << " тис.)\n"; else cout << "Помилка\n"; break; }
        case 5: q.deleteUntilMax(); cout << "Операцію виконано.\n"; q.print(); break;
        }
    }
    return 0;
}