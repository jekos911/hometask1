package ru.jb.hometask1.tests;

import ru.jb.hometask1.annotations.AfterSuit;
import ru.jb.hometask1.annotations.BeforeSuit;
import ru.jb.hometask1.annotations.Test;

public class Tests {
    @BeforeSuit
    public static void beforeEach() {
        System.out.println("before suit annnotated method");
    }

    /**@BeforeSuit
    public static void beforeEach2() {
        System.out.println("before2 suit annnotated method");
    } */

    @AfterSuit
    public static void afterEach() {
        System.out.println("after suit annnotated method");
    }

    /* @AfterSuit
    public static void afterEach2() {
        System.out.println("after2 suit annnotated method");
    } */

    @Test
    public void testWithDefaultPriority() { System.out.println("default priority (5)"); }

    @Test(priority = Test.Priority.TEN)
    public void testWithMaxPriority() { System.out.println("max priority (10)"); }

    @Test(priority = Test.Priority.ONE)
    public void testWithMinPriority() { System.out.println("min priority (1)"); }

}
