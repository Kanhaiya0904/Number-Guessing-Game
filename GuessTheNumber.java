import javax.swing.JOptionPane;
import java.util.Random;

public class GuessTheNumber {
    public static void main(String[] args) {
        Random random = new Random();
        final int TOTAL_ROUNDS = 3;
        final int MAX_TRIES = 7;
        int overallScore = 0;
        int[] roundScores = new int[TOTAL_ROUNDS];

        JOptionPane.showMessageDialog(null,
                "Welcome to Number Guessing Game!\nYou will play a total of " + TOTAL_ROUNDS + " rounds.\nIn each round you have to guess a number between 1 and 100.\nYou will have " + MAX_TRIES + " chances per round.");

        for (int round = 0; round < TOTAL_ROUNDS; round++) {
            int targetNumber = random.nextInt(100) + 1;
            int tryCount = 0;
            boolean guessedCorrectly = false;

            while (tryCount < MAX_TRIES) {
                String guessInput = JOptionPane.showInputDialog(null,
                        "🔄 Round " + (round + 1) + " | Try " + (tryCount + 1) + "/" + MAX_TRIES +
                                "\nEnter your guess (1–100):");

                if (guessInput == null) {
                    JOptionPane.showMessageDialog(null, "🚫 Game exited by user.");
                    return;
                }

                int playerGuess;
                try {
                    playerGuess = Integer.parseInt(guessInput);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "❗ Invalid input. Please enter a valid number.");
                    continue;
                }

                if (playerGuess < 1 || playerGuess > 100) {
                    JOptionPane.showMessageDialog(null, "⚠️ Number must be between 1 and 100.");
                    continue;
                }

                tryCount++;

                if (playerGuess == targetNumber) {
                    int score = (MAX_TRIES - tryCount + 1) * 10;
                    roundScores[round] = score;
                    overallScore += score;
                    JOptionPane.showMessageDialog(null, "🎯 Correct! You guessed it in " + tryCount +
                            " tries.\nPoints this round: " + score);
                    guessedCorrectly = true;
                    break;
                } else if (playerGuess < targetNumber) {
                    JOptionPane.showMessageDialog(null, "🔻 Too low!");
                } else {
                    JOptionPane.showMessageDialog(null, "🔺 Too high!");
                }
            }

            if (!guessedCorrectly) {
                roundScores[round] = 0;
                JOptionPane.showMessageDialog(null, "😓 You didn't guess it.\nThe correct number was: " + targetNumber);
            }
        }

        // Display Scoreboard
        StringBuilder scoreboard = new StringBuilder("📊 Scoreboard:\n");
        for (int i = 0; i < TOTAL_ROUNDS; i++) {
            scoreboard.append("Round ").append(i + 1).append(": ").append(roundScores[i]).append(" points\n");
        }
        scoreboard.append("🏁 Final Total Score: ").append(overallScore).append(" points");

        JOptionPane.showMessageDialog(null, scoreboard.toString());
    }
}
