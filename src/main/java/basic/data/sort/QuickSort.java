package basic.data.sort;

import java.util.Arrays;
import java.util.Random;

// https://www.youtube.com/watch?v=h8eyY7dIiN4
// 1 8 3 9 4 5 7 -- pivot = 7
// L = 8; R = 5
// 1 5 3 9 4 8 7
// L = 9, R = 4
// 1 5 3 4 9 8 7
// L = 9, R = 9 에서 정지
// 1 5 3 4 9 8 7 -- L == R인 위치와 pivot 스왑
// 파티션을 나누기 전 피벗 기준으로 좌측은 작고, 우측은 큰 값이 위치하게 됨
// 결과적으로 파티션을 나누기 전에 피벗 기준으로 나눠지므로, 가장 작은 파티션이 되면 이미 완료가 됨 (피벗 기준 그룹 나눈 후 파티션)
// <-> 병합 정렬은 우선 파티션을 가장 작게 나누고 병합하면서 정렬하는 방식 (분할 후 정렬)
public class QuickSort {

    private static void quickSort(int[] arr) {
        //quickSortByLeftPivot(arr, 0, arr.length - 1);
        quickSortByMidPivot(arr, 0, arr.length - 1);
        //quickSortByRightPivot(arr, 0, arr.length - 1);
    }

    private static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    // left의 경우 R이 먼저 움직이고, R을 기준으로 파티션 나눠짐
    // 1 8 3 9 4 5 7 에서 최소값이 이미 왼쪽에 있는 경우 생각해보기
    private static void quickSortByLeftPivot(int[] arr, int leftIdx, int rightIdx) {
        if(leftIdx >= rightIdx) return;

        int pivot = arr[leftIdx];
        int L = leftIdx;
        int R = rightIdx;

        while(L < R) {
            while(pivot < arr[R] && L < R) R -= 1;
            while(arr[L] < pivot && L < R) L += 1;

            if(L >= R) break;

            swap(arr, L, R);
        }

        quickSortByLeftPivot(arr, leftIdx, R - 1);
        quickSortByLeftPivot(arr, R + 1, rightIdx);
    }

    // 오른쪽 피벗 기준인 경우 L 부터 움직이고, L을 기준으로 파티션 나눔
    private static void quickSortByRightPivot(int[] arr, int leftIdx, int rightIdx) {
        if(leftIdx >= rightIdx) return;

        int pivot = arr[rightIdx];
        int L = leftIdx;
        int R = rightIdx;

        while(L < R) {
            while(arr[L] < pivot && L < R) L += 1;
            while(pivot < arr[R] && L < R) R -= 1;

            while(L >= R) break;

            swap(arr, L, R);
        }

        quickSortByRightPivot(arr, leftIdx, R - 1);
        quickSortByRightPivot(arr, R + 1, rightIdx);
    }

    // 중간 피벗의 경우 L, R이 엇갈릴 때까지 수행
    private static void quickSortByMidPivot(int[] arr, int leftIdx, int rightIdx) {
        if(leftIdx >= rightIdx) return;

        int mid = (leftIdx + rightIdx) / 2;
        int pivot = arr[mid];
        int L = leftIdx;
        int R = rightIdx;

        while(L <= R) {
            while(pivot < arr[R]) R -= 1;
            while(arr[L] < pivot) L += 1;

            if(L <= R) {
                swap(arr, L, R);
                L += 1;
                R -= 1;
            }
        }

        quickSortByRightPivot(arr, leftIdx, R);
        quickSortByRightPivot(arr, L, rightIdx);
    }

    public static void main(String[] args) {
        int[] data = new Random().ints(1, 99).distinct().limit(7).toArray();

        System.out.println(Arrays.toString(data));
        quickSort(data);
        System.out.println(Arrays.toString(data));
    }
}
