package ru.jb.hometask1;

import ru.jb.hometask1.testrunner.TestRunner;
import ru.jb.hometask1.tests.Tests;

import java.lang.reflect.InvocationTargetException;

public class Application {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        TestRunner.runTests(Tests.class);
    }
}
