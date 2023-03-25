package WorvaRewrite;

import java.util.ArrayList;
import java.util.List;

public class Guess {
    private String word;
    private final List<String> evaluation;
    private final PlaySession playSession;

    public Guess(PlaySession ps) {
        word = "-----";
        evaluation = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            evaluation.add(" ");
        }
        playSession = ps;
    }

    public String getWord() {
        return word;
    }

    public String getEvaluation() {
        StringBuilder e = new StringBuilder();
        for (String emoji : evaluation) {
            e.append(emoji);
        }
        return e.toString();
    }

    public int getLength() {
        return word.length();
    }

    public void setWord(String w) {
        word = w;
    }

    public void evaluate() {
        if (playSession.getUnicode()) {
            for (int i = 0; i < 5; i++) {
                if (word.charAt(i) == playSession.getKeyWord().charAt(i)) {
                    evaluation.set(i, "\uD83D\uDFE9");
                } else if (playSession.getKeyWord().contains(String.valueOf(word.charAt(i)))) {
                    evaluation.set(i, "\uD83D\uDFE8");
                } else {
                    evaluation.set(i, "⬛");
                }
            }
        } else {
            for (int i = 0; i < 5; i++) {
                if (word.charAt(i) == playSession.getKeyWord().charAt(i)) {
                    evaluation.set(i, "O");
                } else if (playSession.getKeyWord().contains(String.valueOf(word.charAt(i)))) {
                    evaluation.set(i, "!");
                } else {
                    evaluation.set(i, "X");
                }
            }
        }
    }

    public String toString() {
        StringBuilder guessRow = new StringBuilder();
        if (playSession.getUnicode()) {
            for (int i = 0; i < 5; i++) {
                guessRow.append(evaluation.get(i)).append("  ");
            }
        } else {
            for (int i = 0; i < 5; i++) {
                guessRow.append(" ").append(evaluation.get(i)).append("  ");
            }
        }
        guessRow.append("\n");
        for (int i = 0; i < 5; i++){
            guessRow.append("[").append(word.charAt(i)).append("] ");
        }

        return guessRow.toString();
    }
}
