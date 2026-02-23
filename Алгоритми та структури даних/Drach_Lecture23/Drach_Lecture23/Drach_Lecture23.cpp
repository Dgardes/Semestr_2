#include <iostream>
#include "SortingAnalyzer.h"
using namespace std;

int main()
{
	SortingAnalyzer* analyzer = SortingAnalyzer::getInstance();
	//створюэмо вектор з 1000 елементів
	vector<int> data = analyzer->generateData(1000, SortingAnalyzer::generatorType::Random);
	//аналізуємо сортування бульбашкою
	analyzer->analyzeSort(data, SortingAnalyzer::sortType::BubbleSort);
}

