import java.util.Scanner;

public class MaskedPhoneNumberFormatter {
    public static String maskPhoneNumber(String phone) {
        if (phone.length() != 10) {
            return "Invalid phone number";
        }

        for (int index = 0; index < phone.length(); index++) {
            if (!Character.isDigit(phone.charAt(index))) {
                return "Invalid phone number";
            }
        }

        StringBuilder maskedPhone = new StringBuilder("XXXXXX" + phone.substring(6));
        maskedPhone.insert(6, '-');
        return maskedPhone.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a 10-digit phone number: ");
        System.out.println(maskPhoneNumber(scanner.nextLine().trim()));
        scanner.close();
    }
}
