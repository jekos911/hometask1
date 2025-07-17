package ru.jb.hometask1.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
    Priority priority() default Priority.FIVE;

    public enum Priority {

        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        ;

        private final int priorityValue;

        Priority(int priorityValue) {
            this.priorityValue = priorityValue;
        }

        public int getPriorityValue() {
            return priorityValue;
        }
    }
}
