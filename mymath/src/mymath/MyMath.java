package mymath;

public class MyMath {

	/**
	 * Returns the square root (rounded down) of the given nonnegative number.
	 * 
	 * @pre The given number must be nonnegative.
	 *    | 0 <= x
	 * @post The result is the greatest nonnegative integer whose square is not greater than the given number.
	 *    | 0 <= result &&
	 *    | result * result <= x &&
	 *    | x < (result + 1) * (result + 1)
	 */
	static int sqrt(int x) {
		int result = 0;
		while ((result + 1) * (result + 1) <= x)
			result++;
		return result;
	}

}
