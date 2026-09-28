public class F5FeeHostelMiniSystem {
    static class FeeAccount {
        private final double totalFee;
        private double amountPaid;

        FeeAccount(double totalFee) {
            this.totalFee = totalFee;
        }

        void pay(double amount) {
            if (amount <= 0) {
                System.out.println("Payment rejected: amount must be positive.");
                return;
            }
            amountPaid += amount;
        }

        double getDue() {
            return Math.max(0, totalFee - amountPaid);
        }
    }

    static class HostelFeeAccount extends FeeAccount {
        HostelFeeAccount(double totalFee) {
            super(totalFee);
        }

        void payInTwoInstallments(double amount) {
            pay(amount / 2);
            pay(amount / 2);
        }
    }

    static class HostelRoom {
        private final String roomNo;

        HostelRoom(String roomNo) {
            this.roomNo = roomNo;
        }
    }

    static class SrmStudent {
        private final String name;
        private final String regNo;
        private final HostelFeeAccount feeAccount;
        private final HostelRoom room;
        private static int totalStudents;

        SrmStudent(String name, String regNo, HostelFeeAccount feeAccount, HostelRoom room) {
            this.name = name;
            this.regNo = regNo;
            this.feeAccount = feeAccount;
            this.room = room;
            totalStudents++;
        }

        String fullStatus() {
            String roomNumber = room == null ? "unallotted" : room.roomNo;
            return name + " | Due: Rs " + feeAccount.getDue() + " | Room: " + roomNumber;
        }
    }

    public static void main(String[] args) {
        HostelRoom room214 = new HostelRoom("C-214");
        HostelRoom room507 = new HostelRoom("C-507");
        SrmStudent ravi = new SrmStudent("Ravi", "RA001", new HostelFeeAccount(200000), room214);
        SrmStudent anitha = new SrmStudent("Anitha", "RA002", new HostelFeeAccount(180000), room507);
        SrmStudent karthik = new SrmStudent("Karthik", "RA003", new HostelFeeAccount(200000), null);

        ravi.feeAccount.payInTwoInstallments(60000);
        anitha.feeAccount.pay(-500);

        System.out.println(ravi.fullStatus());
        System.out.println(anitha.fullStatus());
        System.out.println(karthik.fullStatus());
        System.out.println("Total students: " + SrmStudent.totalStudents);
    }
}
