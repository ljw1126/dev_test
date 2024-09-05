package programmers.wooteco;

import java.util.Arrays;

/**
 * 문제 1. 그리디 문제
 * *제한 사항
 * - 1 <= boxes 길이 = n <= 100    // 배열의 길이가 100개
 * - 1 <= boxes 원소 <= 10,000   // 원소값의 범위
 * - boxes[i]는 "i + 1"번 상자에서 만원당 벌 수 있는 금액을 의미합니다.      // 만원당 boxes[i + 1] 만큼 벌 수 있다
 * - 10,000 <= m <= 1,000,000
 * - m은 10,000의 배수입니다
 * - 1 <= k <= 100
 * <p>
 * *입출력 예
 * boxes : [1000, 800, 900], m = 1000000, k = 3, result = 1294200
 * <p>
 * boxes : [9000, 10000, 8520, 9500], m = 110000, k = 4, result = 209000
 * <p>
 * 시간 복잡도 k * n
 * 최대치는 러프하게 계산하더라도 10억보다 작다 (boxes 길이 10에 각 10,000 , 최초 보유 금액 100만원 일 때)
 */
public class Solve1 {

    private static final Data DATA1 = new Data(new int[]{1000, 800, 900}, 1_000_000, 3, 1294200);
    private static final Data DATA2 = new Data(new int[]{9000, 10000, 8520, 9500}, 110_000, 4, 209000);

    public static void main(String[] args) {
        pro(DATA1);
        pro(DATA2);
    }

    private static void pro(Data data) {
        int[] boxes = data.boxes;
        int m = data.m; // 내가 보유한 금액
        int k = data.k; // k일 동안 매일 아침 한 개의 상자를 고른다
        boolean[] used = new boolean[boxes.length];
        Arrays.sort(boxes);

        while (k-- > 0) {
            // 나가 보유한 금액을 넣었을 때 10만원 이하가 되는 최대 박스를 구한다
            int selected = findNextBox(boxes, used, m);

            // 선택 가능한 상자가 없으면
            if (selected == -1) continue;

            // 박스 사용 여부를 표시하고, 이자를 보유 금액에 더한다
            used[selected] = true;
            m += (boxes[selected] * (m / 10_000));
        }

        System.out.println(m);
    }

    private static int findNextBox(int[] boxes, boolean[] used, int money) { // used: 박스 사용여부, moeny 현재 보유 금액(이자포함)
        int earn = 0;
        int count = money / 10_000; // 만원당 이자를 계산하므로, 현재 보유금액을 만원으로 나눈 개수를 구한다
        int selected = -1;

        for (int i = 0; i < boxes.length; i++) {
            if (used[i]) continue;

            // 10만원 이하로 벌 수 있는 상자 중에 최대값을 구함
            int expected = count * boxes[i];
            if (expected <= 100_000 && earn < expected) {
                selected = i;
                earn = expected;
            }
        }

        return selected;
    }


    private static class Data {
        private final int[] boxes;
        private final int m;
        private final int k;
        private final int result;

        public Data(int[] boxes, int m, int k, int result) {
            this.boxes = boxes;
            this.m = m;
            this.k = k;
            this.result = result;
        }
    }
}
