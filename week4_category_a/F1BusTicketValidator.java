import java.util.HashSet;
import java.util.Set;

public class F1BusTicketValidator {
    static class BusTicket {
        private final String passengerName;
        private final String destination;
        private boolean checkedIn;

        BusTicket(String passengerName, String destination) {
            if (!isMeaningful(passengerName) || !isMeaningful(destination)) {
                throw new IllegalArgumentException("Passenger name and destination must contain letters.");
            }
            this.passengerName = passengerName.trim();
            this.destination = destination.trim();
        }

        private static boolean isMeaningful(String value) {
            if (value == null || value.trim().isEmpty()) return false;
            for (char ch : value.trim().toCharArray()) {
                if (!Character.isLetter(ch) && ch != ' ') return false;
            }
            return true;
        }

        void markCheckedIn() {
            if (checkedIn) {
                System.out.println(passengerName + " is already checked in.");
                return;
            }
            checkedIn = true;
            System.out.println(passengerName + " checked in.");
        }

        static void processBatch(String[][] rawBookings) {
            int valid = 0, rejected = 0, duplicates = 0;
            Set<String> acceptedPairs = new HashSet<>();
            if (rawBookings != null) {
                for (String[] booking : rawBookings) {
                    if (booking == null || booking.length != 2) { rejected++; continue; }
                    try {
                        BusTicket ticket = new BusTicket(booking[0], booking[1]);
                        String key = ticket.passengerName.toLowerCase() + "|" + ticket.destination.toLowerCase();
                        if (!acceptedPairs.add(key)) duplicates++; else valid++;
                    } catch (IllegalArgumentException exception) { rejected++; }
                }
            }
            System.out.println("Valid: " + valid + " | Rejected: " + rejected
                    + " | Duplicates skipped: " + duplicates);
        }
    }

    public static void main(String[] args) {
        String[][] bookings = {{"Divya", "Chennai"}, {"", "Bangalore"}, {"Ravi123", "Pune"},
                {"Divya", "Chennai"}, {"    ", "   "}};
        BusTicket.processBatch(bookings);
        BusTicket ticket = new BusTicket("Divya", "Chennai");
        ticket.markCheckedIn();
        ticket.markCheckedIn();
    }
}
