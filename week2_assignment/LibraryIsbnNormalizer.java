import java.util.Scanner;

public class LibraryIsbnNormalizer {
    public static String normalizeCode(String raw) {
        String trimmedCode = raw.trim();
        int publisherCodeEnd = Math.min(3, trimmedCode.length());
        return trimmedCode.substring(0, publisherCodeEnd).toUpperCase()
                + trimmedCode.substring(publisherCodeEnd);
    }

    public static String validateAndFormat(String code) {
        if (code.length() != 13) {
            return "Invalid: code must be exactly 13 characters";
        }

        for (int index = 0; index < 3; index++) {
            if (!Character.isLetter(code.charAt(index))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        for (int index = 3; index < code.length(); index++) {
            if (!Character.isDigit(code.charAt(index))) {
                return "Invalid: code body must contain 10 digits";
            }
        }

        String publisherCode = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalogNumber = code.substring(7, 13);
        StringBuilder formattedCode = new StringBuilder();
        formattedCode.append('[').append(publisherCode).append("] YEAR: ")
                .append(year).append(" | CATALOG: ").append(catalogNumber);
        return formattedCode.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ISBN-style code: ");
        String normalizedCode = normalizeCode(scanner.nextLine());
        System.out.println(validateAndFormat(normalizedCode));
        scanner.close();
    }
}
