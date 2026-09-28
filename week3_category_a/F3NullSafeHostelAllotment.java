public class F3NullSafeHostelAllotment {
    static class HostelRoom {
        private final String roomNo;
        private final int beds;
        private int occupied;

        HostelRoom(String roomNo, int beds, int occupied) {
            this.roomNo = roomNo;
            this.beds = beds;
            this.occupied = occupied;
        }

        boolean allot(String name) {
            if (occupied >= beds) {
                return false;
            }
            occupied++;
            return true;
        }
    }

    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
        if (rooms == null) {
            return null;
        }
        for (HostelRoom room : rooms) {
            if (room != null && room.occupied < room.beds) {
                return room;
            }
        }
        return null;
    }

    static void safeAllot(HostelRoom[] rooms, String studentName) {
        HostelRoom availableRoom = findAvailableRoom(rooms);
        if (availableRoom == null) {
            System.out.println("No rooms available for " + studentName);
            return;
        }
        availableRoom.allot(studentName);
        System.out.println(studentName + " allotted to room " + availableRoom.roomNo);
    }

    public static void main(String[] args) {
        HostelRoom[] rooms = {
                new HostelRoom("C-214", 3, 2),
                new HostelRoom("C-507", 2, 2)
        };
        safeAllot(rooms, "Divya");
        safeAllot(rooms, "Divya");

        // The array holds references, so methods receive references to the same room objects;
        // incrementing occupied mutates the original room rather than a copied room.
    }
}
