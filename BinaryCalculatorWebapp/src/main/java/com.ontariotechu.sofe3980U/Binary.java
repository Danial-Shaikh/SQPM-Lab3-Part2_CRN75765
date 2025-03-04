package com.ontariotechu.sofe3980U;
/**
 * Represents an unsigned binary number.
 */
public class Binary {
    private String number = "0"; // Holds the binary string representation

    /**
     * Constructor for the Binary class.
     * Ensures that the input string consists only of '0' and '1'.
     * If invalid input is given, defaults to "0".
     * Leading zeros are removed, and an empty input is treated as "0".
     *
     * @param number A string consisting of binary digits.
     */
    public Binary(String number) {
        if (number == null || number.isEmpty()) {
            this.number = "0"; // Assign "0" if input is empty or null
            return;
        }
        // Check if all characters are valid binary digits
        for (char ch : number.toCharArray()) {
            if (ch != '0' && ch != '1') {
                this.number = "0"; // Default to "0" if invalid characters exist
                return;
            }
        }
        // Strip leading zeros
        int index;
        for (index = 0; index < number.length(); index++) {
            if (number.charAt(index) != '0') {
                break;
            }
        }
        // Assign the processed number, ensuring it isn't empty
        this.number = (index == number.length()) ? "0" : number.substring(index);
    }

    /**
     * Retrieves the binary representation.
     * @return Binary number as a string.
     */
    public String getValue() {
        return this.number;
    }

    /**
     * Adds two binary numbers together.
     * @param num1 First binary operand.
     * @param num2 Second binary operand.
     * @return A new Binary object containing the sum.
     */
    public static Binary add(Binary num1, Binary num2) {
        int ind1 = num1.number.length() - 1;
        int ind2 = num2.number.length() - 1;
        int carry = 0;
        StringBuilder sumResult = new StringBuilder();

        while (ind1 >= 0 || ind2 >= 0 || carry != 0) {
            int sum = carry;
            if (ind1 >= 0) sum += (num1.number.charAt(ind1--) == '1') ? 1 : 0;
            if (ind2 >= 0) sum += (num2.number.charAt(ind2--) == '1') ? 1 : 0;
            carry = sum / 2;
            sumResult.insert(0, (sum % 2 == 0) ? "0" : "1");
        }
        return new Binary(sumResult.toString());
    }

    /**
     * Performs bitwise OR operation on two binary values.
     * @param num1 First operand.
     * @param num2 Second operand.
     * @return A Binary object with the OR result.
     */
    public static Binary or(Binary num1, Binary num2) {
        int maxLength = Math.max(num1.number.length(), num2.number.length());
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < maxLength; i++) {
            char bit1 = (i < num1.number.length()) ? num1.number.charAt(num1.number.length() - 1 - i) : '0';
            char bit2 = (i < num2.number.length()) ? num2.number.charAt(num2.number.length() - 1 - i) : '0';
            result.insert(0, (bit1 == '1' || bit2 == '1') ? '1' : '0');
        }
        return new Binary(result.toString());
    }

    /**
     * Performs bitwise AND operation on two binary numbers.
     * @param num1 First operand.
     * @param num2 Second operand.
     * @return A Binary object with the AND result.
     */
    public static Binary and(Binary num1, Binary num2) {
        int maxLength = Math.max(num1.number.length(), num2.number.length());
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < maxLength; i++) {
            char bit1 = (i < num1.number.length()) ? num1.number.charAt(num1.number.length() - 1 - i) : '0';
            char bit2 = (i < num2.number.length()) ? num2.number.charAt(num2.number.length() - 1 - i) : '0';
            result.insert(0, (bit1 == '1' && bit2 == '1') ? '1' : '0');
        }
        return new Binary(result.toString());
    }

    /**
     * Multiplies two binary numbers using repeated addition and shifting.
     * @param num1 First operand.
     * @param num2 Second operand.
     * @return A Binary object with the multiplication result.
     */
    public static Binary multiply(Binary num1, Binary num2) {
        Binary result = new Binary("0");
        Binary tempMultiplier = new Binary(num1.number);

        for (int i = num2.number.length() - 1; i >= 0; i--) {
            if (num2.number.charAt(i) == '1') {
                result = add(result, tempMultiplier);
            }
            tempMultiplier = new Binary(tempMultiplier.number + "0"); // Left shift
        }
        return result;
    }
}