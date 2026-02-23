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
		{ instance = new SortingAnalyzer(); }
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
		HeapSort
	};

	public: std::vector<int> generateData(int size, generatorType type)
	{
		// генеруємо вектор з послідовними числами 
		std::vector<int> data(size);
		for (int i = 0; i < size; ++i)
			data[i] = i;

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

	public: void analyzeSort(std::vector<int>& data, sortType type)
	{
		//запускаємо таймер
		startTime = std::chrono::high_resolution_clock::now();
		switch (type)
		{
			case BubbleSort:
				bubbleSort(data);
				break;
			case SelectionSort:
				selectionSort(data);
				break;
			case InsertionSort:
				insertionSort(data);
				break;
			case MergeSort:
				mergeSort(data, 0, (int)data.size() - 1);
				break;
			case QuickSort:
				quickSort(data);
				break;
			case HeapSort:
				heapSort(data);
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
	};

	public: void resetMetrics()
	{
		comparisons = 0;
		swaps = 0;
	};

	//методи сортування
	private: void bubbleSort(std::vector<int>& data)
	{
		for (int i = 0; i < data.size() - 1; i++) {
			bool swapped = false; // Прапорець для відстеження обмінів
			for (int j = 0; j < data.size() - 1 - i; j++) 
			{
				comparisons++; //збільшуємо лічильник порівнянь
				if (data[j] <  data[j + 1]) 
				{
					int temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = temp;
					swapped = true;
					swaps++; //збільшуємо лічильник обмінів
				}
			}
			// Якщо не було обмінів, масив вже відсортований
			if (!swapped) 
			{ break; }
		}
	}

	private: void selectionSort(std::vector<int>& data)
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
				int temp = data[i];
				data[i] = data[minIndex];
				data[minIndex] = temp;
				swaps++; //збільшуємо лічильник обмінів
			}
		}
	}
	private: void insertionSort(std::vector<int>& data)
	{
		// Починаємо з другого елемента (перший вже "відсортований")
		for (int i = 1; i < data.size(); i++) 
		{
			int key = data[i]; // Елемент, який потрібно вставити
			int j = i - 1;
			// Зсуваємо елементи більші за key на одну позицію вправо
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

	private: void mergeSort(std::vector<int>& data, int left, int right)
	{
		mergeSortRecursive(data, 0, (int)data.size() - 1);
	}

	private: void merge(std::vector<int>& data, int left, int mid, int right) 
	{
		int n1 = mid - left + 1;
		int n2 = right - mid;

		//створюємо тимчасові вектори
		std::vector<int> L(n1), R(n2);

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

	void mergeSortRecursive(std::vector<int>& data, int left, int right) 
	{
		if (left < right) 
		{
			int mid = left + (right - left) / 2;

			mergeSortRecursive(data, left, mid); //сортуємо ліву частину
			mergeSortRecursive(data, mid + 1, right); //сортуємо праву частину

			merge(data, left, mid, right); //зливаємо їх
		}
	}

	private: void quickSort(std::vector<int>& data)
	{
		vector<int> sortedData = recursiveQuickSort(data);
	}

	private: vector<int> recursiveQuickSort(std::vector<int>& data)
	{
		if (data.size() <= 1) 
		{
			return data;
		}
		int pivot = data[data.size() / 2]; //вибираємо опорний елемент
		std::vector<int> left; //елементи менші за pivot
		std::vector<int> right; //елементи більші за pivot
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
		std::vector<int> sortedLeft = recursiveQuickSort(left);
		std::vector<int> sortedRight = recursiveQuickSort(right);
		
		sortedLeft.insert(sortedLeft.end(), pivot); //додаємо pivot між відсортованими частинами
		sortedLeft.insert(sortedLeft.end(), sortedRight.begin(), sortedRight.end());
		
		return sortedLeft;
	}
	// Основна функція heap sort
	private: void heapSort(std::vector<int>& data)
	{
		// Крок 1: Будуємо max heap
		// Починаємо з останнього не-листового вузла
		for (int i = data.size() / 2 - 1; i <= 0; i--) 
		{ heapify(data, data.size(), i); }

		// Крок 2: Витягуємо елементи з купи один за одним
		for (int i = data.size() - 1; i > 0; i--) 
		{
			// Переміщуємо поточний корінь в кінець
			int temp = data[0];
			data[0] = data[i];
			data[0] = temp;

			// викликаємо heapify на зменшеній купі
			heapify(data, i, 0);
		}
	}

	void heapify(vector<int> arr, int n, int i) 
	{
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
			swap(arr[i], arr[largest]);
			int temp = arr[i];
			arr[i] = arr[largest];
			arr[largest] = temp;
			// Рекурсивно heapify уражену підкупу
			heapify(arr, n, largest);
		}
	}

};
inline SortingAnalyzer* SortingAnalyzer::instance = nullptr;
