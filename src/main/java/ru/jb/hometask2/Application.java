package ru.jb.hometask2;


import java.util.*;
import java.util.stream.Collectors;


public class Application {
    public static void main(String[] args) {
        task1_top3IntegerValue(new ArrayList<>(List.of(9, 9, 1, 3, 6, 2)));
        splitSystemOut();
        task2_top3IntegerUniqueValue(new ArrayList<>(List.of(9, 9, 1, 3, 6, 2)));
        splitSystemOut();
        var list = new ArrayList<Employee>();
        list.add(new Employee("John", "Инженер", 88));
        list.add(new Employee("George", "Инжирнер", 13));
        list.add(new Employee("Barack ", "Инженегр", 13));
        list.add(new Employee("Jim Beam", "Джин", 13));
        list.add(new Employee("John Junior", "Инженер", 14));
        list.add(new Employee("John Middle", "Инженер", 42));
        task3_threeOldestEngineers(list);
        splitSystemOut();
        task4_threeOldestEngineers(list);
        splitSystemOut();
        task5_longestWordInList(new ArrayList<>(List.of("Жираф", "Зебра", "Слон (наш)", "Зелибоба")));
        splitSystemOut();
        task6_buildMapWithWordsCount("тест тест1 тест тест тест2 тест");
    }

    private static void splitSystemOut() {
        System.out.println("=======================================");
    }

    private static void task1_top3IntegerValue(List<Integer> listInt) {
        if (listInt == null || listInt.isEmpty()) {
            System.out.println("Task 1 | Given array is empty");
            return;
        }
        if (listInt.size() - 3 < 0) {
            System.out.println("Task 1 | Given array is less than 3 elements");
            return;
        }
        Collections.sort(listInt);
        System.out.println("Task 1 | Third element of sorted array: " + listInt.get(listInt.size() - 3));
    }

    private static void task2_top3IntegerUniqueValue(List<Integer> listInt) {
        if (listInt == null || listInt.isEmpty()) {
            System.out.println("Task 2 | Given array is empty");
            return;
        }
        if (listInt.size() < 3) {
            System.out.println("Task 2 | Given array is less than 3 elements");
            return;
        }
        Set<Integer> uniqueSet = new HashSet<>(listInt);
        if (uniqueSet.size() - 3 < 0) {
            System.out.println("Task 2 | Given array of unique elements less than 3 elements");
            return;
        }
        List<Integer> uniqueList = uniqueSet.stream()
                                            .sorted()
                                            .collect(Collectors.toList());
        System.out.println("Task 2 | Third element of sorted array: " + uniqueList.get(uniqueList.size() - 3));
    }

    private static void task3_threeOldestEngineers(List<Employee> employees) {
        var sortedEmployees = employees.stream()
                                       .filter(e -> "Инженер".equals(e.getTitle()))
                                       .sorted(Comparator.comparing(Employee::getAge))
                                       .toList();
        if (sortedEmployees.size() < 3) {
            System.out.println("Task 3 | No 3 engineers in list");
            return;
        }
        System.out.println("Task 3 | Sorted oldest engineers:");
        System.out.println(sortedEmployees.get(sortedEmployees.size() - 1).getName());
        System.out.println(sortedEmployees.get(sortedEmployees.size() - 2).getName());
        System.out.println(sortedEmployees.get(sortedEmployees.size() - 3).getName());
    }

    private static void task4_threeOldestEngineers(List<Employee> employees) {
        var sortedEmployees = employees.stream()
                                       .filter(e -> "Инженер".equals(e.getTitle()))
                                       .mapToInt(Employee::getAge)
                                       .average();
        if (sortedEmployees.isEmpty()) {
            System.out.println("Task 4 | No engineers found");
            return;
        }
        System.out.println("Task 4 | Average engineers age: " + sortedEmployees.getAsDouble());

    }

    private static void task5_longestWordInList(List<String> words) {
        if (words == null || words.isEmpty()) {
            System.out.println("Task 5 | Words list is empty");
            return;
        }
        var sortedWords = words.stream()
                               .sorted(Comparator.comparingInt(String::length))
                               .toList();

        System.out.println("Task 5 | Longest word is \"" + sortedWords.get(sortedWords.size() - 1) + "\"");
    }

    private static void task6_buildMapWithWordsCount(String words) {
        if (words == null || words.isEmpty()) {
            System.out.println("Task 6 | Words list is empty");
            return;
        }
        var map = new HashMap<String, Integer>();
        var array = words.split(" ");
        for (String word: array) {
            if (!map.containsKey(word)) {
                map.put(word, 1);
                continue;
            }
            var value = map.get(word);
            map.put(word, ++value);
        }
        System.out.println("Task 6 | " + map);
    }

    static class Employee {
        private final String name;
        private final String title;
        private final Integer age;

        public Employee(String name, String title, Integer age) {
            this.name = name;
            this.title = title;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public String getTitle() {
            return title;
        }

        public Integer getAge() {
            return age;
        }
    }
}
