#include <iostream>
#include <vector>
#include <windows.h>

using namespace std;

//ітеративний бінарний пошук 
int binarySearch(const vector<int>& arr, int target, int& iterations) 
{
    int left = 0;
    int right = (int)arr.size() - 1;
    iterations = 0;

    while (left <= right) {
        iterations++;
        int mid = left + (right - left) / 2;

        if (arr[mid] == target) return mid;
        if (arr[mid] < target) left = mid + 1;
        else right = mid - 1;
    }
    return -1;
}

//інтерполяційний пошук
int interpolationSearch(const vector<int>& arr, int target, int& iterations) 
{
    int left = 0;
    int right = (int)arr.size() - 1;
    iterations = 0;

    while (left <= right && target >= arr[left] && target <= arr[right]) 
    {
        iterations++;
        if (arr[left] == arr[right]) 
        {
            if (arr[left] == target) return left;
            return -1;
        }

        //формула інтерполяції
        int pos = left + (int)((double)(target - arr[left]) * (right - left) / (arr[right] - arr[left]));

        if (arr[pos] == target) return pos;
        if (arr[pos] < target) left = pos + 1;
        else right = pos - 1;
    }
    return -1;
}

//пошук першого та останнього входження для підрахунку 
int findFirst(const vector<int>& arr, int target) 
{
    int left = 0, right = (int)arr.size() - 1, res = -1;
    while (left <= right) 
    {
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) { res = mid; right = mid - 1; }
        else if (arr[mid] < target) left = mid + 1;
        else right = mid - 1;
    }
    return res;
}

int findLast(const vector<int>& arr, int target) 
{
    int left = 0, right = (int)arr.size() - 1, res = -1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) { res = mid; left = mid + 1; }
        else if (arr[mid] < target) left = mid + 1;
        else right = mid - 1;
    }
    return res;
}

int countOccurrences(const vector<int>& arr, int target) 
{
    int first = findFirst(arr, target);
    if (first == -1) return 0;
    int last = findLast(arr, target);
    return last - first + 1;
}

int main() {
    SetConsoleOutputCP(1251); // кодування

    //тест №1
    vector<int> testArr = { 2, 5, 8, 12, 16, 23, 38, 45, 67, 72 };
    int targets[] = { 8, 23, 72, 99 };
    int iters = 0;

    cout << "Тестування Бінарного пошуку :" << endl;
    for (int t : targets) {
        int res = binarySearch(testArr, t, iters);
        if (res != -1) cout << "Ціль " << t << " знайдена на індексі: " << res << endl;
        else cout << "Ціль " << t << " не знайдена." << endl;
    }

    // Тест №2. підрахунок дублікатів
    cout << "\nПідрахунок входжень:" << endl;
    vector<int> dupArr = { 1, 2, 2, 2, 3, 3, 4, 5 };
    cout << "Число 2 зустрічається: " << countOccurrences(dupArr, 2) << " раз" << endl;

    // Тест №3. порівняння ітерацій 
    cout << "\nПорівняння Бінарного та Інтерполяційного пошуку:" << endl;
    vector<int> largeArr;
    for (int i = 0; i < 1000; i++) largeArr.push_back(i * 2);

    int searchVal = 888;
    int bIters, iIters;
    binarySearch(largeArr, searchVal, bIters);
    interpolationSearch(largeArr, searchVal, iIters);

    cout << "Пошук числа " << searchVal << " у масиві з 1000 елементів:" << endl;
    cout << "Бінарний пошук: " << bIters << " ітерацій." << endl;
    cout << "Інтерполяційний пошук: " << iIters << " ітерацій." << endl;

    return 0;
}