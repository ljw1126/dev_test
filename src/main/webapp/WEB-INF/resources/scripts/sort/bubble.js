function bubbleSort(arr) {
    for(let i = arr.length - 1; i > 0; i--) {
        for(let j = 0; j < i; j++) {
            if(arr[j] < arr[j + 1]) {
                const temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}

function testBubbleSort() {
    const arr = Array.from({length : 10}, () => Math.floor(Math.random() * 1000));
    console.log(arr);

    const startTime = new Date().getTime();
    bubbleSort(arr);
    const endTime = new Date().getTime();

    console.log(`${endTime - startTime} ms \n`);
    console.log(arr);
}