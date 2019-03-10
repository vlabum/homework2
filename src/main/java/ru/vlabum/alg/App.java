package ru.vlabum.alg;

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
}
