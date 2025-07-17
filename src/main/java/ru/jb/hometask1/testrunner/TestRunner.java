package ru.jb.hometask1.testrunner;

import ru.jb.hometask1.annotations.AfterSuit;
import ru.jb.hometask1.annotations.BeforeSuit;
import ru.jb.hometask1.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class TestRunner {

    public static void runTests(Class c) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Optional<Method> beforeMethod = Optional.empty();
        Optional<Method> afterMethod = Optional.empty();
        List<Method> testMethods = new ArrayList<>();

        for (Method method: c.getMethods()) {
            beforeMethod = checkAndGetBeforeMethod(method, beforeMethod);
            afterMethod = checkAndGetAfterMethod(method, afterMethod);
            if (isTestMethod(method)) {
                testMethods.add(method);
            }
        }
        sortAndRunTestMethods(testMethods, beforeMethod, afterMethod, c);
    }

    private static Optional<Method> checkAndGetBeforeMethod(Method method, Optional<Method> foundBeforeMethod) {
        if (method.getAnnotation(BeforeSuit.class) != null) {
            if (foundBeforeMethod.isPresent()) {
                throw new IllegalArgumentException("More than one BeforeSuit marked methods");
            }
            return Optional.of(method);
        }
        return foundBeforeMethod;
    }

    private static Optional<Method> checkAndGetAfterMethod(Method method, Optional<Method> foundAfterMethod) {
        if (method.getAnnotation(AfterSuit.class) != null) {
            if (foundAfterMethod.isPresent()) {
                throw new IllegalArgumentException("More than one AfterSuit marked methods");
            }
            return Optional.of(method);
        }
        return foundAfterMethod;
    }

    private static boolean isTestMethod(Method method) {
        return method.getAnnotation(Test.class) != null;
    }

    private static void sortAndRunTestMethods(List<Method> methods,
                                              Optional<Method> beforeMethod,
                                              Optional<Method> afterMethod,
                                              Class<?> c) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Object obj = c.getDeclaredConstructor().newInstance();
        if (methods != null && !methods.isEmpty()) {
            if (beforeMethod.isPresent()) {
                beforeMethod.get().invoke(obj);
            }
            methods.stream()
                    .sorted(Comparator.comparingInt(m -> m.getAnnotation(Test.class).priority().getPriorityValue()))
                    .sorted((a, b) -> -1)
                    .forEach(method -> {
                        try {
                            method.invoke(obj);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        } catch (InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    });
            if (afterMethod.isPresent()) {
                afterMethod.get().invoke(obj);
            }
        }
    }
}
