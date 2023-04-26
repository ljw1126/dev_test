/*
이진 탐색 (Binary Search)
시간 복잡도 : O(logN)

재귀 함수 또는 반복문으로 구현 가능
*/

function binarySearchByRecursiveCall(arr, target, left, right) {
    if(left > right) return -1;

    const mid = parseInt((left + right)/2);

    if(arr[mid] === target) { // 찾은 경우 인덱스 반환
        return mid;
    } else if(arr[mid] > target) { // 중간점의 값보다 찾고자 하는 값이 작은 경우 왼쪽 확인
        return binarySearchByRecursiveCall(arr, target, left, mid - 1);
    } else { // 중간점의 값보다 찾고자 하는 값이 큰 경우 오른쪽 확인
        return binarySearchByRecursiveCall(arr, target, mid + 1, right);
    }
}

function binarySearchByWhile(arr, target, left, right) {
    while(left <= right) {
        const mid = parseInt((left + right) / 2);
        if(arr[mid] === target) return mid;
        else if(arr[mid] < target) left = mid + 1;
        else right = mid -1;
    }

    return -1;
}

const target = 7; // 찾고자 하는 값
const arr = [1, 3, 5, 7, 9, 11, 13, 15, 17, 19];

let result = binarySearchByRecursiveCall(arr, target, 0, arr.length - 1);

if(result === -1) {
    console.log('원소가 존재하지 않습니다.');
} else {
    console.log(`${result + 1}번째 원소입니다.`);
}

