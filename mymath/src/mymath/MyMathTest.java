package mymath;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

class MyMathTest {
	
	
	/**
	 * Geeft de mediaan van de drie verschillende gegeven getallen terug.
	 * @pre De argumenten zijn verschillend.
	 *     | x != y && y != z && x != z
	 * @post Het resultaat is gelijk aan x, y, of z
	 *     | result == x || result == y || result == z
	 * @post Het resultaat is groter dan x of y of z
	 *     | x < result || y < result || z < result
	 * @post Het resultaat is kleiner dan x of y of z
	 *     | result < x || result < y || result < z 
	 */
	int mediaan(int x, int y, int z) {
		return
				x < y ?
						y < z ?
								y
						:
								x < z ?
										z
								:
										x
				:
						x < z ?
								x
						:
								y < z ?
										z
								:
										y;
	}
	
	@Test
	void testMediaan() {
		assertEquals(20, mediaan(10, 20, 30));
		assertEquals(20, mediaan(20, 10, 30));
		assertEquals(20, mediaan(10, 30, 20));
		assertEquals(20, mediaan(30, 20, 10));
		assertEquals(20, mediaan(30, 10, 20));
		assertEquals(20, mediaan(20, 30, 10));
	}
	
	/**
	 * Geeft het aantal voorkomens van 'waarde' in 'getallen' terug.
	 * @pre Het argument `getallen` wijst naar een array; het is geen null-verwijzing.
	 *    | getallen != null
	 * @post Het resultaat is gelijk aan het aantal indices in `getallen` waarop `waarde` voorkomt.
	 *    | result == IntStream.range(0, getallen.length).filter(i -> getallen[i] == waarde).count()
	 */
	int tel(int[] getallen, int waarde) {
		int result = 0;
		for (int i = 0; i < getallen.length; i++)
			if (getallen[i] == waarde)
				result++;
		return result;
	}
	
	/**
	 * Verhoogt het element op index `index` in array `array` met `bedrag`.
	 * 
	 * @pre Het argument `array` wijst naar een array.
	 *     | array != null
	 * @pre De gegeven index is een geldige index voor `array`.
	 *     | 0 <= index && index < array.length
	 * @mutates | array
	 * @post De waarde in `array` op de gegeven index is gelijk aan de oude waarde plus het gegeven bedrag.
	 *     | array[index] == old(array[index]) + bedrag
	 * @post De waarden in `array` op de andere indices is ongewijzigd.
	 *     | IntStream.range(0, array.length).allMatch(i -> i == index || array[i] == old(array.clone())[i])
	 */
	void verhoogElement(int[] array, int index, int bedrag) {
		array[index] += bedrag;
	}
	
	/**
	 * Verwisselt de elementen op de gegeven indices in de gegeven array met elkaar.
	 * @pre | getallen != null
	 * @pre De gegeven indices zijn geldig.
	 *      | 0 <= index1 && index1 < getallen.length && 0 <= index2 && index2 < getallen.length
	 * @mutates | getallen
	 * @post | getallen[index1] == old(getallen.clone())[index2]
	 * @post | getallen[index2] == old(getallen.clone())[index1]
	 * @post | IntStream.range(0, getallen.length).allMatch(i -> i == index1 || i == index2 || getallen[i] == old(getallen.clone())[i])
	 */
	void verwissel(int[] getallen, int index1, int index2) {
		int tmp = getallen[index1];
		getallen[index1] = getallen[index2];
		getallen[index2] = tmp;
	}
	
	@Test
	void testVerwissel() {
		int[] mijnGetallen = {10, 20, 30};
		verwissel(mijnGetallen, 0, 2);
		assertArrayEquals(new int[] {30, 20, 10}, mijnGetallen);
		
		int[] jouwGetallen = {10, 20, 30};
		verwissel(jouwGetallen, 2, 1);
		assertArrayEquals(new int[] {10, 30, 20}, jouwGetallen);
		assertEquals(10, jouwGetallen[0]);
		assertEquals(30, jouwGetallen[1]);
		assertEquals(20, jouwGetallen[2]);
	}
	
	/**
	 * Vervangt elk getal in de gegeven array door zijn negatie.
	 * @pre Het argument `getallen` wijst naar een array.
	 *     | getallen != null
	 * @post Voor elke positie in `getallen` geldt dat de nieuwe waarde op die positie gelijk is aan de negatie van de oude waarde op die positie.
	 *     | IntStream.range(0, getallen.length).allMatch(i -> getallen[i] == -old(getallen.clone())[i]) 
	 */
	// voeg bovenaan (tussen de package-regel en de class-regel) 'import java.util.stream.IntStream;' toe als Eclipse dit niet zelf doet.
	void negatie(int[] getallen) {
		for (int i = 0; i < getallen.length; i++)
			getallen[i] = -getallen[i];
	}
	
	/**
	 * Geeft de index terug van het eerste voorkomen van `needle` in `haystack`, of -1 als `needle` niet voorkomt in `haystack`.
	 * 
	 * @pre | haystack != null
	 * @post Als de needle zich op index 0 bevindt, dan is het resultaat gelijk aan 0 (Deze postconditie is een speciaal geval van de laatste, ter illustratie.)
	 *      | !(haystack.length > 0 && haystack[0] == needle) || result == 0
	 * @inspects | haystack
	 * @post Als het resultaat -1 is, dan komt `needle` niet voor in `haystack`
	 *      | result != -1 || IntStream.range(0, haystack.length).allMatch(i -> haystack[i] != needle)
	 * @post Als het resultaat niet -1 is, dan is het een geldige index in `haystack`
	 *      | result == -1 || 0 <= result && result < haystack.length
	 * @post Als het resultaat niet -1 is, dan is de waarde in `haystack` op index `result` gelijk aan `needle`
	 *      | result == -1 || haystack[result] == needle
	 * @post Als het resultaat niet -1 is, dan is `result` de kleinste index waar `needle` voorkomt.
	 *      | result == -1 || IntStream.range(0, haystack.length).allMatch(i -> haystack[i] != needle || result <= i)
	 */
	int find(int[] haystack, int needle) {
		for (int i = 0; i < haystack.length; i++)
			if (haystack[i] == needle)
				return i;
		return -1;
	}
	
	@Test
	void testFind() {
		int[] mijnHaystack = {10, 20, 30};
		assertEquals(-1, find(mijnHaystack, 15));
		assertEquals(0, find(mijnHaystack, 10));
		assertEquals(1, find(mijnHaystack, 20));
		assertEquals(2, find(mijnHaystack, 30));
	}
	
	void insert(int[] values, int n) {
		int i = 0;
		int v = values[n];
		while (i < n && values[i] < v)
			i++;
		for (int j = n; i < j; j--)
			values[j] = values[j - 1];
		values[i] = v;
	}
	
	/**
	 * Sorteert de getallen in de gegeven array van klein naar groot.
	 * @pre | values != null
	 * @mutates | values
	 * @post De elementen van `values` staan in oplopende volgorde.
	 *     | IntStream.range(1, values.length).allMatch(i -> values[i - 1] <= values[i])
	 * @post De inhoud van de array, gezien als een multiset (verzameling met mogelijks meerdere voorkomens van een element), is ongewijzigd gebleven.
	 *     | IntStream.range(0, values.length).allMatch(i ->
	 *     |     IntStream.range(0, values.length).filter(j -> values[j] == values[i]).count() ==
	 *     |     IntStream.range(0, values.length).filter(j -> old(values.clone())[j] == values[i]).count()
	 *     | )
	 */
	void sort(int[] values) {
		for (int i = 1; i < values.length; i++)
			insert(values, i);
	}
	
	@Test
	void testSort() {
		int[] mijnValues = {40, 20, 30, 10};
		sort(mijnValues);
		assertArrayEquals(new int[] {10, 20, 30, 40}, mijnValues);
		
		int[] jouwValues = {20, 20, 15, 30, 15, 40, 0};
		sort(jouwValues);
		assertArrayEquals(new int[] {0, 15, 15, 20, 20, 30, 40}, jouwValues);
	}

	@Test
	void testSqrt() {
		assertEquals(3, MyMath.sqrt(9));
		assertEquals(0, MyMath.sqrt(0));
		assertEquals(3, MyMath.sqrt(15));
		assertEquals(4, MyMath.sqrt(16));
	}
	
	@Test
	void testMax3() {
		assertEquals(30, MyMath.max3(10, 20, 30));
		assertEquals(30, MyMath.max3(20, 10, 30));
		assertEquals(30, MyMath.max3(10, 30, 20));
		assertEquals(30, MyMath.max3(20, 30, 10));
		assertEquals(30, MyMath.max3(30, 10, 20));
		assertEquals(30, MyMath.max3(30, 20, 10));
	}
	
	// TODO: Schrijf grondige tests voor mediaan, verwissel, find, en sort.
	
	@Test
	void testTel() {
		assertEquals(0, tel(new int[] {10, 20, 30}, 15));
		assertEquals(1, tel(new int[] {10, 20, 30}, 20));
		assertEquals(2, tel(new int[] {10, 20, 30, 20}, 20));
		assertEquals(3, tel(new int[] {10, 20, 10, 30, 10}, 10));
	}
	
	@Test
	void testVerhoogElement() {
		int[] mijnArray = {10, 20, 30};
		verhoogElement(mijnArray, 1, 5);
		assertArrayEquals(new int[] {10, 25, 30}, mijnArray);
	}
	
	@Test
	void testNegatie() {
		int[] mijnArray = {10, -20, 30};
		negatie(mijnArray);
		assertArrayEquals(new int[] {-10, 20, -30}, mijnArray);
	}

}
