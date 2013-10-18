package org.noip.sinc.chapter15.tasks.t4;

/**
 * Created with IntelliJ IDEA.
 * User: alexey
 * Date: 03.07.13
 * Time: 13:21
 */
public class CallScala {
    public static void main(String[] args) {
        CallFromJava fromScala = new CallFromJava();
        System.out.println(fromScala.sum(1, 2, 3, 4));
        System.out.println(fromScala.readFileContent("bear.txt"));
    }
}
