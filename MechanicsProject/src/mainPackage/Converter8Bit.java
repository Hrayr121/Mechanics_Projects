package mainPackage;

public class Converter8Bit extends Converter {
    
    private Spring[] springs;

    // Constructor that initializes the springs
    public Converter8Bit() {
        springs = new Spring[8];
        for (int i = 0; i < springs.length; i++) {
            springs[i] = new Spring(1);
        }
    }

    // Override the abstract method to return the corresponding system of 8 springs
    @Override
    public Spring[] bitSequenceToSprings(int[] bits) {
        if (bits.length != 8) {
            throw new IllegalArgumentException("Input bit sequence must have length of 8");
        }
        Spring[] system = new Spring[8];
        for (int i = 0; i < system.length; i++) {
            if (bits[i] == 0) {
                system[i] = new Spring(0);
            } else {
                system[i] = springs[i];
            }
        }
        return system;
    }

    // Override the abstract method to evaluate the decimal value using the frequency amplitudes
    @Override
    public int evaluateDecimalValue(double[] freqAmplitudes) {
        double sum = 0;
        for (int i = 0; i < freqAmplitudes.length; i++) {
            sum += freqAmplitudes[i] * Math.pow(2, i);
        }
        return (int) sum;
    }

	@Override
	public Spring systemOfSprings(String bitSequence) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int convertToDecimal(String bitSequence) {
		// TODO Auto-generated method stub
		return 0;
	}
}
