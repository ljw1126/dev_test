function selectionSort(arr) {
    for(let i = 0; i < arr.length; i++) {
        let minIndex = i;
        for(let j = i + 1; j < arr.length; j ++) {
            if(arr[minIndex] > arr[j]) {
                minIndex = j;
            }
        }

        const temp = arr[i];
        arr[i] = arr[minIndex];
        arr[minIndex] = temp;
    }
}

function testSelectionSort() {
    const arr = Array.from({length : 10}, () => Math.floor(Math.random() * 1000));
    console.log(arr);

    const startTime = new Date().getTime();
    selectionSort(arr);
    const endTime = new Date().getTime();

    console.log(`${endTime - startTime} ms`);
    console.log(arr);
}