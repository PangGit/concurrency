package com.algorithm.hashmap;

import java.util.*;

/**
 * @author dab
 * @version 1.0.0
 * @Description :
 * @Date 2018/5/24 18:04
 */
public class HashMapTest {

<<<<<<< HEAD
    HashMap<String, Object> hashMap = new HashMap<>();

    LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();

    private HashMap getHashMap() {

=======
    private HashMap<String, Object> hashMap = new HashMap<>();

    public void setMap() {
        hashMap.put("a", "1");
>>>>>>> 4a437b0e4ca2e99d1b67b8a98b2ade7b4e4758c1
        hashMap.put("b", "2");
        hashMap.put("c", "3");
        hashMap.put("d", "4");
        hashMap.put("e", "5");
        hashMap.put("f", "6");
        hashMap.put("g", "7");
        hashMap.put("h", "8");
        hashMap.put("i", "9");
        hashMap.put("j", "10");
        hashMap.put("k", "11");
        hashMap.put("o", "12");
        hashMap.put("p", "13");
        hashMap.put("q", "14");
        hashMap.put("r", "15");
        hashMap.put("s", "16");
        hashMap.put("t", "17");
        hashMap.put("u", "18");
        hashMap.put("v", "19");
        hashMap.put("w", "20");
        hashMap.put("x", "21");
        hashMap.put("y", "22");
        hashMap.put("z", "23");
<<<<<<< HEAD

        hashMap.put("a", "1");

        return this.hashMap;
    }

    static int hash(Object key) {
        int h;
        System.out.println(key + " hashCode:"+key.hashCode());
=======
    }

    int hash(Object key) {
        int h;
>>>>>>> 4a437b0e4ca2e99d1b67b8a98b2ade7b4e4758c1
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public static void main(String[] args) {
        HashMapTest hashMapTest = new HashMapTest();

/*        hashMapTest.linkedHashMap.putAll(hashMapTest.getHashMap());

        Set<String> set = hashMapTest.linkedHashMap.keySet();

        for (String key : set) {
            String value = (String) hashMapTest.linkedHashMap.get(key);
            System.out.println(key + "---" + value);
        }*/

        System.out.println(hash(3));



    }


}
