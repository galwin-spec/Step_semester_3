public class F4LibraryMemberStaticDesign {
    static class BrokenLibraryMember {
        static String name;
        static String memberId;
        static int booksIssued;

        BrokenLibraryMember(String name, String memberId, int booksIssued) {
            BrokenLibraryMember.name = name;
            BrokenLibraryMember.memberId = memberId;
            BrokenLibraryMember.booksIssued = booksIssued;
        }
    }

    static class LibraryMember {
        private final String name;
        private final String memberId;
        private final int booksIssued;
        private static final String libraryName = "SRM Central Library";
        private static int memberCount;

        LibraryMember(String name, int booksIssued) {
            this.name = name;
            this.booksIssued = booksIssued;
            memberCount++;
            this.memberId = "LM-" + (1000 + memberCount);
        }

        void printMemberCard() {
            System.out.println(name + " | " + memberId + " | " + libraryName
                    + " | Books issued: " + booksIssued);
        }

        static void printTotalMembers() {
            System.out.println("Total members: " + memberCount);
        }
    }

    public static void main(String[] args) {
        BrokenLibraryMember aditiBroken = new BrokenLibraryMember("Aditi", "LM-1001", 2);
        BrokenLibraryMember rohanBroken = new BrokenLibraryMember("Rohan", "LM-1002", 1);
        System.out.println("Broken version: " + aditiBroken.name + ", " + rohanBroken.name
                + " (Aditi's data was overwritten)");

        // name, memberId, and booksIssued belong to each member, so static would wrongly
        // share them. libraryName and memberCount are common to every member and can be static.
        LibraryMember aditi = new LibraryMember("Aditi", 2);
        LibraryMember rohan = new LibraryMember("Rohan", 1);
        aditi.printMemberCard();
        rohan.printMemberCard();
        LibraryMember.printTotalMembers();
    }
}
