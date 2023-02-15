package bullscows;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the length of the secret code: ");
        String lengthStr = scanner.nextLine();
        if (!lengthStr.matches("\\d+")) {
            System.out.println("Error: \""+lengthStr+"\" isn't a valid number.");
            return;
        }
        System.out.println("Input the number of possible symbols in the code: ");
        int challenge = scanner.nextInt();

        int length = Integer.parseInt(lengthStr);
        if(length == 0 || challenge == 0){
            System.out.println("Error: Challenge Impossible!");
        }
        if(length > challenge){
            System.out.println("Error: it's not possible to generate a code with a length of "+length+" with "+challenge+" unique symbols.");
        } else if (challenge > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
        }

        else{
            playGame(length,challenge);
        }

    }

    public static void playGame(int length, int challenge){
        Scanner scanner = new Scanner(System.in);
        String stars = "*".repeat(length);
        System.out.println("The secret is prepared: " + stars + " (0-9, a-" + (char)(challenge - 11 + 'a') + ").");
        System.out.println("Okay, let's start a game!");
        String code = generateRandomUniqueString(length,challenge);
        int bulls = 0;
        int codeLength = code.length();
        int turn = 0;
        while(bulls != code.length()){
            System.out.println("Turn " + turn + ":");
            String guess = scanner.next();

            if (guess.length() != codeLength) {
                System.out.println("Invalid guess length. Please enter a " + codeLength + "-character code.");
                continue;
            }
            bulls = 0;
            int cows = 0;
            for (int i = 0; i < codeLength; i++) {
                if (guess.charAt(i) == code.charAt(i)) {
                    bulls++;
                } else if (code.contains(guess.charAt(i) + "")) {
                    cows++;
                }
            }
            printMessage(new int[]{bulls, cows});
            turn ++;
        }
        System.out.println("Congratulations! You guessed the secret code.");
    }


    public static String generateRandomUniqueString(int length, int allowLength) {
        String charsList = "0123456789abcdefghijklmnopqrstuvwxyz";
        String allowedChars = charsList.substring(0, allowLength);
        char[] chars = allowedChars.toCharArray();
        Random random = new Random();
        for (int i = chars.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = chars[index];
            chars[index] = chars[i];
            chars[i] = temp;
        }
        return new String(chars, 0, length);
    }


    public static void printMessage(int[] bulls_cows) {
        int bull = bulls_cows[0];
        int cow = bulls_cows[1];
        System.out.print("Grade: ");
        String msg = "";
        if (bull == 0 && cow == 0) {
            msg += "None.";
        }
        if (bull == 1) {
            msg += bull + " bull";
            //System.out.print(bull + " bull(s).");
        } else if (bull > 1) {
            msg += bull + " bulls";
        }
        if (cow == 1) {
            if (msg.isEmpty()) {
                msg += "1 cow";
            } else {
                msg += " and 1 cow";
            }
        } else if (cow > 1) {
            if (msg.isEmpty()) {
                msg += cow + " cows";
            } else {
                msg += " and " + cow + " cows";
            }
        }
        System.out.println(msg);
    }


    public static List<Integer> longToList(long number) {
        List<Integer> digits = new ArrayList<>();
        while (number > 0) {
            digits.add((int) (number % 10));
            number /= 10;
        }
        return digits;
    }


}
