#include <iostream>
#include <list>
#include <vector>
#include <string>
#include <windows.h>
using namespace std;

// =======================================================
// хеш-таблиця методом ланцюжків: ВАРІАНТ №6
// Ключ: int (держ. номер автомобіля), Значення: string (марка)
// =======================================================
class HashTable {
private:
    int m;//розмір таблиці
    vector<list<pair<int, string>>> t;//масив списків

    //метод ділення з захистом від від'ємних чисел
    int h(int key)
    {
        return ((key % m) + m) % m;
    }

public:
    //конструктор таблиці
    HashTable(int size) : m(size), t(size) {}

    //Вставка 
    void insert(int key, string val) 
    {
        int idx = h(key);
        for (auto& p : t[idx]) 
        {
            if (p.first == key) 
            {
                //якщо такий номер вже є – оновлюємо марку
                p.second = val; 
                return;
            }
        }
        //додаємо в ланцюжок колізій
        t[idx].push_back({ key, val }); 
    }

    //пошук: повертає марку або "" якщо не знайдено
    string find(int key) 
    {
        int idx = h(key);
        for (auto& p : t[idx]) 
        {
            if (p.first == key) return p.second;
        }
        return "";
    }

    //видалення запису 
    void remove(int key) 
    {
        int idx = h(key);
        t[idx].remove_if([key](auto& p) { return p.first == key; });
    }

    //вивід поточної структури таблиці
    void print() 
    {
        cout << "\n=== Реестр авто (m=" << m << ") ===\n";
        for (int i = 0; i < m; i++) 
        {
            cout << "[" << i << "] ";
            if (t[i].empty()) 
            {
                cout << "---\n";
                continue;
            }
            for (auto& p : t[i]) 
            {
                cout << p.first << ":" << p.second << "  ";
            }
            cout << "\n";
        }
        cout << "==========================================\n";
    }
};

int main() 
{
    SetConsoleCP(1251);
	SetConsoleOutputCP(1251);

    //створюємо реєстр на 7 комірок
    HashTable registry(7);

    //вставляємо 7 пар даних 
    registry.insert(1001, "Toyota");
    registry.insert(2003, "BMW");
    registry.insert(1008, "Audi"); // 1008 % 7 = 4 (колізія з 1001, бо 1001 % 7 = 4)
    registry.insert(3502, "Mercedes");
	registry.insert(5005, "Ford"); // 5005 % 7 = 1 (колізія з 2003, бо 2003 % 7 = 1)
    registry.insert(1015, "Honda");// 1015 % 7 = 4 (колізія в комірку 4)
    registry.insert(7779, "Porsche");

    //виводимо таблицю до операцій
    cout << "Стан реєстру автомобілів після первинного заповнення:";
    registry.print();

    //демонстрація пошуку
    cout << "\n перевірка операцій пошуку \n";

    string car1 = registry.find(2002);
    cout << "Пошук номера 2002: " << (car1 != "" ? car1 : "НЕ ЗНАЙДЕНО") << endl;
    string car2 = registry.find(1015);
    cout << "Пошук номера 1015: " << (car2 != "" ? car2 : "НЕ ЗНАЙДЕНО") << endl;
    string car3 = registry.find(9999);
    cout << "Пошук номера 9999: " << (car3 != "" ? car3 : "НЕ ЗНАЙДЕНО") << endl;

    //демонстрація видалення 1 елемента
    cout << "\n перевірка операції видалення \n";
    cout << "Видаляємо автомобіль з номером 1001 \n";
    registry.remove(1001);

    //таблиця після видалення
    cout << "\nСтан реєстру автомобілів після видалення:";
    registry.print();

    cout << "Перевірка авто 1008 після видалення сусіда: " << registry.find(1008) << endl;
    return 0;
}