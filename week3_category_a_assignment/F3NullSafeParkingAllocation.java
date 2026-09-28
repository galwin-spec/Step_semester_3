public class F3NullSafeParkingAllocation {
    static class ParkingSlot {
        private final String slotNo;
        private final int capacity;
        private int occupiedCount;

        ParkingSlot(String slotNo, int capacity, int occupiedCount) {
            this.slotNo = slotNo;
            this.capacity = capacity;
            this.occupiedCount = occupiedCount;
        }

        boolean allot(String vehicleNo) {
            if (occupiedCount >= capacity) {
                return false;
            }
            occupiedCount++;
            return true;
        }
    }

    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {
        if (slots == null) {
            return null;
        }
        for (ParkingSlot slot : slots) {
            if (slot != null && slot.occupiedCount < slot.capacity) {
                return slot;
            }
        }
        return null;
    }

    static void safeAllot(ParkingSlot[] slots, String vehicleNo) {
        ParkingSlot availableSlot = findAvailableSlot(slots);
        if (availableSlot == null) {
            System.out.println("No slots available for " + vehicleNo);
            return;
        }
        availableSlot.allot(vehicleNo);
        System.out.println(vehicleNo + " allotted to slot " + availableSlot.slotNo);
    }

    public static void main(String[] args) {
        ParkingSlot[] slots = {
                new ParkingSlot("A1", 4, 3),
                new ParkingSlot("A2", 5, 5)
        };
        safeAllot(slots, "TN09AB1234");
        safeAllot(slots, "TN09AB1234");

        // The array contains references to the original slots, not copied slot objects;
        // changing occupiedCount therefore changes the same object seen by the caller.
    }
}
