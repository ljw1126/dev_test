function insertionSort(arr) {
    for(let i = 1; i < arr.length; i++) {
        for(let j = i; j > 0; j--) {
            if(arr[j - 1] > arr[j]) {
                const temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
            } else {
                break;
            }
        }
    }
}

function testInsertionSort() {
    const arr = Array.from({length : 10}, () => Math.floor(Math.random() * 1000));
    console.log(arr);

    const startTime = new Date().getTime();
    insertionSort(arr);
    const endTime = new Date().getTime();

    console.log(`${endTime - startTime} ms`);
    console.log(arr);
}