package programmers.mdup;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 참고. 정규 표현식
 * https://inpa.tistory.com/entry/JAVA-%E2%98%95-%EC%A0%95%EA%B7%9C%EC%8B%9DRegular-Expression-%EC%82%AC%EC%9A%A9%EB%B2%95-%EC%A0%95%EB%A6%AC
 * <p>
 * 시간복잡도 :
 * - nums 최대길이 200,000이고, 각 num의 최대길이가 20일때 필터링 로직은 O(20만 * 20) 소요
 * - 마지막 정렬의 경우 정상 계좌의 수가 20만개일때 O(k * log k) 로 약 1,600,000 소요
 * - 전체적으로 O(n) + O(k * log k)
 */
public class Solve1 {
    private static final Data DATA1 = new Data(new String[]{
            "4514--234495-1", // 11개
            "305-44-291501", // 11개
            "1-2-34-495-8623", // 11개
            "492134545151", // 12개
            "623-421523-67-341", // 14개
            "-5439-59639921", // 12개
            "6235-7X3+47-7456", // 12개
            "98-76-543-210", // 10개
            "512-73-634901", // 11개
            "000-999999-22-555", //14개
            "064-82-792561" // 11개
    }, new int[]{3, 2, 1});

    private static final Data DATA2 = new Data(new String[]{
            "1-2-3-456789012",   // 숫자 13개
            "582845-385823",     // 숫자 12개
            "48572-39485-89012", // 숫자 15개
            "4-5-2-593328484", // 12개
            "4958-39-2945123-", // 13개
            "49582039415423", // 14개
            "7-3-7-000000000",  // 숫자 12개
            "485723-693812",    // 숫자 12개
            "39482746582734",  // 숫자 14개
            "1-1-1-111111111", // 숫자 12개
            "A4944-5095-4951",
            "4851293412223" // 숫자 13개
    }, new int[]{4, 2, 2, 1});

    private static final Data DATA3 = new Data(new String[]{
            "592356=5345",
            "49-694-4495-64",  // 숫자 11개
            "5923565345%"      // 숫자 9개
    }, new int[]{1});

    public static void main(String[] args) {
        pro(DATA1);
        pro(DATA2);
        pro(DATA3);
    }

    private static final Pattern RULE1 = Pattern.compile("[^\\d|\\-]"); // digit도 dash도 아닌 경우
    private static final Pattern RULE2 = Pattern.compile("\\d");
    private static final Pattern RULE3 = Pattern.compile("\\-");

    /**
     * 규칙1. 0~9사이의 숫자와 특수문자 '-'로만 이루어짐
     * 규칙2. 포함된 숫자는 11개이상 14개 이하
     * 규칙3. 포함된 '-'의 개수는 0개 이상 3개이하
     * 규칙4. '-'는 연속적해서 나타날 수 없고, 처음과 마지막에도 나타날 수 없다
     *
     * @param data
     */
    private static void pro(Data data) {
        String[] nums = data.nums;
        Map<String, Integer> countMap = new HashMap<>(); // gruopBy였던가 카운팅이

        for (String num : nums) {
            if (!isValidAccount(num)) continue;

            String replaced = num.replaceAll("\\d", "x");
            countMap.put(replaced, countMap.getOrDefault(replaced, 0) + 1);
        }

        int[] result = countMap.values().stream()
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();

        System.out.println(Arrays.toString(result));
    }

    private static boolean isValidAccount(String num) {
        // 규칙 1: 0~9와 "-"로만 이루어졌는지 확인
        Matcher matcher1 = RULE1.matcher(num);
        if (matcher1.find()) return false;

        // 규칙 2: 숫자의 개수가 11~14개인지 확인
        long digitCount = num.chars().filter(Character::isDigit).count();
        if (digitCount < 11 || 14 < digitCount) return false;

        // 규칙 3: "-"의 개수가 0 ~ 3개인지 확인
        long dashCount = num.chars().filter(c -> c == '-').count();
        if (dashCount > 3) return false;

        // 규칙 4: "-"가 연속으로 나오거나 처음/마지막에 오는지 확인
        if (num.startsWith("-") || num.endsWith("-") || num.contains("--")) return false;

        return true;
    }

    private static boolean checkRule1(String num) {
        Matcher matcher = RULE1.matcher(num);
        return matcher.find();
    }

    private static boolean checkRule2(String num) {
        Matcher matcher = RULE2.matcher(num);
        int count = getCount(matcher);
        return count < 11 || 14 < count;
    }

    private static boolean checkRule3(String num) {
        Matcher matcher = RULE3.matcher(num);
        int count = (int) matcher.results().count(); // 자바 9이상
        return 3 < count;
    }

    private static boolean checkRule4(String num) {
        return num.startsWith("-") || num.endsWith("-") || num.contains("--");
    }

    private static int getCount(Matcher matcher) {
        int count = 0;
        while (matcher.find()) {
            count += 1;
        }

        return count;
    }

    private static class Data {
        private final String[] nums;
        private final int[] result;

        public Data(String[] nums, int[] result) {
            this.nums = nums;
            this.result = result;
        }
    }
}
