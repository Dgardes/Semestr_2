#include <iostream>
#include <vector>
#include <string>
#include <windows.h>
#include <ctime>
#include "SortingAnalyzer.h"

//функція генерації студентів
std::vector<Student> generateStudents(int count)
{
    std::vector<Student> students(count);
    std::vector<std::string> firstNames = { "Олександр", "Марія", "Іван", "Анна", "Петро", "Олена", "Дмитро", "Катерина" };
    std::vector<std::string> lastNames = { "Коваленко", "Шевченко", "Бондаренко", "Ткаченко", "Кравченко", "Олійник" };
    std::vector<std::string> groups = { "АКІТР-23", "ГЗМ-23", "ІЕСТК-23", "ІПЗ-23" };

    for (int i = 0; i < count; i++)
    {
        students[i].id = 1000000 + rand() % 9000000;
        students[i].name = lastNames[rand() % lastNames.size()] + " " + firstNames[rand() % firstNames.size()];
        students[i].grade = rand() % 101;
        students[i].group = groups[rand() % groups.size()];
    }
    return students;
}

//функція виведення статистики
void printStatistics(const std::vector<Student>& arr)
{
    if (arr.empty()) {
        std::cout << "База даних порожня!\n";
        return;
    }

    int sum = 0;
    int gradeCount[101] = { 0 };

    for (const auto& s : arr) {
        sum += s.grade;
        gradeCount[s.grade]++;
    }

    std::cout << "\n=== СТАТИСТИКА ===\n";
    std::cout << "Всього студентів: " << arr.size() << "\n";
    std::cout << "Середній бал: " << static_cast<double>(sum) / arr.size() << "\n";

    int excellent = 0;
    for (int i = 90; i <= 100; i++) excellent += gradeCount[i];
    std::cout << "Відмінників (90-100 балів): " << excellent << " студентів\n";
    std::cout << "==================\n";
}

int main() {
    SetConsoleOutputCP(1251);
    srand(static_cast<unsigned>(time(0)));

    SortingAnalyzer* analyzer = SortingAnalyzer::getInstance();
    std::vector<Student> db;

    int choice;
    do {
        std::cout << "\n=== СИСТЕМА УПРАВЛІННЯ СТУДЕНТАМИ ===\n";
        std::cout << "1. Завантажити дані (згенерувати 10000)\n";
        std::cout << "2. Сортувати\n";
        std::cout << "3. Пошук\n";
        std::cout << "4. Статистика\n";
        std::cout << "5. Вихід\n";
        std::cout << "Виберіть опцію: ";
        std::cin >> choice;

        switch (choice) {
        case 1: {
            db = generateStudents(10000);
            std::cout << "Успішно згенеровано 10000 записів!\n";
            break;
        }
        case 2: {
            if (db.empty()) { std::cout << "Спочатку завантажте дані!\n"; break; }

            std::cout << "Сортувати за:\n1. ID (Radix Sort)\n2. Оцінкою (Counting Sort)\n3. Ім'ям (Merge Sort)\nВиберіть: ";
            int sortChoice;
            std::cin >> sortChoice;

            analyzer->resetMetrics();
            auto start = std::chrono::high_resolution_clock::now();

            if (sortChoice == 1) analyzer->radixSortById(db);
            else if (sortChoice == 2) analyzer->countingSortByGrade(db);
            else if (sortChoice == 3) analyzer->mergeSortByName(db);
            else { std::cout << "Невірний вибір.\n"; break; }

            auto end = std::chrono::high_resolution_clock::now();
            auto duration = std::chrono::duration_cast<std::chrono::milliseconds>(end - start).count();

            std::cout << "Виконано за " << duration << " мс\n";
            std::cout << "Порівнянь: " << analyzer->getComparisons() << "\n";
            std::cout << "Обмінів: " << analyzer->getSwaps() << "\n";
            break;
        }
        case 3: {
            if (db.empty()) { std::cout << "Спочатку завантажте дані!\n"; break; }

            std::cout << "Пошук за:\n1. ID (Interpolation Search)\n2. Оцінкою (Binary Range Search)\n3. Ім'ям (Binary Search)\nВиберіть: ";
            int searchChoice;
            std::cin >> searchChoice;

            if (searchChoice == 1) {
                int targetId;
                std::cout << "Введіть ID для пошуку: ";
                std::cin >> targetId;
                int pos = analyzer->interpolationSearchById(db, targetId);
                if (pos != -1) std::cout << "Знайдено: " << db[pos].name << " (Оцінка: " << db[pos].grade << ")\n";
                else std::cout << "Студента не знайдено (перевірте, чи відсортовано за ID).\n";
            }
            else if (searchChoice == 2) {
                int targetGrade;
                std::cout << "Введіть оцінку: ";
                std::cin >> targetGrade;
                auto range = analyzer->binarySearchRangeByGrade(db, targetGrade);
                if (range.first != -1) {
                    std::cout << "Знайдено студентів: " << (range.second - range.first + 1) << "\n";
                    std::cout << "Діапазон індексів: [" << range.first << " - " << range.second << "]\n";
                }
                else std::cout << "Студентів з такою оцінкою не знайдено (перевірте, чи відсортовано за оцінкою).\n";
            }
            else if (searchChoice == 3) {
                std::string targetName;
                std::cout << "Введіть ім'я (наприклад 'Шевченко Марія'): ";
                std::cin.ignore();
                std::getline(std::cin, targetName);
                int pos = analyzer->binarySearchByName(db, targetName);
                if (pos != -1) std::cout << "Знайдено! ID: " << db[pos].id << ", Оцінка: " << db[pos].grade << "\n";
                else std::cout << "Студента не знайдено (перевірте, чи відсортовано за ім'ям).\n";
            }
            break;
        }
        case 4:
            printStatistics(db);
            break;
        case 5:
            std::cout << "До побачення!\n";
            break;
        default:
            std::cout << "Невідома команда.\n";
        }
    } while (choice != 5);

    return 0;
}