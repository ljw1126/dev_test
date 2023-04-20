/*
    [병합 정렬]
    시간 복잡도 : O(NlogN)
    장점 : 최악의 경우에도 O(NlogN) 보장
    단점 : 병합하는 과정에서 "임시 배열" (저장 공간) 필요
          재귀 함수 호출하는 과정에서 오버헤드 발생 가능
 */
function merge(arr, left, mid, right) {
    let i = left;
    let j = mid + 1;
    let k = left; // sorted index
    const sorted = [];

    while(i <= mid && j <= right) {
        if(arr[i] <= arr[j]) sorted[k++] = arr[i++];
        else sorted[k++] = arr[j++];
    }

    if(i > mid) {
        for(; j <= right; j++) sorted[k++] = arr[j];
    } else {
        for(; i <= mid; i++) sorted[k++] = arr[i];
    }

    for(let x = left; x <= right; x++) {
        arr[x] = sorted[x];
    }
}
function mergeSort(arr, left, right) {
    if(left === right) return;

    const mid = parseInt((left + right) / 2);
    mergeSort(arr, left, mid);
    mergeSort(arr, mid + 1, right);

    merge(arr, left, mid, right);
}
function testMergeSort() {
    const arr = Array.from({length : 10}, () => Math.floor(Math.random() * 1000));

    const startTime = new Date().getTime();
    mergeSort(arr, 0, arr.length - 1);
    const endTime = new Date().getTime();

    console.log(`소요시간 ${endTime - startTime} ms \n 실행결과 : \n ${arr}`);
}





