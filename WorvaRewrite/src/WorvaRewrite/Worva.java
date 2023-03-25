package WorvaRewrite;

import java.util.Scanner;
import java.io.IOException;

public class Worva {
    public static void clrScr() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        PlaySession currentPlaySession = new PlaySession();

        Scanner input = new Scanner(System.in);

        System.out.print("Does this: ⬛ look like a black box? (y/n): ");
        if (input.next().equalsIgnoreCase("y")) {
            currentPlaySession.setUnicode(true);
        }

        clrScr();

        do {
            currentPlaySession.generateKeyWord();

            Guess[] guessBoard = new Guess[6];
            for (int i = 0; i < 6; i++) {
                guessBoard[i] = new Guess(currentPlaySession);
            }

            for (int i = 0; i < 6; i++) {
                clrScr();
                for (Guess guess : guessBoard) {
                    System.out.println(guess);
                }

                do {
                    System.out.print("Guess " + (i + 1) + ": ");
                    guessBoard[i].setWord(input.next().toUpperCase());
                } while (guessBoard[i].getLength() != 5);
                guessBoard[i].evaluate();

                if (guessBoard[i].getWord().equalsIgnoreCase(currentPlaySession.getKeyWord())) {
                    break;
                }
            }

            clrScr();
            for (Guess guess : guessBoard) {
                System.out.println(guess);
            }

            System.out.println("\nThe word was: " + currentPlaySession.getKeyWord());

            for (Guess guess : guessBoard) {
                System.out.println(guess.getEvaluation());
            }

            System.out.print("\nPlay Again? (y/n): ");
        } while (input.next().equalsIgnoreCase("y"));
    }
}
