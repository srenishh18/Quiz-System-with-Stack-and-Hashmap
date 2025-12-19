import java.util.*;

public class QuizApp {
    static class Question {
        String question;
        List<String> options;
        String correct;

        Question(String question, List<String> options, String correct) {
            this.question = question;
            this.options = options;
            this.correct = correct;
        }
    }

    private final List<Question> quizData = new ArrayList<>();
    private final Deque<String> questionStack = new ArrayDeque<>(); // LIFO
    private final Map<String, String> answerMap = new LinkedHashMap<>();

    private int currentQuestion = 0;
    private int score = 0;

    private final Scanner scanner = new Scanner(System.in);

    public QuizApp() {
        // Populate quiz data (converted from the original script.js)
        quizData.add(new Question("What is the capital of India?",
                Arrays.asList("Mumbai", "Delhi", "Chennai", "Kolkata"), "Delhi"));
        quizData.add(new Question("Which data structure uses LIFO?",
                Arrays.asList("Queue", "Stack", "Array", "Tree"), "Stack"));
        quizData.add(new Question("What keyword is used to inherit a class in Java?",
                Arrays.asList("implements", "inherits", "extends", "import"), "extends"));
        quizData.add(new Question("Which collection stores unique elements?",
                Arrays.asList("List", "Set", "Queue", "Map"), "Set"));
        quizData.add(new Question("What is the default value of a boolean in Java?",
                Arrays.asList("true", "false", "null", "0"), "false"));
    }

    private void loadQuestion() {
        Question q = quizData.get(currentQuestion);
        System.out.println();
        System.out.printf("Q%d: %s\n", currentQuestion + 1, q.question);
        for (int i = 0; i < q.options.size(); i++) {
            System.out.printf("  %d) %s\n", i + 1, q.options.get(i));
        }
        System.out.println("Enter option number (1-" + q.options.size() + "):");
    }

    private void selectAnswer(int choice) {
        Question currentQ = quizData.get(currentQuestion);
        String chosen = currentQ.options.get(choice - 1);

        // Stack push
        questionStack.push(currentQ.question);

        // HashMap store
        answerMap.put(currentQ.question, chosen);

        // Scoring
        if (chosen.equals(currentQ.correct)) {
            score += 10;
            System.out.println("Correct! +10 points.");
        } else {
            System.out.println("Incorrect. Correct answer: " + currentQ.correct);
        }
    }

    private void showFinalScore() {
        int total = quizData.size() * 10;
        System.out.println();
        System.out.println("Your Final Score: " + score + " / " + total);
        System.out.println();
        System.out.println("Stack (LIFO order of questions asked):");
        for (String q : questionStack) {
            System.out.println(" - " + q);
        }

        System.out.println();
        System.out.println("Answers map:");
        answerMap.forEach((q, a) -> System.out.println("Q: " + q + " -> A: " + a));
    }

    private void restartQuiz() {
        score = 0;
        currentQuestion = 0;
        questionStack.clear();
        answerMap.clear();
        System.out.println("Quiz restarted.");
    }

    public void run() {
        boolean running = true;
        while (running) {
            loadQuestion();
            int choice = -1;
            try {
                String line = scanner.nextLine().trim();
                if (line.equalsIgnoreCase("exit") || line.equalsIgnoreCase("quit")) {
                    System.out.println("Exiting quiz.");
                    return;
                }
                choice = Integer.parseInt(line);
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number corresponding to an option.");
                continue; // ask the same question again
            }

            if (choice < 1 || choice > quizData.get(currentQuestion).options.size()) {
                System.out.println("Choice out of range. Try again.");
                continue;
            }

            selectAnswer(choice);

            currentQuestion++;
            if (currentQuestion < quizData.size()) {
                System.out.println("Press Enter to continue to next question, or type 'restart' to restart, 'exit' to quit.");
                String cmd = scanner.nextLine().trim();
                if (cmd.equalsIgnoreCase("restart")) {
                    restartQuiz();
                } else if (cmd.equalsIgnoreCase("exit") || cmd.equalsIgnoreCase("quit")) {
                    System.out.println("Exiting quiz.");
                    return;
                }
            } else {
                showFinalScore();
                System.out.println();
                System.out.println("Type 'restart' to take the quiz again or 'exit' to quit.");
                String cmd = scanner.nextLine().trim();
                if (cmd.equalsIgnoreCase("restart")) {
                    restartQuiz();
                } else {
                    running = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Quiz (console version). Type 'exit' at any prompt to quit.");
        QuizApp app = new QuizApp();
        app.run();
        System.out.println("Goodbye.");
    }
}
