package basic.data.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class QuickSort {

    // 마지막 인덱스를 pivot 으로 지정하는 경우
    // https://st-lab.tistory.com/250
    private static void quickSortByRightPivot(List<Integer> arr, int leftIdx, int rightIdx) {
        if(leftIdx >= rightIdx) return;

        int pivot = arr.get(rightIdx);
        int L = leftIdx;
        int R = rightIdx - 1;

        while(L < R) {
            while(arr.get(L) <= pivot && L < R) L += 1;

            while(pivot <= arr.get(R) && L < R) R -= 1;

            Collections.swap(arr, L, R);
        }

        Collections.swap(arr, L, rightIdx); // L == R 일때 피벗과 스왑

        quickSortByRightPivot(arr, leftIdx, L - 1);
        quickSortByRightPivot(arr, L + 1, rightIdx);
    }

    // 2 77 60 84 79 86 46 22 22 와 같이
    // 왼쪽 피벗이 제일 제일 작은 경우 이슈가 발생함
    //https://hongl.tistory.com/5
    private static void quickSortByLeftPivot(List<Integer> arr, int leftIdx, int rightIdx) {
        if(leftIdx >= rightIdx) return;

        int pivot = arr.get(leftIdx);
        int L = leftIdx + 1;
        int R = rightIdx;

        while(L <= R) {
            // right pivot과 비교시 범위가 다르네 ..
            while(arr.get(L) <= pivot && L < rightIdx) L += 1;
            while(pivot <= arr.get(R) && leftIdx < R) R -= 1;

            if(L >= R) break;

            Collections.swap(arr, L, R);
        }

        Collections.swap(arr, leftIdx, R); // 여기서 꼬이네 2 58 .. 2보다 큰 경우 R = 1, L = 1되고 나니 스왑하면망함

        if(leftIdx < L) quickSortByLeftPivot(arr, leftIdx, R - 1);
        if(L < rightIdx) quickSortByLeftPivot(arr, R + 1, rightIdx);
    }


    private static void quickSort(List<Integer> arr, int low, int high) {
        if(low >= high) return;

        int pivot = partition(arr, low, high);
        quickSort(arr, low, pivot - 1);
        quickSort(arr, pivot + 1, high);
    }

    private static int partition(List<Integer> arr, int low, int high) {
        int pivot = arr.get(low);
        int i = low;
        for(int j = i + 1; j <= high; j++) {
            if(pivot > arr.get(j)) {
                i += 1;
                Collections.swap(arr, i, j);
            }
        }

        Collections.swap(arr, low, i);

        return i - 1;
    }

    // 중간 인덱스를 pivot 으로 지정하는 경우
    // https://www.youtube.com/watch?v=7BDzle2n47c&t=395s
    private static void quickSortByMiddlePivot(List<Integer> arr, int leftIdx, int rightIdx) {
        if(leftIdx >= rightIdx) return;

        int pl = leftIdx;
        int pr = rightIdx;

        // 같은 경우 swap하더라도 변화없고 pl > pr 로 교차하게 됨
        int mid = (pl + pr) / 2;
        int pivot = arr.get(mid); // 한번 피벗 값은 해당 파티션에서 고정**
        while(pl <= pr) { // 이 괄호가 문제였는듯
            while(arr.get(pl) < pivot) pl += 1; // 크거나 같은 값을 찾음
            while(pivot < arr.get(pr)) pr -= 1; // 작거나 같은 값을 찾음

            if(pl <= pr) {
                Collections.swap(arr, pl, pr);
                pl += 1;
                pr -= 1;
            }
        }

        // 파티션
        if(leftIdx < pr) quickSortByMiddlePivot(arr, leftIdx, pr);
        if(pl < rightIdx) quickSortByMiddlePivot(arr, pl, rightIdx);
    }

    public static void main(String[] args) {
        List<Integer> data = new ArrayList<>();

        for(int i = 1; i <= 9; i++) {
            data.add((int)(Math.random() * 100));
        }

        System.out.println(data);
        quickSortByLeftPivot(data, 0, data.size() - 1);
        System.out.println(data);
    }
}
