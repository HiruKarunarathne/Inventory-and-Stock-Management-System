package com.intellij.InventoryAndStockManagementSystem.util;

import com.intellij.InventoryAndStockManagementSystem.model.Item;
import java.util.*;

public class SortUtil {
    public static List<Item> mergeSortByExpiryDate(List<Item> items) {
        if (items.size() <= 1) return items;

        int mid = items.size() / 2;
        List<Item> left = mergeSortByExpiryDate(items.subList(0, mid));
        List<Item> right = mergeSortByExpiryDate(items.subList(mid, items.size()));

        return merge(left, right);
    }

    private static List<Item> merge(List<Item> left, List<Item> right) {
        List<Item> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).getExpiryDate().compareTo(right.get(j).getExpiryDate()) <= 0) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }
        while (i < left.size()) result.add(left.get(i++));
        while (j < right.size()) result.add(right.get(j++));
        return result;
    }
}
