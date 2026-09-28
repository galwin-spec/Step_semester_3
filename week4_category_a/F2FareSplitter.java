import java.util.Arrays;

public class F2FareSplitter {
    static class FareSplitter {
        private final String tripId;
        private final double totalFare;
        private final int passengerCount;

        FareSplitter(String tripId, double totalFare, int passengerCount) {
            if (tripId == null || tripId.trim().isEmpty() || totalFare < 0 || passengerCount <= 0)
                throw new IllegalArgumentException("Invalid trip split details.");
            this.tripId = tripId;
            this.totalFare = totalFare;
            this.passengerCount = passengerCount;
        }
        FareSplitter(String tripId, double totalFare) { this(tripId, totalFare, 2); }
        FareSplitter(String tripId) { this(tripId, 0, 2); }

        double[] fareBreakdown() {
            long totalPaise = Math.round(totalFare * 100);
            long base = totalPaise / passengerCount;
            long remainder = totalPaise % passengerCount;
            double[] shares = new double[passengerCount];
            for (int i = 0; i < passengerCount; i++)
                shares[i] = (base + (i == passengerCount - 1 ? remainder : 0)) / 100.0;
            return shares;
        }
        boolean isConfirmationOverdue(int confirmed, int expected) {
            if (confirmed < 0 || expected < 0) throw new IllegalArgumentException("Counts cannot be negative.");
            return confirmed < expected;
        }
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new FareSplitter("TRIP001", 100000, 3).fareBreakdown()));
        System.out.println(Arrays.toString(new FareSplitter("TRIP003").fareBreakdown()));
    }
}
