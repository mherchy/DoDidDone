package com.example.dodiddone.metier.typedValues.calcul;

import com.example.dodiddone.metier.Ligne;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class LigneMath {

    public static BigDecimal sum(Set<Ligne> lignes) {
        BigDecimal op = BigDecimal.ZERO;
        for (Ligne ligne: lignes) {
            op = op.add((BigDecimal)ligne.getValeur().getTyped());
        }
        return op;
    }


    public static BigDecimal moy(Set<Ligne> lignes) {
        if(lignes.size() == 0) {
            return BigDecimal.ZERO;
        }
        return sum(lignes).divide(new BigDecimal(lignes.size()));
    }

    public static BigDecimal variance(Set<Ligne> lignes) {
        BigDecimal op = BigDecimal.ZERO;
        if(lignes.size() == 0) {
            return op;
        }
        BigDecimal moy = moy(lignes);
        for (Ligne ligne: lignes) {
            op = op.add(
                    ((BigDecimal)ligne.getValeur().getTyped())
                            .subtract(moy)
                            .pow(2)
            );
        }
        op = op.divide(new BigDecimal(lignes.size()));
        return op;
    }

    public static BigDecimal ectype(Set<Ligne> lignes) {
        return sqrt(variance(lignes));
    }


    private static LinkedList<Map.Entry<BigDecimal,Integer>> getSortedBigDecimal(Set<Ligne> lignes) {
        HashMap<BigDecimal,Integer> map = new HashMap<>();
        for (Ligne ligne: lignes) {
            BigDecimal data = (BigDecimal) ligne.getValeur().getTyped();
            int nb = map.containsKey(data) ? map.get(data) : 0;
            map.put(data, nb+1);
        }

        LinkedList<Map.Entry<BigDecimal, Integer>> list = new LinkedList<>(map.entrySet());
        list.sort(new Comparator<Map.Entry<BigDecimal, Integer>>() {
            @Override
            public int compare(Map.Entry<BigDecimal, Integer> o1, Map.Entry<BigDecimal, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        return list;
    }


    public static String top3(Set<Ligne> lignes) {
        String out = "";
        int i = 1;
        LinkedList<Map.Entry<BigDecimal,Integer>> sorted = getSortedBigDecimal(lignes);
        if(lignes.size() > 0) {
            Map.Entry<BigDecimal,Integer> item = sorted.pollLast();
            out = "("+item.getValue()+") "+item.getKey().toString();
            while (i<3 && lignes.size() > 0) {
                item = sorted.pollLast();
                out = out.concat("\n")
                        .concat("("+item.getValue()+") "+item.getKey().toString());
                i++;
            }
        }
        return out;
    }

    public static BigDecimal max(Set<Ligne> lignes) {
        LinkedList<Map.Entry<BigDecimal,Integer>> sorted = getSortedBigDecimal(lignes);
        if(lignes.size() == 0) {
            return BigDecimal.ZERO;
        }
        return sorted.pollLast().getKey();
    }

    public static BigDecimal min(Set<Ligne> lignes) {
        LinkedList<Map.Entry<BigDecimal,Integer>> sorted = getSortedBigDecimal(lignes);
        if(lignes.size() == 0) {
            return BigDecimal.ZERO;
        }
        return sorted.pollFirst().getKey();
    }



    private static Map<Boolean, Integer> countBool(Set<Ligne> lignes) {
        HashMap<Boolean,Integer> map = new HashMap<>();
        map.put(true, 0);
        map.put(false, 0);
        for (Ligne ligne: lignes) {
            Boolean data = (Boolean) ligne.getValeur().getTyped();
            int nb = map.get(data);
            map.put(data, nb+1);
        }
        return map;
    }

    public static String nbyes(Set<Ligne> lignes) {
        Map<Boolean, Integer> map = countBool(lignes);
        return ""+map.get(true);
    }

    public static String nbno(Set<Ligne> lignes) {
        Map<Boolean, Integer> map = countBool(lignes);
        return ""+map.get(false);
    }







    /* from http://www.java2s.com/example/java/java.math/calculates-the-square-root-of-the-bigdecimal.html */


    private static final BigDecimal TWO = BigDecimal.valueOf(2);

    /**
     * Calculates the square root of the number.
     *
     * @param number the input number.
     * @return the square root of the input number.
     */
    private static BigDecimal sqrt(BigDecimal number) {
        int digits; // final precision
        BigDecimal numberToBeSquareRooted;
        BigDecimal iteration1;
        BigDecimal iteration2;
        BigDecimal temp1 = null;
        BigDecimal temp2 = null; // temp values

        int extraPrecision = number.precision();
        MathContext mc = new MathContext(extraPrecision,
                RoundingMode.HALF_UP);
        numberToBeSquareRooted = number; // bd global variable
        double num = numberToBeSquareRooted.doubleValue(); // bd to double

        if (mc.getPrecision() == 0)
            throw new IllegalArgumentException(
                    "\nRoots need a MathContext precision > 0");
        if (num < 0.)
            throw new ArithmeticException(
                    "\nCannot calculate the square root of a negative number");
        if (num == 0.)
            return number.round(mc); // return sqrt(0) immediately

        if (mc.getPrecision() < 50) // small precision is buggy..
            extraPrecision += 10; // ..make more precise
        int startPrecision = 1; // default first precision

        /* create the initial values for the iteration procedure:
         * x0:  x ~ sqrt(d)
         * v0:  v = 1/(2*x)
         */
        if (num == Double.POSITIVE_INFINITY) // d > 1.7E308
        {
            BigInteger bi = numberToBeSquareRooted.unscaledValue();
            int biLen = bi.bitLength();
            int biSqrtLen = biLen / 2; // floors it too

            bi = bi.shiftRight(biSqrtLen); // bad guess sqrt(d)
            iteration1 = new BigDecimal(bi); // x ~ sqrt(d)

            MathContext mm = new MathContext(5, RoundingMode.HALF_DOWN); // minimal precision
            extraPrecision += 10; // make up for it later

            iteration2 = BigDecimal.ONE.divide(
                    TWO.multiply(iteration1, mm), mm); // v = 1/(2*x)
        } else // d < 1.7E10^308  (the usual numbers)
        {
            double s = Math.sqrt(num);
            iteration1 = new BigDecimal(s); // x = sqrt(d)
            iteration2 = new BigDecimal(1. / 2. / s); // v = 1/2/x
            // works because Double.MIN_VALUE * Double.MAX_VALUE ~ 9E-16, so: v > 0

            startPrecision = 64;
        }

        digits = mc.getPrecision() + extraPrecision; // global limit for procedure

        // create initial MathContext(precision, RoundingMode)
        MathContext n = new MathContext(startPrecision,
                mc.getRoundingMode());

        return sqrtProcedure(n, digits, numberToBeSquareRooted, iteration1,
                iteration2, temp1, temp2); // return square root using argument precision
    }

    /**
     * Square root by coupled Newton iteration, sqrtProcedure() is the iteration part I adopted the Algorithm from the
     * book "Pi-unleashed", so now it looks more natural I give sparse math comments from the book, it assumes argument
     * mc precision >= 1
     *
     * @param mc
     * @param digits
     * @param numberToBeSquareRooted
     * @param iteration1
     * @param iteration2
     * @param temp1
     * @param temp2
     * @return
     */
    @SuppressWarnings({ "JavaDoc" })
    private static BigDecimal sqrtProcedure(MathContext mc, int digits,
                                            BigDecimal numberToBeSquareRooted, BigDecimal iteration1,
                                            BigDecimal iteration2, BigDecimal temp1, BigDecimal temp2) {
        // next v                                         // g = 1 - 2*x*v
        temp1 = BigDecimal.ONE.subtract(TWO.multiply(iteration1, mc)
                .multiply(iteration2, mc), mc);
        iteration2 = iteration2.add(temp1.multiply(iteration2, mc), mc); // v += g*v        ~ 1/2/sqrt(d)

        // next x
        temp2 = numberToBeSquareRooted.subtract(
                iteration1.multiply(iteration1, mc), mc); // e = d - x^2
        iteration1 = iteration1.add(temp2.multiply(iteration2, mc), mc); // x += e*v        ~ sqrt(d)

        // increase precision
        int m = mc.getPrecision();
        if (m < 2)
            m++;
        else
            m = m * 2 - 1; // next Newton iteration supplies so many exact digits

        if (m < 2 * digits) // digits limit not yet reached?
        {
            mc = new MathContext(m, mc.getRoundingMode()); // apply new precision
            sqrtProcedure(mc, digits, numberToBeSquareRooted, iteration1,
                    iteration2, temp1, temp2); // next iteration
        }

        return iteration1; // returns the iterated square roots
    }
}
