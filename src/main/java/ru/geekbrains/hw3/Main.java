package ru.geekbrains.hw3;

import javax.sound.midi.Soundbank;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean game = true;
        System.out.println("Привет давай играть?");
        while (game) {
            System.out.println("Выберите номер игры, 0 - выход.");
            System.out.println("1 - Отгадай число");
            System.out.println("2 - Отгадай слово");
            if (scanner.hasNextInt()) {
                int menu = scanner.nextInt();
                scanner.nextLine();
                switch (menu) {
                    case 1:
                        guessNum();
                        break;
                    case 2:
                        guessWord();
                        break;
                    case 0:
                        game = false;
                        break;
                    default:
                        System.out.printf("Игры под номером %d не существует\n", menu);
                }
            } else {
                System.out.println("Вы ввели некорректное число");
            }

        }
        scanner.close();
    }

    public static void guessWord() {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        Random random = new Random();
        int wordNum = random.nextInt(words.length);
        String guessWord = words[wordNum];
        //System.out.println(words[wordNum]);
        System.out.println("Компьютер загадал слово, попробуй его отгадать по буквам");
        char[] wordChar = new char[words[wordNum].length()];
        for (int i = 0; i < words[wordNum].length(); i++) {
            wordChar[i] = '#';
        }
        System.out.println("Внимание! Вводить необходимо ТОЛЬКО маленькие буквы!!! Для выхода из игры введите exit.");
        Scanner scanner = new Scanner(System.in);
        boolean game = true;
        int winChars = 0;
        while (game) {
            String entresWord = "";
            for (int i = 0; i < wordChar.length; i++) {
                System.out.print(wordChar[i]);
            }
            System.out.print("\nВвыедите букву: ");
            String line = scanner.nextLine();
            if (line.length() > 1) {
                if (line.equals("exit")) {
                    System.out.println("Вы вышли из игры");
                    break;
                }
                System.out.println("Вы ввели больше одного символа!");
            } else {
                for (int i = 0; i < words[wordNum].length(); i++) {
                    char ch = words[wordNum].charAt(i);
                    if (ch == line.charAt(0)) {
                        wordChar[i] = ch;
                        winChars = winChars + 1;
                    }
                    //System.out.println(entresWord + " " + guessWord);
                    if (winChars == words[wordNum].length()) {
                        System.out.println("Вы победили!!! Компьютер загадал слово: " + guessWord + "\n\n");
                        game = false;
                    }
                }
            }
        }

    }

    public static void guessNum() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int num = random.nextInt(10);
        System.out.println("Компьютер загадал число от 0 до 9");
        System.out.println("Попробуй его отгадать за 3 попытки");
        int amount = 3;
        while (amount != 0) {
            System.out.printf("Попыток: %d, введите число: ", amount);
            if (scanner.hasNextInt()) {
                amount -= 1;
                int enterNum = scanner.nextInt();
                if (enterNum == num) {
                    System.out.println("Ура, вы выиграли!!!\n\n");
                    break;
                } else if (enterNum > num) {
                    System.out.println("Загаданное число меньше");
                } else if (enterNum < num) {
                    System.out.println("Загаданное число больше");
                }
                if (amount == 0 && enterNum != num) {
                    System.out.printf("К сожалению у Вас не получилось отгадать число. :-(");
                }
            } else {
                System.out.println("При вводе допускаются только цифры!");
            }
            scanner.nextLine();
        }
    }
}
