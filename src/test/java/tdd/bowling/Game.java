package tdd.bowling;

public class Game {
    private final int[] scores = new int[21];
    private int frameSeq = 0;

    public void roll(int pins) {
        scores[frameSeq++] = pins;
    }

    public int score() {
        int result = 0;
        int frame = 0;
        for (int i = 1; i <= 10; i++) {
            if (isStrike(frame)) {
                result += 10 + strikeBonus(frame);
                frame += 1;
            } else if (isSpare(frame)) {
                result += 10 + spareBonus(frame);
                frame += 2;
            } else {
                result += scores[frame] + scores[frame + 1];
                frame += 2;
            }
        }

        return result;
    }

    private boolean isStrike(int frame) {
        return scores[frame] == 10;
    }

    private boolean isSpare(int frame) {
        return scores[frame] + scores[frame + 1] == 10;
    }

    private int strikeBonus(int frame) {
        return scores[frame + 1] + scores[frame + 2];
    }

    private int spareBonus(int frame) {
        return scores[frame + 2];
    }

}
