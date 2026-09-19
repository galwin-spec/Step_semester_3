import java.util.Scanner;

public class BankTransactionReference {
    public static String normalizeReference(String raw) {
        String trimmedReference = raw.trim();
        int bankCodeEnd = Math.min(3, trimmedReference.length());
        return trimmedReference.substring(0, bankCodeEnd).toUpperCase()
                + trimmedReference.substring(bankCodeEnd);
    }

    public static String validateAndFormat(String reference) {
        if (reference.length() != 14) {
            return "Invalid: reference must be exactly 14 characters";
        }

        for (int index = 0; index < 3; index++) {
            if (!Character.isLetter(reference.charAt(index))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

        for (int index = 3; index < reference.length(); index++) {
            if (!Character.isDigit(reference.charAt(index))) {
                return "Invalid: reference body must contain 11 digits";
            }
        }

        String bankCode = reference.substring(0, 3);
        String date = reference.substring(3, 9);
        String sequence = reference.substring(9, 14);
        StringBuilder formattedReference = new StringBuilder();
        formattedReference.append('[').append(bankCode).append("] DATE: ")
                .append(date, 0, 2).append('/')
                .append(date, 2, 4).append('/')
                .append(date, 4, 6).append(" | SEQ: ")
                .append(sequence);
        return formattedReference.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter transaction reference: ");
        String normalizedReference = normalizeReference(scanner.nextLine());
        System.out.println(validateAndFormat(normalizedReference));
        scanner.close();
    }
}
