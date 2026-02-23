#include <iostream>
#include "SortingAnalyzer.h"
using namespace std;

int main()
{
	SortingAnalyzer* analyzer = SortingAnalyzer::getInstance();
	//створюэмо вектор з 1000 елементів
	vector<int> NotSortedData = analyzer->generateData(50000, SortingAnalyzer::generatorType::Random);
	vector<int> SortedData = analyzer->generateData(50000, SortingAnalyzer::generatorType::Sorted);
	vector<int> ReversedSortedData = analyzer->generateData(50000, SortingAnalyzer::generatorType::Reversed);
	
	/*
	//аналізуємо сортування бульбашкою
	cout << "Bubble Sort:\n";
	cout << "Not Sorted Data:\n";
	analyzer->analyzeSort(NotSortedData, SortingAnalyzer::sortType::BubbleSort);
	cout << "Sorted Data:\n";
	analyzer->analyzeSort(SortedData, SortingAnalyzer::sortType::BubbleSort);
	cout << "Reversed Sorted Data:\n";
	analyzer->analyzeSort(ReversedSortedData, SortingAnalyzer::sortType::BubbleSort);

	//аналізуємо сортування вибором
	cout << "\nSelection Sort:\n";
	cout << "Not Sorted Data:\n";
	analyzer->analyzeSort(NotSortedData, SortingAnalyzer::sortType::SelectionSort);
	cout << "Sorted Data:\n";
	analyzer->analyzeSort(SortedData, SortingAnalyzer::sortType::SelectionSort);
	cout << "Reversed Sorted Data:\n";
	analyzer->analyzeSort(ReversedSortedData, SortingAnalyzer::sortType::SelectionSort);

	//аналізуємо сортування вставками
	cout << "\nInsertion Sort:\n";
	cout << "Not Sorted Data:\n";
	analyzer->analyzeSort(NotSortedData, SortingAnalyzer::sortType::InsertionSort);
	cout << "Sorted Data:\n";
	analyzer->analyzeSort(SortedData, SortingAnalyzer::sortType::InsertionSort);
	cout << "Reversed Sorted Data:\n";
	analyzer->analyzeSort(ReversedSortedData, SortingAnalyzer::sortType::InsertionSort);

	//аналізуємо сортування злиттям
	cout << "\nMerge Sort:\n";
	cout << "Not Sorted Data:\n";
	analyzer->analyzeSort(NotSortedData, SortingAnalyzer::sortType::MergeSort);
	cout << "Sorted Data:\n";
	analyzer->analyzeSort(SortedData, SortingAnalyzer::sortType::MergeSort);
	cout << "Reversed Sorted Data:\n";
	analyzer->analyzeSort(ReversedSortedData, SortingAnalyzer::sortType::MergeSort);

	//аналізуємо сортування швидке
	cout << "\nQuick Sort:\n";
	cout << "Not Sorted Data:\n";
	analyzer->analyzeSort(NotSortedData, SortingAnalyzer::sortType::QuickSort);
	cout << "Sorted Data:\n";
	analyzer->analyzeSort(SortedData, SortingAnalyzer::sortType::QuickSort);
	cout << "Reversed Sorted Data:\n";
	analyzer->analyzeSort(ReversedSortedData, SortingAnalyzer::sortType::QuickSort);

	//аналізуємо сортування купою
	cout << "\nHeap Sort:\n";
	cout << "Not Sorted Data:\n";
	analyzer->analyzeSort(NotSortedData, SortingAnalyzer::sortType::HeapSort);
	cout << "Sorted Data:\n";
	analyzer->analyzeSort(SortedData, SortingAnalyzer::sortType::HeapSort);
	cout << "Reversed Sorted Data:\n";
	analyzer->analyzeSort(ReversedSortedData, SortingAnalyzer::sortType::HeapSort);

	*/
	//аналізуємо гібридне сортування
	cout << "\nHybrid Sort:\n";
	cout << "Not Sorted Data:\n";
	analyzer->analyzeSort(NotSortedData, SortingAnalyzer::sortType::HybridSort);
	cout << "Sorted Data:\n";
	analyzer->analyzeSort(SortedData, SortingAnalyzer::sortType::HybridSort);
	cout << "Reversed Sorted Data:\n";
	analyzer->analyzeSort(ReversedSortedData, SortingAnalyzer::sortType::HybridSort);


}

