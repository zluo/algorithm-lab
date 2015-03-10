import java.util.*;

public class Inversions {

	// warning: a is modified during processing
	public static int inversions(int[] a, int low, int high) {
		if (high - low < 2)
			return 0;
		int mid = (low + high) >>> 1;
		int res = inversions(a, low, mid) + inversions(a, mid, high);
		int[] b = Arrays.copyOfRange(a, low, mid);
		for (int i = low, j = mid, k = 0; k < b.length; i++)
			if (j == high || b[k] <= a[j]) {
				a[i] = b[k++];
			} else {
				a[i] = a[j++];
				res += b.length - k;
			}
		return res;
	}

	// random test
	public static void main(String[] args) {
		Random rnd = new Random(1);
		for (int step = 0; step < 1000; step++) {
			int n = rnd.nextInt(100);
			int[] p = new int[n];
			for (int i = 0; i < n; i++)
				p[i] = rnd.nextInt(n);
			int res1 = inversions(p.clone(), 0, p.length);
			int res2 = slowInversions(p);
			if (res1 != res2)
				throw new RuntimeException();
		}
	}

	static int slowInversions(int[] p) {
		int res = 0;
		for (int i = 0; i < p.length; i++)
			for (int j = 0; j < i; j++)
				if (p[j] > p[i])
					++res;
		return res;
	}
}
