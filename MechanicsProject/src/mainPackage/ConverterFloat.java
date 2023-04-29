package mainPackage;

public class ConverterFloat extends Converter {

    @Override
    public Spring systemOfSprings(String bitSequence) {
        // TODO: Implement the conversion of the binary bit sequence to a system of springs
        // that represents the floating point number
        return null;
    }

    @Override
    public int convertToDecimal(String bitSequence) {
        // Split the binary bit sequence into two parts: integer and fractional
        String integerPart = bitSequence.substring(0, bitSequence.indexOf('.'));
        String fractionalPart = bitSequence.substring(bitSequence.indexOf('.') + 1);

        // Convert the integer part to decimal using the ConverterInt class
        ConverterInt converterInt = new ConverterInt();
        int decimalIntegerPart = converterInt.convertToDecimal(integerPart);

        // Convert the fractional part to decimal using the formula:
        // decimalFractionalPart = sum(ai * 2^-i), where ai is the i-th bit of the fractional part
        double decimalFractionalPart = 0;
        for (int i = 0; i < fractionalPart.length(); i++) {
            int bit = Character.getNumericValue(fractionalPart.charAt(i));
            decimalFractionalPart += bit * Math.pow(2, -(i+1));
        }

        // Combine the decimal integer and fractional parts into a single decimal value
        return (int) (decimalIntegerPart + decimalFractionalPart);
    }
}

