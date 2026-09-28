public class F4BoardingPenaltyCalculator {
    static final class BoardingPenaltyCalculator {
        private final double minimumPenaltyPercent;
        BoardingPenaltyCalculator(double minimumPenaltyPercent) {
            if (minimumPenaltyPercent < 0) throw new IllegalArgumentException("Minimum rate cannot be negative.");
            this.minimumPenaltyPercent = minimumPenaltyPercent;
        }
        final double calculatePenalty(double ticketFare, int minutesLate) {
            if (ticketFare < 0 || minutesLate < 0) throw new IllegalArgumentException("Fare and minutes must be non-negative.");
            if (minutesLate == 0) return 0.0;
            int firstTier = Math.min(minutesLate, 5);
            int secondTier = Math.min(Math.max(minutesLate - 5, 0), 10);
            int thirdTier = Math.max(minutesLate - 15, 0);
            double tiered = ticketFare * (firstTier * .005 + secondTier * .01 + thirdTier * .02);
            return Math.max(tiered, ticketFare * minimumPenaltyPercent / 100);
        }
    }
    public static void main(String[] args) {
        BoardingPenaltyCalculator calculator = new BoardingPenaltyCalculator(1);
        System.out.println("Rs " + calculator.calculatePenalty(1000, 0));
        System.out.println("Rs " + calculator.calculatePenalty(1000, 1));
        System.out.println("Rs " + calculator.calculatePenalty(1000, 16));
    }
}
