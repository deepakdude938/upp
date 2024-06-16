package ignore;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RCode {

	public static void main(String[] args) {
		int[] array = { 9, 8, 7, 6, 5, 10, 10, 9, 0, -1 };
		int second = secondLargest(array);
		System.out.println(second);
	}

	private static int secondLargest(int[] array) {

Integer s= Arrays.stream(array).boxed().distinct().sorted(Comparator.reverseOrder()).collect(Collectors.toList()).get(1);

		return s;
	}

}
