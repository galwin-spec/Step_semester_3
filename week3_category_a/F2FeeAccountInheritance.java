public class F2FeeAccountInheritance {
    static class FeeAccount {
        private final String regNo;
        private final double totalFee;
        private double amountPaid;

        FeeAccount(String regNo, double totalFee) {
            this.regNo = regNo;
            this.totalFee = totalFee;
        }

        void pay(double amount) {
            if (amount <= 0) {
                System.out.println("Rejected payment for " + regNo + ": amount must be positive.");
                return;
            }
            amountPaid += amount;
        }

        double getDue() {
            return Math.max(0, totalFee - amountPaid);
        }
    }

    static class HostelFeeAccount extends FeeAccount {
        HostelFeeAccount(String regNo, double totalFee) {
            super(regNo, totalFee);
        }

        void payInTwoInstallments(double amount) {
            pay(amount / 2);
            pay(amount / 2);
        }
    }

    static class ScholarshipFeeAccount extends FeeAccount {
        private final double scholarshipPercent;

        ScholarshipFeeAccount(String regNo, double totalFee, double scholarshipPercent) {
            super(regNo, totalFee);
            this.scholarshipPercent = Math.max(0, Math.min(100, scholarshipPercent));
        }

        double effectiveDue() {
            return getDue() * (1 - scholarshipPercent / 100);
        }
    }

    public static void main(String[] args) {
        FeeAccount plain = new FeeAccount("RA001", 150000);
        HostelFeeAccount hostel = new HostelFeeAccount("RA002", 200000);
        ScholarshipFeeAccount scholarship = new ScholarshipFeeAccount("RA003", 180000, 20);
        FeeAccount[] accounts = {plain, hostel, scholarship};

        plain.pay(150000);
        for (FeeAccount account : accounts) {
            if (account instanceof HostelFeeAccount) {
                ((HostelFeeAccount) account).payInTwoInstallments(60000);
                System.out.println("Hostel account due: Rs " + account.getDue());
            } else if (account instanceof ScholarshipFeeAccount) {
                System.out.println("Scholarship account effective due: Rs "
                        + ((ScholarshipFeeAccount) account).effectiveDue());
            } else {
                System.out.println("Plain account due: Rs " + account.getDue());
            }
        }
    }
}
