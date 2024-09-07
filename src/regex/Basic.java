package regex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Basic {
    public static boolean isIpV4(String ip) {
        return ip.matches("((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)");
    }

    public static boolean isMac(String mac) throws IOException {
        String oui = mac.substring(0, 8).replace(":", "").replace("-", "").replace(".", "");
        return mac.matches("([0-9A-Fa-f]{2}[.:-]){5}([0-9A-Fa-f]{2})") && Files.readAllLines(Path.of("ressources/mac-manufacturers")).contains(oui);
    }

    public static boolean isDecimal(String decimal) {
        return decimal.matches("[-+]?\\d+([.,]\\d+)?");
    }

    public static boolean isDate(String date) {
        return date.matches("(0?[1-9]|[12][0-9]|3[01])\\.(0?[1-9]|1[0-2])(\\.(\\d{4}|\\d{2}))?");
    }

    public static boolean isValidAustrianPlate(String plate) {
        return plate.matches("(BP-\\d{4,5})|([A-Z]{2}-(([1-9]\\d?[A-PR-Z]{3})|([1-9]\\d{1,2}[A-PR-Z]{2})|([1-9]\\d{2,3}[A-PR-Z]{1})))|([A-Z]-(([1-9]\\d{1,2}[A-PR-Z]{3})|([1-9]\\d{2,3}[A-PR-Z]{2})|([1-9]\\d{3,4}[A-PR-Z])))|(^([A-Z]{1,2}-((?=.{4,7}$)([A-PR-Z]{1,5}[1-9]\\d{0,4}))))");
    }

    public static void main(String[] args) {
        System.out.println("isIpV4(\"192.168.1.1\") = " + isIpV4("192.168.1.1"));// true
        System.out.println("isIpV4(\"0.0.0.0\") = " + isIpV4("0.0.0.0"));// true
        System.out.println("isIpV4(\"333.333.333.333\") = " + isIpV4("333.333.333.333")); // false
        System.out.println("isIpV4(\"192.168.1\") = " + isIpV4("192.168.1")); // false
        System.out.println("isIpV4(\"192.168.1.1.1\") = " + isIpV4("192.168.1.1.1")); // false
        System.out.println("isIpV4(\"0.0.0.256\") = " + isIpV4("0.0.0.256")); // false
        System.out.println();
        try {
            System.out.println("isMac(\"00:1A:2B:3C:4D:5E\") = " + isMac("00:1A:2B:3C:4D:5E")); // true
            System.out.println("isMac(\"00:1A:2B:3C:4D\") = " + isMac("00:1A:2B:3C:4D")); // false
            System.out.println("isMac(\"00:1A:2B:3C:4D:5E:6F\") = " + isMac("00:1A:2B:3C:4D:5E:6F")); // false
            System.out.println("isMac(\"00:1A:2B:3C:4D:5\") = " + isMac("00:1A:2B:3C:4D:5")); // false
            System.out.println("isMac(\"00:1A:2B:3C:4D:5G\") = " + isMac("00:1A:2B:3C:4D:5G")); // false
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println();

        System.out.println("isDecimal(\"123\") = " + isDecimal("123")); // true
        System.out.println("isDecimal(\"123.456\") = " + isDecimal("123.456")); // true
        System.out.println("isDecimal(\"-123.456\") = " + isDecimal("-123.456")); // true
        System.out.println("isDecimal(\"+123,456\") = " + isDecimal("+123,456")); // true
        System.out.println("isDecimal(\"123.\") = " + isDecimal("123.")); // false
        System.out.println("isDecimal(\"+-123\") = " + isDecimal("+-123")); // false
        System.out.println("isDecimal(\"123.456.789\") = " + isDecimal("123.456.789")); // false
        System.out.println();

        System.out.println("isDate(\"01.01.2021\") = " + isDate("01.01.2021")); // true
        System.out.println("isDate(\"1.1.2021\") = " + isDate("1.1.2021")); // true
        System.out.println("isDate(\"01.01.21\") = " + isDate("01.01.21")); // true
        System.out.println("isDate(\"1.1.21\") = " + isDate("1.1.21")); // true
        System.out.println("isDate(\"41.01.2021\") = " + isDate("41.01.2021")); // true
        System.out.println("isDate(\"01.13.2021\") = " + isDate("01.13.2021")); // true
        System.out.println("isDate(\"01.01.2021.2021\") = " + isDate("01.01.2021.2021")); // false
        System.out.println("isDate(\"01.01.2021.\") = " + isDate("01.01.2021.")); // false
        System.out.println("isDate(\"01.01.2021.21\") = " + isDate("01.01.2021.21")); // false
        System.out.println("isDate(\"011.01.2021\") = " + isDate("011.01.2021")); // true
        System.out.println("isDate(\"01.011.2021\") = " + isDate("01.011.2021")); // true
        System.out.println("isDate(\"01.01.20211\") = " + isDate("01.01.20211ay")); // false
        System.out.println();

        String[] correctPlates = {
                "BP-1381", "BP-18899", "KI-1AAA", "KI-99ZZZ", "KI-10AA", "KI-999ZZ",
                "KI-100A", "KI-9999Z", "W-10AAA", "W-999ZZZ", "W-100AA", "W-9999ZZ",
                "W-1000A", "W-99999Z", "GM-NDEN1", "W-A12345", "GM-A1234"
        };

        for (String plate : correctPlates) {
            System.out.println("isValidAustrianPlate(\"" + plate + "\") = " + isValidAustrianPlate(plate));
        }

        System.out.println();
        String[] incorrectPlates = {
                "BP-138", "BP-188999", "KI-1AAAA", "KI-99ZZZZ", "KI-00AAA", "KI-999ZZZ",
                "KI-000AA", "KI-9999ZZ", "W-00AAAA", "W-999ZZZZ", "W-000AAA", "W-9999ZZZ",
                "W-0000AA", "W-99999ZZ", "GM-N1", "W-A123456", "GM-A1234567", "W-100000", "W-AAAAA", "GM-01AA", "W-12345AAA", "GM-123456"
        };

        for (String plate : incorrectPlates) {
            System.out.println("isValidAustrianPlate(\"" + plate + "\") = " + isValidAustrianPlate(plate));
        }
    }
}
