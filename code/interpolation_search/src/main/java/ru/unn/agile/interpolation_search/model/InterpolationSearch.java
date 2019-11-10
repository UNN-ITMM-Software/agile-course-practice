package ru.unn.agile.interpolation_search.model;

import java.util.NoSuchElementException;

public class InterpolationSearch {

    public <T extends Comparable<T>> int find(final T[] array, final T value) {
        final Integer[] arrayInt = (Integer[])array;
        final Integer valueInt = (Integer)value;
        if (!isSortedArray(arrayInt)) {
            throw new IllegalArgumentException("Array is not sorted");
        }
        return search(arrayInt, valueInt);
    }
    private boolean isSortedArray(final Integer[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                return false;
            }
        }
        return true;
    }

    private int search(final Integer[] array, final Integer value) {
        int start = 0, end = array.length - 1;
        while (start <= end && value >= array[start] && value <= array[end]) {
            int offset = (start == end) ? 0 : getSearchOffset(array, start, end, value);
            int pos = start + offset;
            if (array[pos].equals(value)) {
                return pos;
            }
            if (array[pos] < value) {
                start = pos + 1;
            } else {
                end = pos - 1;
            }
        }
        throw new NoSuchElementException("Cannot find element '" + value + "' in array.");
    }

    private int getSearchOffset(final Integer[] array, final int startIndex,
                                final int endIndex, final int findElement) {
        int range = array[endIndex] - array[startIndex];
        if (range == 0) {
            range = 1;
        }
        return (((endIndex - startIndex) / range) * (findElement - array[endIndex]));
    }
}