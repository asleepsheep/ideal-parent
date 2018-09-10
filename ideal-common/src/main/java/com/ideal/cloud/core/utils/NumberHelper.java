package com.ideal.cloud.core.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class NumberHelper {
	public static final BigDecimal zoom = new BigDecimal(10000);

	public static boolean isEmpty(Object object) {
		return null == object ? true : false;
	}

	public static BigDecimal add(BigDecimal b1, BigDecimal b2) {
		if (!isEmpty(b1) && !isEmpty(b2))
			return b1.add(b2);
		return isEmpty(b1) ? (isEmpty(b2) ? null : b2) : b1;
	}

	public static BigDecimal subtract(BigDecimal b1, BigDecimal b2) {
		if (!isEmpty(b1) && !isEmpty(b2))
			return b1.subtract(b2);
		return isEmpty(b1) ? (isEmpty(b2) ? null : b2) : b1;
	}

	public static BigDecimal multiply(BigDecimal b1, BigDecimal b2) {
		if (!isEmpty(b1) && !isEmpty(b2))
			return b1.multiply(b2);
		return null;
	}

	public static BigDecimal multiply(String b1, BigDecimal b2) {
		if (!StringHelper.isEmpty(b1) && !isEmpty(b2))
			return new BigDecimal(b1).multiply(b2);
		return null;
	}

	public static BigDecimal divide(String s1, BigDecimal b2, int scale, RoundingMode roundingMode) {
		if (!StringHelper.isEmpty(s1) && !isEmpty(b2) && !isEqThanZero(b2))
			return new BigDecimal(s1).divide(b2, scale, roundingMode);
		return null;
	}

	public static BigDecimal divide(BigDecimal b1, BigDecimal b2, int scale, RoundingMode roundingMode) {
		if (!isEmpty(b1) && !isEmpty(b2) && !isEqThanZero(b2))
			return b1.divide(b2, scale, roundingMode);
		return null;
	}

	public static int getRandom(int min, int max) {
		return new Random().nextInt(max) % (max - min + 1) + min;
	}

	public static long getRandomLong(long begin, long end) {
		return begin + (long) (Math.random() * (end - begin));
	}

	public static long getRemainder(long molecula, int denominator) {
		if (denominator == 0)
			return 0l;
		return molecula % denominator;
	}

	public static long getFloor(long molecula, int denominator) {
		if (denominator == 0)
			return 0l;
		return (long) Math.floor(molecula / denominator);
	}

	public static boolean isEqThanZero(BigDecimal amount) {
		if (null == amount)
			return false;
		if (amount.compareTo(BigDecimal.ZERO) == 0)
			return true;
		return false;
	}

	public static boolean isGreatThanZero(BigDecimal amount) {
		if (null == amount)
			return false;
		if (amount.compareTo(BigDecimal.ZERO) > 0)
			return true;
		return false;
	}

	public static boolean isLessThanZero(BigDecimal amount) {
		if (null == amount)
			return false;
		if (amount.compareTo(BigDecimal.ZERO) < 0)
			return true;
		return false;
	}

	public static BigDecimal formatBigDecimal(BigDecimal b, int scale, RoundingMode roundingMode) {
		if (isEmpty(b))
			return null;
		return b.setScale(scale, roundingMode);
	}

	public static boolean isGreatThan(BigDecimal amount1, BigDecimal amount2) {
		return amount1.compareTo(amount2) > 0;
	}

	public static boolean isLessThan(BigDecimal amount1, BigDecimal amount2) {
		return amount1.compareTo(amount2) < 0;
	}

	public static boolean isEqualThan(BigDecimal amount1, BigDecimal amount2) {
		return amount1.compareTo(amount2) == 0;
	}

	public static boolean isGoRules(BigDecimal in) {
		if (null != in && in.compareTo(BigDecimal.ZERO) != 0) {
			return true;
		}
		return false;
	}

	public static boolean isGoRules(Integer in) {
		if (null != in && in != 0) {
			return true;
		}
		return false;
	}

	public static void zoomOut(Object obj) {
		try {
			String[] arrV = (String[]) obj.getClass().getField("zoomArr").get(obj);
			for (String methodName : arrV) {
				methodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
				BigDecimal val = (BigDecimal) obj.getClass().getMethod("get" + methodName).invoke(obj);
				val = zoomOut(val);
				obj.getClass().getMethod("set" + methodName, BigDecimal.class).invoke(obj, val);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static BigDecimal zoom(BigDecimal amount) {
		if (null == amount)
			return BigDecimal.ZERO;
		return amount;
	}

	public static BigDecimal zoomZero(BigDecimal amount) {
		if (null == amount || isLessThanZero(amount))
			return BigDecimal.ZERO;
		return amount;
	}

	public static BigDecimal zoomOut(BigDecimal amount) {
		return zoomOut(amount, zoom);
	}

	public static BigDecimal zoomOut(BigDecimal amount, BigDecimal zoom) {
		return zoomOut(amount, zoom, 0);
	}

	public static BigDecimal zoomOut(BigDecimal amount, BigDecimal zoom, int scale) {
		if (null == amount) {
			return amount;
		}
		return amount.multiply(zoom).setScale(scale);
	}

	public static long getTotalPage(long totalNum, long pageSize) {
		long residual = totalNum % pageSize;
		if (residual == 0l)
			return totalNum / pageSize;
		if (residual > 0)
			return totalNum / pageSize + 1l;
		return 0l;
	}

	public static long intToLong(int num) {
		return (long) num;
	}

	public static int longToint(long num) {
		return (int) num;
	}
	
	public static boolean hasNextPage(int pageNo,int pageSize,int total) {
        if (pageSize > 0) {
            return pageNo < (int) (total / pageSize + ((total % pageSize == 0) ? 0 : 1));
        } else {
            return false;
        }
	}
}
