import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LessonThree {
    public static void main(String[] args) {
        wordСounter();

        PhoneBook.add("Лилия","8-918-000-00-01");
        PhoneBook.add("Лилия","8-918-000-00-02");
        PhoneBook.add("Михаил","8-918-000-00-03");
        PhoneBook.add("Михаил","8-918-000-00-04");
        PhoneBook.get("Лилия");
        PhoneBook.get("Михаил");
    }

    public static void wordСounter() {
        String[] strings = {"Яблоко","Дом","Море","Машина","Яблоко","Горы","Море","Апельсин","Яблоко","Машина",
                "Киви","Дом","Горы","Яблоко","Работа","Семья","Дом","Машина","Апельсин","Киви"};
        HashSet<String> myHashSet = new HashSet<>();
        HashMap<String,Integer> myHashMap = new HashMap<>();

        for(String item : strings) {
            myHashSet.add(item);
        }

        for(String word : myHashSet) {
            int temp = 0;
            for(String item : strings) {
                if(word.equals(item)) {
                    temp++;
                    myHashMap.put(word,temp);
                }
            }
        }

        for(Map.Entry<String,Integer> word : myHashMap.entrySet()) {
            System.out.println("Слово \"" + word.getKey() + "\" встречается " + word.getValue() + " раз.");
        }
    }
}
