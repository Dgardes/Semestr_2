#include <iostream>
#include <vector>
#include "SortingAnalyzer.h"
#include <Windows.h>

using namespace std;

//завдання 3.1 – функція partition для квік сорту
//вона вибирає останній елемент як опорний, розділяє масив на дві частини і повертає індекс опорного елемента після розділення
int partition(int arr[], int low, int high) {
    int pivot = arr[high];
    int i = low - 1;
    for (int j = low; j < high; j++) {
        if (arr[j] < pivot) {
            i++;
            std::swap(arr[i], arr[j]);
        }
    }
    std::swap(arr[i + 1], arr[high]);
    return i + 1;
}

//завдання 3.2 – квік сорт алгоритм з використанням функції partition
// він рекурсивно сортує підмасиви, розділені опорним елементом
void quickSort(int arr[], int low, int high) 
{
    if (low < high) 
    {
        int pi = partition(arr, low, high);
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}

//завдання 3.3 – функція злиття підмасивів
// вона зливає два відсортовані підмасиви в один відсортований масив
void merge(int arr[], int left, int mid, int right) {
    int n1 = mid - left + 1;
    int n2 = right - mid;

    // Створюємо тимчасові динамічні масиви
    int* L = new int[n1];
    int* R = new int[n2];

    // Копіюємо дані у тимчасові масиви
    for (int i = 0; i < n1; i++) L[i] = arr[left + i];
    for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

    int i = 0, j = 0, k = left;

    // Порівнюємо елементи та записуємо менший назад в основний масив
    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) {
            arr[k++] = L[i++];
        }
        else {
            arr[k++] = R[j++];
        }
    }

    // Дописуємо залишки, якщо вони є
    while (i < n1) arr[k++] = L[i++];
    while (j < n2) arr[k++] = R[j++];
    delete[] L;
    delete[] R;
}

//завдання 3.4 – рекурсивний алгоритм мердж сорт
// він розділяє масив на дві половини, рекурсивно сортує їх і зливає назад
void mergeSort(int arr[], int left, int right) {
    if (left < right) {
        int mid = left + (right - left) / 2;

        mergeSort(arr, left, mid); //рекурсія для лівої частини
        mergeSort(arr, mid + 1, right); //рекурсія для правої частини

        merge(arr, left, mid, right); //злиття результатів
    }
}

//допоміжна функція для виведення масиву
void printArray(const string& label, int arr[], int size) {
    cout << label << ": ";
    for (int i = 0; i < size; i++) {
        cout << arr[i] << " ";
    }
    cout << endl;
}

int main() 
{
	SetConsoleOutputCP(1251);
	SortingAnalyzer* analyzer = SortingAnalyzer::getInstance(); // отримуємо інстанс сінглтона

    /*
	//завдання 3.1 - тестування функції partition на випадковому масиві
	cout << "Завдання 3.1 - тестування функції partition на випадковому масиві\n";
	std::vector<int> data = analyzer->generateData<int>(10, SortingAnalyzer::Random); //генеруємо випадкові дані

    cout << "до реалізації функції partition: ";
    for (int x : data) cout << x << " ";
    cout << endl;

    int pivotIndex = partition(data.data(), 0, (int)data.size() - 1);

    cout << "після реалізації функції partition:  ";
    for (int x : data) cout << x << " ";
    cout << "\nPivot index: " << pivotIndex << endl;


	//завдання 3.2 - тестування алгоритму швидкого сортування на різних типах масивів
	cout << "\nЗавдання 3.2 - тестування алгоритму швидкого сортування на різних типах масивів\n";
	// 1. Тест на випадковому масиві
    int randomArr[] = { 45, 23, 11, 89, 77, 98, 4, 28, 65, 43 };
    int n1 = sizeof(randomArr) / sizeof(randomArr[0]);
    cout << "Випадковий масив: " << endl;
    printArray("До", randomArr, n1);
    quickSort(randomArr, 0, n1 - 1);
    printArray("Після", randomArr, n1);
    cout << endl;

    // 2. Тест на відсортованому масиві
    int sortedArr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    int n2 = sizeof(sortedArr) / sizeof(sortedArr[0]);
    cout << "Відсортований масив:" << endl;
    printArray("До", sortedArr, n2);
    quickSort(sortedArr, 0, n2 - 1);
    printArray("Після", sortedArr, n2);
    cout << endl;

    // 3. Тест на зворотному масиві
    int reverseArr[] = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
    int n3 = sizeof(reverseArr) / sizeof(reverseArr[0]);
    cout << "Зворотний масив:" << endl;
    printArray("До", reverseArr, n3);
    quickSort(reverseArr, 0, n3 - 1);
    printArray("Після", reverseArr, n3);
    cout << endl;
    

	//завдання 3.3, 3.4 - тестування функції злиття на двох відсортованих підмасивах
	cout << "\nЗавдання 3.3, 3.4 - тестування функції злиття на двох відсортованих підмасивах\n";
    // 1. Тест на випадковому масиві
    int mArr1[] = { 45, 23, 11, 89, 77, 98, 4, 28, 65, 43 };
    int mn1 = sizeof(mArr1) / sizeof(mArr1[0]);
    cout << "\nВипадковий масив:" << endl;
    printArray("До", mArr1, mn1);
    mergeSort(mArr1, 0, mn1 - 1);
    printArray("Після", mArr1, mn1);

    // 2. Тест на відсортованому масиві
    int mArr2[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    int mn2 = sizeof(mArr2) / sizeof(mArr2[0]);
    cout << "\nВідсортований масив (Best Case):" << endl;
    printArray("До", mArr2, mn2);
    mergeSort(mArr2, 0, mn2 - 1);
    printArray("Після", mArr2, mn2);

    // 3. Тест на зворотному масиві
    int mArr3[] = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
    int mn3 = sizeof(mArr3) / sizeof(mArr3[0]);
    cout << "\nЗворотний масив (Worst Case):" << endl;
    printArray("До", mArr3, mn3);
    mergeSort(mArr3, 0, mn3 - 1);
    printArray("Після", mArr3, mn3);

    */

    //
    //
    //

    /*

	//завдання 4.1, 4.2, 4.3 - тестування алгоритмів сортування на різних типах даних та розмірах
    //масив розмірів для тестування
    std::vector<int> sizes = { 100, 1000, 10000 };

    // тест на типі int
	cout << "\nТестування алгоритмів сортування на типі int:" << endl;
    for (int size : sizes) 
    {
        //uенеруємо випадкові дані типу int
        auto data = analyzer->generateData<int>(size, SortingAnalyzer::Random);

        //nестуємо алгоритми 
        cout << "Quick Sort:" << " elements count: " << size << endl;
        analyzer->analyzeSort(data, SortingAnalyzer::QuickSort);
        cout << "Merge Sort:" << " elements count: " << size << endl;
        analyzer->analyzeSort(data, SortingAnalyzer::MergeSort);
    }

	// тест на типі double
	cout << "\nТестування алгоритмів сортування на типі double:" << endl;
    for (int size : sizes) 
    {
        //генеруємо випадкові дані типу double
        auto data = analyzer->generateData<double>(size, SortingAnalyzer::Random);

        cout << "Quick Sort:" << " elements count: " << size << endl;
        analyzer->analyzeSort(data, SortingAnalyzer::QuickSort);
        cout << "Merge Sort:" << " elements count: " << size << endl;
        analyzer->analyzeSort(data, SortingAnalyzer::MergeSort);

    }

	// тести на відсортованих та зворотних даних
    cout << "\nтест на відсортованих даних (10 000 ел.)" << endl;
    auto sortedData = analyzer->generateData<int>(10000, SortingAnalyzer::Sorted);
	cout << "Quick Sort:" << endl;
    analyzer->analyzeSort(sortedData, SortingAnalyzer::QuickSort);
	cout << "Merge Sort:" << endl;
    analyzer->analyzeSort(sortedData, SortingAnalyzer::MergeSort);

    cout << "\nтест на відсортованих даних у зворотньму порядку (10 000 ел.)" << endl;
    auto reversedData = analyzer->generateData<int>(10000, SortingAnalyzer::Reversed);
    cout << "Quick Sort:" << endl;
    analyzer->analyzeSort(reversedData, SortingAnalyzer::QuickSort);
    cout << "Merge Sort:" << endl;
    analyzer->analyzeSort(reversedData, SortingAnalyzer::MergeSort);

    */

	// частина 5.1, 5.2, 5.3, 5.4 - тестування алгоритмів сортування з використанням ітеративного Quick Sort, 
    // трьохстороннього Quick Sort та гібридного Quick Sort

    //тест на випадкових даних для нових методів
    cout << "\nТестування оптимізованих алгоритмів (10 000 ел.):" << endl;
    auto advData = analyzer->generateData<int>(10000, SortingAnalyzer::Random);

    cout << "Hybrid Quick Sort (Quick + Insertion):" << endl;
    analyzer->analyzeSort(advData, SortingAnalyzer::HybridQuickSort);

    cout << "Iterative Quick Sort (Без рекурсії):" << endl;
    analyzer->analyzeSort(advData, SortingAnalyzer::IterativeQuickSort);

    //спеціальний тест для Three-way QuickSort
    cout << "\nТест на масиві з великою кількістю дублікатів (10 000 ел.):" << endl;
    std::vector<int> dupData;
    for (int i = 0; i < 10000; i++) dupData.push_back(rand() % 5); // лише 5 унікальних значень

    cout << "Standard Quick Sort (для порівняння):" << endl;
    analyzer->analyzeSort(dupData, SortingAnalyzer::QuickSort);

    cout << "Three-way Quick Sort (Алгоритм Дейкстри):" << endl;
    analyzer->analyzeSort(dupData, SortingAnalyzer::ThreeWayQuickSort);

    // Тест шаблонів для нових методів (на типі double)
    cout << "\nТестування Hybrid Quick Sort на типі double (5 000 ел.):" << endl;
    auto doubleAdvData = analyzer->generateData<double>(5000, SortingAnalyzer::Random);
    analyzer->analyzeSort(doubleAdvData, SortingAnalyzer::HybridQuickSort);

    return 0;
}