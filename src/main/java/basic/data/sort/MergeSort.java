package basic.data.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// https://www.youtube.com/watch?v=3j0SWDX4AtU
public class MergeSort {
    public static List<Integer> mergeSort(List<Integer> list){
        if(list.size() <= 1) return list;

        int mid = list.size() / 2;
        List<Integer> leftArray = mergeSort(new ArrayList<>(list.subList(0, mid)));
        List<Integer> rightArray = mergeSort(new ArrayList<>(list.subList(mid, list.size())));

        return merge(leftArray, rightArray);
    }


    public static List<Integer> merge(List<Integer> leftArray, List<Integer> rightArray){
        List<Integer> result = new ArrayList<>();
        int leftIdx = 0;
        int rightIdx = 0;

        while(leftIdx < leftArray.size() && rightArray.size() > rightIdx) {
            if(leftArray.get(leftIdx) < rightArray.get(rightIdx)) {
                result.add(leftArray.get(leftIdx++));
            } else {
                result.add(rightArray.get(rightIdx++));
            }
        }

        while(leftArray.size() > leftIdx) {
            result.add(leftArray.get(leftIdx++));
        }

        while(rightArray.size() > rightIdx) {
            result.add(rightArray.get(rightIdx++));
        }

        return result;
    }

    public static void main(String[] args) {
        List<Integer> dataList = new ArrayList<>();

        for(int i = 0 ; i < 5 ; i++){
            dataList.add((int)(Math.random() * 100));
        }

        System.out.println(dataList.toString());
        System.out.println("결과 ====================");
        System.out.println(mergeSort(dataList));
    }
}
