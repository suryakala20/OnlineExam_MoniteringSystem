import java.util.Scanner;
import dao.UserDAO;
import dao.ExamDAO;
import dao.LogDAO;
import dao.QuestionDAO;
import dao.AdminLogDAO;

public class Main {
    static int userId = -1;
    static String role = "";
    static int attemptId = -1;
    static int violations = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== ONLINE EXAM PROCTORING SYSTEM =====");
            System.out.println("1. Login");
            System.out.println("2. Start Exam (Student)");
            System.out.println("3. Log TAB SWITCH");
            System.out.println("4. Log WINDOW MINIMIZED");
            System.out.println("5. Log PAGE REFRESH");
            System.out.println("6. Submit Exam");
            System.out.println("7. Add Question (Admin)");
            System.out.println("8. View My Result (Student)");
            System.out.println("9. View All Results (Admin)");
            System.out.println("10. Exit");
            System.out.print("Choose option: ");

            if (!sc.hasNextInt()) {
                System.out.println("Please enter numeric option only");
                sc.nextLine();
                continue;
            }
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    sc.nextLine();
                    System.out.print("Username: ");
                    String username = sc.nextLine();
                    System.out.print("Password: ");
                    String password = sc.nextLine();
                    String loginResult = UserDAO.login(username, password);
                    if (loginResult != null) {
                        String[] data = loginResult.split(":");
                        userId = Integer.parseInt(data[0]);
                        role = data[1];
                        System.out.println("Login successful. Role = " + role);
                        if (role.equals("ADMIN")) {
                            AdminLogDAO.logAdminAction(userId, "Admin logged in");
                        }
                    } else {
                        System.out.println("Invalid username or password");
                    }
                    break;
                case 2:
                    if (!role.equals("STUDENT")) {
                        System.out.println("Only students can start exam");
                        break;
                    }
                    attemptId = ExamDAO.startExam(userId, 1);
                    violations = 0;
                    if (attemptId != -1) {
                        System.out.println("Exam started successfully");
                        System.out.println("Attempt ID = " + attemptId);
                    }
                    break;
                case 3:
                    logViolation("TAB_SWITCH");
                    break;
                case 4:
                    logViolation("WINDOW_MINIMIZED");
                    break;
                case 5:
                    logViolation("PAGE_REFRESH");
                    break;
                case 6:
                    if (attemptId == -1) {
                        System.out.println("No active exam");
                        break;
                    }
                    ExamDAO.submitExam(attemptId, 50);
                    attemptId = -1;
                    violations = 0;
                    break;
                case 7:
                    if (!role.equals("ADMIN")) {
                        System.out.println("Only admin can add questions");
                        break;
                    }
                    sc.nextLine();
                    System.out.print("Question: ");
                    String q = sc.nextLine();
                    System.out.print("Option A: ");
                    String a = sc.nextLine();
                    System.out.print("Option B: ");
                    String b = sc.nextLine();
                    System.out.print("Option C: ");
                    String c = sc.nextLine();
                    System.out.print("Option D: ");
                    String d = sc.nextLine();
                    System.out.print("Correct Option (A/B/C/D): ");
                    String correct = sc.nextLine();
                    QuestionDAO.addQuestion(1, q, a, b, c, d, correct);
                    AdminLogDAO.logAdminAction(userId, "Added a new question");
                    break;
                case 8:
                    if (!role.equals("STUDENT")) {
                        System.out.println("Only students can view results");
                        break;
                    }
                    ExamDAO.viewStudentResults(userId);
                    break;
                case 9:
                    if (!role.equals("ADMIN")) {
                        System.out.println("Only admin can view all results");
                        break;
                    }
                    ExamDAO.viewAllResults();
                    AdminLogDAO.logAdminAction(userId, "Viewed all student results");
                    break;
                case 10:
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    // ---------- PROCTORING HELPER ----------
    private static void logViolation(String activity) {

        if (attemptId == -1) {
            System.out.println("Start exam first");
            return;
        }

        LogDAO.logActivity(attemptId, activity);
        violations++;

        if (violations >= 3) {
            System.out.println("Too many violations. Auto submitting exam");
            ExamDAO.submitExam(attemptId, 0);
            attemptId = -1;
            violations = 0;
        }
    }
}
