package com.sivannsan.millidata;

import java.util.*;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public final class MilliMap extends MilliData {
    private final Map<String, MilliData> map = new TreeMap<>();

    public MilliMap() {
    }

    public MilliMap(String key, MilliData value) {
        put(key, value);
    }

    public MilliMap(String key1, MilliData value1, String key2, MilliData value2) {
        put(key1, value1);
        put(key2, value2);
    }

    public MilliMap(String key1, MilliData value1, String key2, MilliData value2, String key3, MilliData value3) {
        put(key1, value1);
        put(key2, value2);
        put(key3, value3);
    }

    public MilliMap(Map<?, ?> map) {
        if (map != null) this.map.putAll(Converter.convert(map).asMilliMap().asMap());
    }

    public MilliMap append(String key, MilliData value) {
        put(key, value);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MilliMap milliMap = (MilliMap) o;
        return Objects.equals(map, milliMap.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map);
    }

    /**
     * @param level negative value for infinite deep
     */
    public boolean superOf(MilliMap subMilliMap, int level) {
        for (Entry subEntry : subMilliMap.entries()) {
            boolean found = false;
            if (level == 0) {
                for (Map.Entry<String, MilliData> currentEntry : map.entrySet()) {
                    if (!(currentEntry.getKey().equals(subEntry.getKey()) && currentEntry.getValue().equals(subEntry.getValue()))) continue;
                    found = true;
                    break;
                }
            } else {
                for (Map.Entry<String, MilliData> currentEntry : map.entrySet()) {
                    if (!(currentEntry.getKey().equals(subEntry.getKey()) && currentEntry.getValue().superOf(subEntry.getValue(), level - 1))) continue;
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        return true;
    }

    public boolean superOf(MilliMap subMilliMap) {
        return superOf(subMilliMap, 0);
    }

    public boolean contains(String key) {
        return map.containsKey(key);
    }

    public boolean contains(MilliData value) {
        return map.containsValue(value);
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public void clear() {
        map.clear();
    }

    public void put(String key, MilliData value) {
        map.put(Objects.requireNonNull(key), Objects.requireNonNull(value));
    }

    public void remove(String key) {
        map.remove(key);
    }

    public MilliData get(String key) {
        return Objects.requireNonNullElse(map.get(key), MilliNull.INSTANCE);
    }

    public int size() {
        return map.size();
    }

    /**
     * @return a view only entry list
     */
    public List<Entry> entries() {
        Entry[] entries = new Entry[map.size()];
        String[] keys = map.keySet().toArray(new String[0]);
        for (int i = 0; i < map.size(); i++) entries[i] = new Entry(keys[i], map.get(keys[i]));
        return Arrays.asList(entries);
    }

    /**
     * @return a view only key list
     */
    public List<String> keys() {
        return Arrays.asList(map.keySet().toArray(new String[0]));
    }

    /**
     * @return a view only value list
     */
    public List<MilliData> values() {
        return Arrays.asList(map.values().toArray(new MilliData[0]));
    }

    @Override
    protected String toString(int indent, int previous) {
        Set<String> s = new TreeSet<>();
        String ss = indent > 0 ? " " : ""; //Space after colon
        String tt = indent > 0 ? " ".repeat(indent + previous) : ""; //Space of the body
        String t = indent > 0 ? " ".repeat(previous) : ""; //Space before closing curly bracket
        String nn = indent > 0 ? "\n" : ""; //New line or not
        for (Entry entry : entries())
            s.add(new MilliValue(entry.getKey()) + ":" + ss + entry.getValue().toString(indent, previous + indent));
        return "{" + nn + tt + String.join("," + nn + tt, s) + nn + t + "}";
    }

    public Map<String, MilliData> asMap() {
        return map;
    }

    public static final class Entry {
        private final String key;
        private final MilliData value;

        private Entry(String key, MilliData value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry entry = (Entry) o;
            return Objects.equals(key, entry.key) && Objects.equals(value, entry.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }

        public String getKey() {
            return key;
        }

        public MilliData getValue() {
            return value;
        }
    }
}
