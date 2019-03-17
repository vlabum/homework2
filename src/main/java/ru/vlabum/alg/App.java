package ru.vlabum.alg;

import java.util.Comparator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
    }

    public static String reverseString(String string) {
        int len = string.length();
        MyStack<Character> stack = new MyStack<>();
        for (int i = 0; i < len; i++) {
            stack.push(string.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    MyStack<Integer> from = new MyStack<>();
    MyStack<Integer> buf = new MyStack<>();
    MyStack<Integer> to = new MyStack<>();

    /**
     * Схема быстрого возведения в степень "справа на лево"
     *
     * @param base
     * @param power
     * @return
     */
    public static long powFast(long base, long power) {
        if (power < 0) {
            return 0;
        }
        if (power < 2) {
            return 1;
        }
        long z = base;
        long rez = 1;
        while (power > 0) {
            if (power % 2 > 0) {
                rez *= z;
            }
            z *= z;
            power = power >> 1;
        }
        return rez;
    }

    /**
     * Рекурсивный метод возведения в степень
     *
     * @param base
     * @param pow
     * @return
     */
    public static long powRec(long base, long pow) {
        if (pow < 0) {
            return 0;
        }
        if (pow == 0) {
            return 1;
        }
        if (pow == 1) {
            return base;
        }
        return powRec(base, pow - 1) * base;
    }

    public static <T> void hanoyR(MyStack<T> from, MyStack<T> buf, MyStack<T> to) {
        int q = from.size();
        hanoyRec(from, buf, to, q);
    }

    /**
     * Решение задачи "Ханойские башни" рекурсивным методом
     *
     * @param from
     * @param buf
     * @param to
     * @param q
     * @param <T>
     */
    private static <T> void hanoyRec(MyStack<T> from, MyStack<T> buf, MyStack<T> to, int q) {
        if (q == 0) {
            return;
        }
        hanoyRec(from, to, buf, q - 1);
        to.push(from.pop());
        hanoyRec(buf, from, to, q - 1);
    }

    /**
     * Обозначим через «1-2» такое действие: переложить диск или с 1-го штыря на 2-й, или со 2-го на 1-й,
     * в зависимости от того, где он меньше. Тогда, чтобы решить головоломку с чётным количеством дисков,
     * надо многократно повторять действия: 1-2, 1-3, 2-3. Если число дисков нечётно — 1-3, 1-2, 2-3.
     *
     * @param from
     * @param buf
     * @param to
     * @param <T>
     */
    public static <T> void hanoyC(MyStack<T> from, MyStack<T> buf, MyStack<T> to, Comparator<T> cmp) {
        int q = from.size();
        if (q == 0) {
            return;
        }
        if (q % 2 == 0) {
            while (to.size() < q) {
                // 1-2
                hanoyChange(from, buf, cmp);
                if (to.size() == q) break;
                // 1-3
                hanoyChange(from, to, cmp);
                if (to.size() == q) break;
                // 2-3
                hanoyChange(buf, to, cmp);
            }
        } else {
            while (to.size() < q) {
                // 1-3
                hanoyChange(from, to, cmp);
                if (to.size() == q) break;
                // 1-2
                hanoyChange(from, buf, cmp);
                if (to.size() == q) break;
                // 2-3
                hanoyChange(buf, to, cmp);
            }
        }
    }

    private static <T> void hanoyChange(MyStack<T> from, MyStack<T> to, Comparator<T> cmp) {
        if (to.size() == 0 && from.size() == 0) {
            return;
        }
        if (to.size() == 0 && from.size() > 0) {
            to.push(from.pop());
        } else if (to.size() > 0 && from.size() == 0) {
            from.push(to.pop());
        } else if (MyStack.less(from.peek(), to.peek(), cmp)) {
            to.push(from.pop());
        } else {
            from.push(to.pop());
        }
    }


}
