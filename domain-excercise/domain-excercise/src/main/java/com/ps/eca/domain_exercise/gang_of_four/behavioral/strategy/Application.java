package com.ps.eca.domain_exercise.gang_of_four.behavioral.strategy;

public class Application {
    public static void main(String[] args) {
        int[] array = {7, 2, 5, 1, 9, 3};

        SortContext sortContext = new SortContext();

        // Use Bubble Sort strategy
        sortContext.setSortingStrategy(new BubbleSort());
        sortContext.performSort(array.clone());

        // Use Quick Sort strategy
        sortContext.setSortingStrategy(new QuickSort());
        sortContext.performSort(array.clone());

        // Use Merge Sort strategy
        sortContext.setSortingStrategy(new MergeSort());
        sortContext.performSort(array.clone());
    }
}
