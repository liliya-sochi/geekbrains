import java.util.ArrayList;

public class Lesson1 {
    public static void main(String[] args) {

        /** Задание 1 */
        Object[] objects1 = {1,2};
        objects1 = item1(objects1);
        Object[] objects2 = {"Один","Два"};
        objects2 = item1(objects2);
        Object[] objects3 = {true, false};
        objects3 = item1(objects3);
        System.out.println(objects1[0] + " & " + objects1[1]);
        System.out.println(objects2[0] + " & " + objects2[1]);
        System.out.println(objects3[0] + " & " + objects3[1]);

        /** Задание 2 */
        Object[] objects4 = {1,2,3,4,5,6,7,8,9,0};
        Object[] objects5 = {"Один","Два","Три","Четыре","Пять","Шесть","Семь","Восемь","Девять","Ноль"};
        ArrayList<Object> list1 = item2(objects4);
        ArrayList<Object> list2 = item2(objects5);
        System.out.println(list1.toString());
        System.out.println(list2.toString());

        /** Задание 3 */
        Box<Apple> boxA1 = new Box<>();
        boxA1.addFruit(new Apple());
        boxA1.addFruit(new Apple());
        boxA1.addFruit(new Apple());
        Box<Apple> boxA2 = new Box<>();
        boxA2.addFruit(new Apple());
        boxA2.addFruit(new Apple());
        Box<Orange> boxO1 = new Box<>();
        boxO1.addFruit(new Orange());
        boxO1.addFruit(new Orange());
        boxA1.compare(boxA2);
        boxA1.compare(boxO1);
        boxA1.unite(boxA2);
        boxA1.getWeight();
        boxA2.getWeight();
    }

    public static Object[] item1(Object[] objects) {
        Object[] substitution = new Object[2];
        substitution[0] = objects[1];
        substitution[1] = objects[0];
        return substitution;
    }

    public static ArrayList<Object> item2(Object[] objects) {
        ArrayList<Object> substitution = new ArrayList<>(objects.length);
        for (int i = 0; i < objects.length; i++) substitution.add(objects[i]);
        return substitution;
    }
}
