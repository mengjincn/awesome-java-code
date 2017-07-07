package com.java.code.util;

public class MathUtils {
	/**
	 * Don't let anyone instantiate this class.
	 */
	private MathUtils() {
	}
	
	/**
	 * 判断 N 是不是素数
	 * @param N 是正整数
	 * @return true：N是素数；false：N不是素数
	 */
	public static boolean isPrime(int N) {
		if (N < 2)
			return false;
		for (int i = 2; i * i <= N; i++)
			if (N % i == 0)
				return false;
		return true;
	}
	/**
	 * 计算 c 的平方根
	 * @param c 大于等于0的实数
	 * @return 如果c小于0，则返回Double.NaN; 否则返回 c 的平方根
	 */
	public static double sqrt(double c) {
		if (c < 0.0)
			return Double.NaN;
		double err = 1e-15;
		double t = c;
		while (Math.abs(t - c / t) > err * t)
			t = (c / t + t) / 2.0;
		return t;
	}
}
