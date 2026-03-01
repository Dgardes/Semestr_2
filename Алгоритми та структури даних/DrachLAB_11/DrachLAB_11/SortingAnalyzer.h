#pragma once
#include <vector>
#include <algorithm>
#include <random>
#include <chrono>
#include <iostream>

class SortingAnalyzer
{
	//змінні
	private: int comparisons = 0;
	private: int swaps = 0;

	//таймер для вимірювання часу виконання
	private: std::chrono::high_resolution_clock::time_point startTime;

	//реалізація сінглтона
	static SortingAnalyzer* instance;
	private: SortingAnalyzer() {}

	public: static SortingAnalyzer* getInstance()
	{
		if (instance == nullptr)
		{
			instance = new SortingAnalyzer();
		}
		return instance;
	}

	public: enum generatorType
	{
		Random,
		Sorted,
		Reversed
	};

	public: enum sortType
	{
		BubbleSort,
		SelectionSort,
		InsertionSort,
		MergeSort,
		QuickSort,
		HeapSort,
		HybridSort,
		IterativeQuickSort,
		ThreeWayQuickSort,
		HybridQuickSort
	};
	
	public: template<typename T> std::vector<T> generateData(int size, generatorType type)
	{
		// генеруємо вектор з послідовними числами 
		std::vector<T> data(size);
		for (int i = 0; i < size; ++i)
			data[i] = static_cast<T>(i);

		// створюємо генератор випадкових чисел
		std::random_device rd;
		std::mt19937 g(rd());

		switch (type)
		{
		case Sorted:
			//повертаємо відсортований вектор
			return data;
		case Reversed:
			//перевертаємо вектор
			std::reverse(data.begin(), data.end());
			return data;
		case Random:
			//перемішуємо вектор випадковим чином
			std::shuffle(data.begin(), data.end(), g);
			return data;
		default:
			return {};
		}

	};
	
	public: template<typename T> void analyzeSort(std::vector<T>& data, sortType type)
	{
		std::vector<T> tempData = data;
		//запускаємо таймер
		startTime = std::chrono::high_resolution_clock::now();
		switch (type)
		{
		case BubbleSort:
			bubbleSort(tempData);
			break;
		case SelectionSort:
			selectionSort(tempData);
			break;
		case InsertionSort:
			insertionSort(tempData);
			break;
		case MergeSort:
			mergeSort(tempData, 0, (int)tempData.size() - 1);
			break;
		case QuickSort:
			quickSort(tempData);
			break;
		case HeapSort:
			heapSort(tempData);
			break;
		case HybridSort:
			hybridSort(tempData);
			break;
		case IterativeQuickSort:
			iterativeQuickSort(tempData, 0, (int)tempData.size() - 1);
			break;
		case ThreeWayQuickSort:
			threeWayQuickSort(tempData, 0, (int)tempData.size() - 1);
			break;
		case HybridQuickSort:
			hybridQuickSort(tempData, 0, (int)tempData.size() - 1);
			break;
		default:
			return;
		}
		//зупиняємо таймер і виводимо результати
		auto endTime = std::chrono::high_resolution_clock::now();
		auto duration = std::chrono::duration_cast<std::chrono::milliseconds>(endTime - startTime).count();
		std::cout << "Sorting completed in " << duration << " ms\n";
		std::cout << "Comparisons: " << comparisons << "\n";
		std::cout << "Swaps: " << swaps << "\n";
		resetMetrics();// скидаємо лічильники після сортування
	};

	public: void resetMetrics()
	{
		comparisons = 0;
		swaps = 0;
	};

	//методи сортування
	//бульбашкове сортування з використанням дженериків для підтримки різних типів даних
	private: template<typename T> void bubbleSort(std::vector<T>& data) { 
		for (size_t i = 0; i < data.size() - 1; i++) 
		{
			bool swapped = false;
			for (size_t j = 0; j < data.size() - 1 - i; j++) 
			{
				comparisons++;
				if (data[j] > data[j + 1]) 
				{
					std::swap(data[j], data[j + 1]);
					swaps++;
					swapped = true;
				}
			}
			if (!swapped) break;
		}
	}
	
	private: template<typename T> void selectionSort(std::vector<T>& data)
	{
		// Проходимо по всьому масиву
		for (int i = 0; i < data.size() - 1; i++) {
			// Знаходимо індекс мінімального елемента
			int minIndex = i;
			for (int j = i + 1; j < data.size(); j++) {
				comparisons++; //збільшуємо лічильник порівнянь
				if (data[j] < data[minIndex])
				{
					minIndex = j;
				}
			}
			// Міняємо мінімальний елемент з поточним
			if (minIndex != i) {
				//swap(arr[i], arr[minIndex]);
				T temp = data[i];
				data[i] = data[minIndex];
				data[minIndex] = temp;
				swaps++; //збільшуємо лічильник обмінів
			}
		}
	}
	
	private: template<typename T> void insertionSort(std::vector<T>& data)
	{
		// Починаємо з другого елемента (перший вже "відсортований")
		for (int i = 1; i < data.size(); i++)
		{
			T key = data[i]; // Елемент, який потрібно вставити
			int j = i - 1;
			// Зсуваємо елементи більші за key на одну позицію вправо
			comparisons++; //збільшуємо лічильник порівнянь
			while (j >= 0 && data[j] > key)
			{
				comparisons++; //збільшуємо лічильник порівнянь
				swaps++; //збільшуємо лічильник обмінів
				data[j + 1] = data[j];
				j--;
			}
			// Вставляємо key на правильну позицію
			data[j + 1] = key;
		}
	}
	
	private: template<typename T> void mergeSort(std::vector<T>& data, int left, int right)
	{
		mergeSortRecursive(data, 0, (int)data.size() - 1);
	}

	
	private: template<typename T> void merge(std::vector<T>& data, int left, int mid, int right)
	{
		int n1 = mid - left + 1;
		int n2 = right - mid;

		//створюємо тимчасові вектори
		std::vector<T> L(n1), R(n2);

		for (int i = 0; i < n1; i++) L[i] = data[left + i];
		for (int j = 0; j < n2; j++) R[j] = data[mid + 1 + j];

		int i = 0, j = 0, k = left;

		//зливаємо тимчасові вектори назад у data
		while (i < n1 && j < n2)
		{
			comparisons++; //збільшуємо лічильник порівнянь
			if (L[i] <= R[j]) {
				data[k] = L[i];
				i++;
			}
			else {
				data[k] = R[j];
				j++;
			}
			k++;
			swaps++; //
		}

		//додаємо залишки, якщо вони є
		while (i < n1)
		{
			data[k] = L[i];
			i++; k++; swaps++;
		}
		while (j < n2)
		{
			data[k] = R[j];
			j++; k++; swaps++;
		}
	}

	
	private: template<typename T> void mergeSortRecursive(std::vector<T>& data, int left, int right)
	{
		if (left < right)
		{
			int mid = left + (right - left) / 2;

			mergeSortRecursive(data, left, mid); //сортуємо ліву частину
			mergeSortRecursive(data, mid + 1, right); //сортуємо праву частину

			merge(data, left, mid, right); //зливаємо їх
		}
	}

	
	private: template<typename T> void quickSort(std::vector<T>& data)
	{
		data = recursiveQuickSort(data);
	}

	
	private: template<typename T> std::vector<T> recursiveQuickSort(std::vector<T>& data)
	{
		if (data.size() <= 1)
		{
			return data;
		}
		T pivot = data[data.size() / 2]; //вибираємо опорний елемент
		std::vector<T> left; //елементи менші за pivot
		std::vector<T> right; //елементи більші за pivot
		for (int i = 0; i < data.size(); i++)
		{
			comparisons++; //збільшуємо лічильник порівнянь
			if (data[i] < pivot)
			{
				left.push_back(data[i]);
				swaps++; //збільшуємо лічильник обмінів
			}
			else if (data[i] > pivot)
			{
				right.push_back(data[i]);
				swaps++; //збільшуємо лічильник обмінів
			}
			else
			{
				swaps++; //збільшуємо лічильник обмінів для елементів, рівних pivot
			}
		}
		std::vector<T> sortedLeft = recursiveQuickSort(left);
		std::vector<T> sortedRight = recursiveQuickSort(right);

		sortedLeft.insert(sortedLeft.end(), pivot); //додаємо pivot між відсортованими частинами
		sortedLeft.insert(sortedLeft.end(), sortedRight.begin(), sortedRight.end());

		return sortedLeft;
	}
	// Основна функція heap sort
	
	private: template<typename T> void heapSort(std::vector<T>& data)
	{
		// Крок 1: Будуємо max heap
		// Починаємо з останнього не-листового вузла
		for (int i = data.size() / 2 - 1; i >= 0; i--)
		{
			heapify(data, data.size(), i);
		}

		// Крок 2: Витягуємо елементи з купи один за одним
		for (int i = data.size() - 1; i > 0; i--)
		{
			// Переміщуємо поточний корінь в кінець
			T temp = data[0];
			data[0] = data[i];
			data[i] = temp;

			// викликаємо heapify на зменшеній купі
			heapify(data, i, 0);
		}
	}

	private: template<typename T> void heapify(std::vector<T>& arr, int n, int i)
	{
		comparisons++;
		int largest = i; // Ініціалізуємо найбільший як корінь
		int left = 2 * i + 1; // Лівий дочірній
		int right = 2 * i + 2; // Правий дочірній
		// Якщо лівий дочірній більший за корінь
		if (left < n && arr[left] > arr[largest])
		{
			largest = left;
		}
		// Якщо правий дочірній більший за найбільший
		if (right < n && arr[right] > arr[largest])
		{
			largest = right;
		}
		// Якщо найбільший не корінь

		if (largest != i)
		{
			swaps++;
			T temp = arr[i];
			arr[i] = arr[largest];
			arr[largest] = temp;
			// Рекурсивно heapify уражену підкупу
			heapify(arr, n, largest);
		}
	}
	private: template<typename T> void hybridSort(std::vector<T>& data)
	{
		hybridSortRecursive(data, 0, (int)data.size() - 1);
	}

	private: template<typename T> void hybridSortRecursive(std::vector<T>& data, int left, int right)
	{
		// ПОРІГ: якщо елементів 15 або менше — використовуємо вставку
		if (right - left <= 15)
		{
			// Звичайна логіка Insertion Sort для шматочка масиву
			for (int i = left + 1; i <= right; i++) {
				T key = data[i];
				int j = i - 1;
				while (j >= left && data[j] > key) {
					comparisons++;
					swaps++;
					data[j + 1] = data[j];
					j--;
				}
				data[j + 1] = key;
			}
			return;
		}

		// Якщо масив великий — продовжуємо Merge Sort
		int mid = left + (right - left) / 2;
		hybridSortRecursive(data, left, mid);
		hybridSortRecursive(data, mid + 1, right);
		merge(data, left, mid, right); // Використовуємо твій існуючий merge
	}

	//
	//
	// методи для сортування з використанням ітеративного Quick Sort, 
	// трьохстороннього Quick Sort та гібридного Quick Sort.
	//
	//

	private:
    //завданяя 5.1 – медіана трьох 
    template<typename T>
    int medianOfThree(std::vector<T>& arr, int low, int high) {
        int mid = low + (high - low) / 2;
        if (arr[low] > arr[mid]) std::swap(arr[low], arr[mid]);
        if (arr[low] > arr[high]) std::swap(arr[low], arr[high]);
        if (arr[mid] > arr[high]) std::swap(arr[mid], arr[high]);
        //міняємо медіану з передостаннім елементом, як у прикладі
        std::swap(arr[mid], arr[high - 1]);
        swaps += 4; comparisons += 3;
        return high - 1;
    }

    //завдання 5.2 – гібридний QuickSort 
    template<typename T>
    void hybridQuickSort(std::vector<T>& arr, int low, int high) {
        const int THRESHOLD = 16;
        if (high - low < THRESHOLD) {
			// використання логіки Insertion Sort для маленьких підмасивів
            for (int i = low + 1; i <= high; i++) {
                T key = arr[i];
                int j = i - 1;
                while (j >= low && arr[j] > key) {
                    comparisons++; swaps++;
                    arr[j + 1] = arr[j];
                    j--;
                }
                arr[j + 1] = key;
            }
            return;
        }
        
        int pi = partitionSimple(arr, low, high);
        hybridQuickSort(arr, low, pi - 1);
        hybridQuickSort(arr, pi + 1, high);
    }

    // допоміжний метод partition для роботи на місці без нових векторів
    template<typename T>
    int partitionSimple(std::vector<T>& arr, int low, int high) {
        T pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            comparisons++;
            if (arr[j] < pivot) {
                i++;
                std::swap(arr[i], arr[j]);
                swaps++;
            }
        }
        std::swap(arr[i + 1], arr[high]);
        swaps++;
        return i + 1;
    }

    //завдання 5.3 – Ітеративний QuickSort
    template<typename T>
    void iterativeQuickSort(std::vector<T>& arr, int low, int high) {
        std::vector<int> stack(high - low + 1);
        int top = -1;

        stack[++top] = low;
        stack[++top] = high;

        while (top >= 0) {
            int h = stack[top--];
            int l = stack[top--];

            int pi = partitionSimple(arr, l, h);

            if (pi - 1 > l) {
                stack[++top] = l;
                stack[++top] = pi - 1;
            }
            if (pi + 1 < h) {
                stack[++top] = pi + 1;
                stack[++top] = h;
            }
        }
    }

    //завдання 5.4 – Three-way QuickSort
    template<typename T>
    void threeWayQuickSort(std::vector<T>& arr, int low, int high) {
        if (low >= high) return;

        T pivot = arr[low];
        int lt = low, gt = high, i = low + 1;

        while (i <= gt) {
            comparisons++;
            if (arr[i] < pivot) {
                std::swap(arr[lt++], arr[i++]);
                swaps++;
            }
            else if (arr[i] > pivot) {
                std::swap(arr[i], arr[gt--]);
                swaps++;
            }
            else {
                i++;
            }
        }
        threeWayQuickSort(arr, low, lt - 1);
        threeWayQuickSort(arr, gt + 1, high);
    }
};
inline SortingAnalyzer* SortingAnalyzer::instance = nullptr;
