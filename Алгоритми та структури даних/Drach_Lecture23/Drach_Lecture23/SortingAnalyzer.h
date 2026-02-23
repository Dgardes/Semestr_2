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
				mergeSort(data, 0, data.size() - 1);
				break;
			case QuickSort:
				quickSort(data, 0, data.size() - 1);
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

	}
	private: void insertionSort(std::vector<int>& data)
	{

	}
	private: void mergeSort(std::vector<int>& data, int left, int right)
	{

	}
	private: void quickSort(std::vector<int>& data, int low, int high)
	{

	}
	private: void heapSort(std::vector<int>& data)
	{

	}

};
inline SortingAnalyzer* SortingAnalyzer::instance = nullptr;
