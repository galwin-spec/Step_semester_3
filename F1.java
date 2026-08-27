class BookIssue {
    String title;
    String borrowerName;
    int daysOverdue;

    BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }

    double fineAmount() {
        if (daysOverdue > 0)
            return daysOverdue * 5;
        return 0;
    }

    boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }

    static double totalFineCollected(BookIssue[] issues) {
        double total = 0;

        for (BookIssue issue : issues) {
            total += issue.fineAmount();
        }

        return total;
    }
}

public class F1 {
    public static void main(String[] args) {

        BookIssue[] issues = {
            new BookIssue("Clean Code", "John", 18),
            new BookIssue("Effective Java", "Mike", 5),
            new BookIssue("Refactoring", "Alex", 0),
            new BookIssue("DSA Handbook", "Sam", 21),
            new BookIssue("Design Patterns", "Tom", 9)
        };

        for (BookIssue issue : issues) {
            System.out.print(issue.title + " - "
                    + issue.daysOverdue + " days - ");

            if (issue.isSeverelyOverdue())
                System.out.println("Severely overdue");
            else
                System.out.println("OK");
        }

        System.out.println("Total fine collected: Rs "
                + BookIssue.totalFineCollected(issues));
    }
}

