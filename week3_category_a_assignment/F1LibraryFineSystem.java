public class F1LibraryFineSystem {
    static class BookIssue {
        private final String title;
        private final String borrowerName;
        private final int daysOverdue;

        BookIssue(String title, String borrowerName, int daysOverdue) {
            this.title = title;
            this.borrowerName = borrowerName;
            this.daysOverdue = daysOverdue;
        }

        double fineAmount() {
            return daysOverdue > 0 ? daysOverdue * 5.0 : 0.0;
        }

        boolean isSeverelyOverdue() {
            return daysOverdue > 14;
        }

        static double totalFineCollected(BookIssue[] issues) {
            double totalFine = 0;
            for (BookIssue issue : issues) {
                totalFine += issue.fineAmount();
            }
            return totalFine;
        }
    }

    public static void main(String[] args) {
        BookIssue[] issues = {
                new BookIssue("Clean Code", "Aditi", 18),
                new BookIssue("Effective Java", "Rohan", 5),
                new BookIssue("Refactoring", "Meera", 0),
                new BookIssue("DSA Handbook", "Karan", 21),
                new BookIssue("Design Patterns", "Divya", 9)
        };

        for (BookIssue issue : issues) {
            String status = issue.isSeverelyOverdue() ? "Severely overdue" : "OK";
            System.out.println(issue.title + " - " + issue.daysOverdue + " days - " + status);
        }

        // This sums fines across many objects, so it belongs to the class;
        // fineAmount() depends on one issue's daysOverdue, so it is an instance method.
        System.out.println("Total fine collected: Rs " + BookIssue.totalFineCollected(issues));
    }
}
