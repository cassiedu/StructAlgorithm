package map.treeMap;

import java.util.*;

public class adjacentWords {

    public static void main(String[] args) {
        List<String> words = new LinkedList<>();
        String[] s = {"abc", "abd", "aec", "aef", "abcd", "abce"};
        for (String item: s){
            words.add(item);
        }
        Map<String, List<String>> result = computeAdjacentWords(words);
        Set keys = result.keySet();
        if (keys != null){
            Iterator iterator = keys.iterator();
            while (iterator.hasNext()){
                Object key = iterator.next();
                Object value = result.get(key);
                System.out.println(key + " " + value);
            }
        }
    }

    private static void update(Map<Integer, List<String>> m, int key, String value){
        List<String> lst = m.get(key);
        if (lst == null){
            lst = new ArrayList<>();
            m.put(key, lst);
        }
        lst.add(value);
        for (String item: lst)
            System.out.print(item + " ");
        System.out.println();
    }

    private static void update(Map<String, List<String>> m, String key, String value){
        List<String> lst = m.get(key);
        if (lst == null){
            lst = new ArrayList<>();
            m.put(key, lst);
        }
        lst.add(value);
    }

    public static Map<String, List<String>> computeAdjacentWords(List<String> words){
        Map<String, List<String>> adjWords = new TreeMap<>();
        Map<Integer, List<String>> wordsByLength = new TreeMap<>();
        //先依据字符串长度分组
        for (String w: words){
            update(wordsByLength, w.length(), w);
        }
        //work on each group
        for (Map.Entry<Integer, List<String>> entry: wordsByLength.entrySet()){
            List<String> groupsWords = entry.getValue();
            int groupNum = entry.getKey();
            //work on each character in a group
            for (int i = 0; i < groupNum; i++){
                Map<String, List<String>> repToWord = new TreeMap<>();
                for (String str: groupsWords){
                    String rep = str.substring(0, i) + str.substring(i+1);
                    update(repToWord, rep, str);
                }
                for (List<String> wordClique: repToWord.values())
                    if (wordClique.size() >= 2)
                        for (String s1: wordClique)
                            for (String s2: wordClique)
                                if (s1 != s2)
                                    update(adjWords, s1, s2);
            }
        }
        return adjWords;
    }
}
