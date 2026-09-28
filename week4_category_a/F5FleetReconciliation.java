public class F5FleetReconciliation {
    static class BusTicketAccount {
        static final String DEPOT_NAME;
        static { DEPOT_NAME = "SRM Central Depot"; }
        final String bookingId;
        final double ticketFare;
        BusTicketAccount(String bookingId, double ticketFare) {
            if (bookingId == null || bookingId.trim().isEmpty() || ticketFare < 0) throw new IllegalArgumentException("Invalid account.");
            this.bookingId = bookingId; this.ticketFare = ticketFare;
        }
        BusTicketAccount(String bookingId) { this(bookingId, 0); }
        final double calculatePenalty(int minutesLate) {
            if (minutesLate < 0) throw new IllegalArgumentException("Minutes cannot be negative.");
            if (minutesLate == 0) return 0;
            return Math.max(ticketFare * .01, ticketFare * minutesLate * .005);
        }
    }
    static class SleeperTicketAccount extends BusTicketAccount {
        SleeperTicketAccount(String bookingId, double ticketFare) { super(bookingId, ticketFare); }
    }
    static void processAccount(BusTicketAccount account, double amount, int minutesLate) {
        double penalty = account.calculatePenalty(minutesLate);
        String type = account instanceof SleeperTicketAccount ? "sleeper" : "regular";
        System.out.println(account.bookingId + " (" + type + ") settled: Rs " + amount + ", penalty: Rs " + penalty);
    }
    static void processBatch(BusTicketAccount[] accounts, double[] amounts, int[] minutesLateArray) {
        if (accounts == null || amounts == null || minutesLateArray == null || accounts.length != amounts.length || accounts.length != minutesLateArray.length)
            throw new IllegalArgumentException("Parallel arrays must have matching lengths.");
        int processed = 0, nullSkipped = 0, sleeper = 0, regular = 0; double totalPenalties = 0;
        for (int i = 0; i < accounts.length; i++) {
            BusTicketAccount account = accounts[i];
            if (account == null) { nullSkipped++; continue; }
            processAccount(account, amounts[i], minutesLateArray[i]);
            totalPenalties += account.calculatePenalty(minutesLateArray[i]); processed++;
            if (account instanceof SleeperTicketAccount) sleeper++; else regular++;
        }
        System.out.println(processed + " processed | " + nullSkipped + " null skipped | " + sleeper + " sleeper | " + regular + " regular | grand total penalties = Rs " + totalPenalties);
    }
    public static void main(String[] args) {
        BusTicketAccount[] accounts = {new SleeperTicketAccount("BK001", 2000), null, new BusTicketAccount("BK002", 1200)};
        processBatch(accounts, new double[]{1200, 900, 700}, new int[]{10, 5, 0});
    }
}
