package util.stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CompareStreamForTest {
    @DisplayName("int 배열 덧셈 비교")
    @Test
    void test1() {
        int[] numbers = IntStream.rangeClosed(1, 1000).toArray();

        long startTime = System.nanoTime();

        //test1byForLoop(numbers);
        //test1byEnhancedFor(numbers);
        test1byStream(numbers);

        long endTime = System.nanoTime();

        long result = endTime - startTime;

        System.out.println(result * 1e-6);
    }

    private long test1byForLoop(int[] data) {
        long total = 0;
        for(int i = 0; i < data.length; i++) {
            total += data[i];
        }
        return total;
    }

    private long test1byEnhancedFor(int[] data) {
        long total = 0;
        for(int n : data) {
            total += n;
        }
        return total;
    }

    private long test1byStream(int[] data) {
        return Arrays.stream(data).sum();
    }


    @DisplayName("배열 최대값 구하는 연산")
    @Test
    void test2() {
        int[] data = IntStream.rangeClosed(1, 1000).toArray();

        long startTime = System.nanoTime();
        //test2ByFor(data);
        //test2ByEnhancedFor(data);
        test2ByStream(data);
        long endTime = System.nanoTime();

        long result = (endTime - startTime);

        System.out.println(result * 1e-6);
    }

    private long test2ByFor(int[] data) {
        int max = 0;
        for(int i = 0; i < data.length; i++) {
            max = Math.max(max, data[i]);
        }

        return max;
    }

    private long test2ByEnhancedFor(int[] data) {
        int max = 0;
        for(int n : data) {
            max = Math.max(max, n);
        }

        return max;
    }

    private long test2ByStream(int[] data) {
        return Arrays.stream(data).max().orElse(-1);
    }

    @DisplayName("리스트 덧셈 성능 비교")
    @Test
    void test3() {
        List<Integer> data = IntStream.rangeClosed(1, 1000).boxed().collect(Collectors.toList());

        long startTime = System.nanoTime();
        //test3ByFor(data);
        //test3ByEnhancedFor(data);
        test3ByStream(data);
        long endTime = System.nanoTime();

        long result = (endTime - startTime);

        System.out.println(result * 1e-6);
    }

    private long test3ByFor(List<Integer> data) {
        int total = 0;
        for(int i = 0; i < data.size(); i++) {
            total += data.get(i);
        }

        return total;
    }

    private long test3ByEnhancedFor(List<Integer> data) {
        int total = 0;
        for(int n : data) {
            total += n;
        }

        return total;
    }

    private long test3ByStream(List<Integer> data) {
        return data.stream().mapToInt(Integer::intValue).sum();
    }


    @DisplayName("리스트 최대값 성능 비교")
    @Test
    void test4() {
        List<Integer> data = IntStream.rangeClosed(1, 10000).boxed().collect(Collectors.toList());

        // #1
        long startTime = System.nanoTime();
        //test4ByFor(data);
        //test4ByEnhancedFor(data);
        test4ByStream(data);
        long endTime = System.nanoTime();
        long result = (endTime - startTime);

        System.out.println(result * 1e-6);
    }

    private long test4ByFor(List<Integer> data) {
        int max = 0;
        for(int i = 0; i < data.size(); i++) {
            max = Math.max(max, data.get(i));
        }

        return max;
    }

    private long test4ByEnhancedFor(List<Integer> data) {
        int max = 0;
        for(int n : data) {
            max = Math.max(max, n);
        }

        return max;
    }

    private long test4ByStream(List<Integer> data) {
        return data.stream().mapToInt(Integer::intValue).max().orElse(-1);
    }
}
