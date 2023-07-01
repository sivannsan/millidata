package com.sivannsan.millidata;

import java.util.*;

public abstract class AbstractMilliMap extends MilliData {
    private final Map<String, MilliData> map = new LinkedHashMap<>();

    protected AbstractMilliMap() {
    }

    protected AbstractMilliMap(String key, MilliData value) {
        put(key, value);
    }

    protected AbstractMilliMap(String key1, MilliData value1, String key2, MilliData value2) {
        put(key1, value1);
        put(key2, value2);
    }

    protected AbstractMilliMap(String key1, MilliData value1, String key2, MilliData value2, String key3, MilliData value3) {
        put(key1, value1);
        put(key2, value2);
        put(key3, value3);
    }

    protected AbstractMilliMap(Map<?, ?> map) {
        if (map != null) this.map.putAll(MilliDataConverter.convert(map).asMilliMap().asMap());
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractMilliMap milliMap = (AbstractMilliMap) o;
        return Objects.equals(map, milliMap.map);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(map);
    }

    protected boolean superOf(MilliMap subMilliMap, int level) {
        for (MilliMap.Entry subEntry : subMilliMap.entries()) {
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

    protected boolean superOf(MilliMap subMilliMap) {
        return superOf(subMilliMap, 0);
    }

    protected boolean contains(String key) {
        return map.containsKey(key);
    }

    protected boolean contains(MilliData value) {
        return map.containsValue(value);
    }

    protected boolean isEmpty() {
        return map.isEmpty();
    }

    protected void clear() {
        map.clear();
    }

    protected void put(String key, MilliData value) {
        map.put(Objects.requireNonNull(key), Objects.requireNonNull(value));
    }

    protected void remove(String key) {
        map.remove(key);
    }

    protected MilliData get(String key) {
        MilliData data = map.get(key);
        return data == null ? MilliNull.INSTANCE : data;
    }

    protected int size() {
        return map.size();
    }

    protected List<MilliMap.Entry> entries() {
        MilliMap.Entry[] entries = new MilliMap.Entry[map.size()];
        String[] keys = map.keySet().toArray(new String[0]);
        for (int i = 0; i < map.size(); i++) entries[i] = new MilliMap.Entry(keys[i], map.get(keys[i]));
        return Arrays.asList(entries);
    }

    protected List<String> keys() {
        return Arrays.asList(map.keySet().toArray(new String[0]));
    }

    protected List<MilliData> values() {
        return Arrays.asList(map.values().toArray(new MilliData[0]));
    }

    @Override
    protected final String toString(int indent, int previous) {
        Set<String> s = new LinkedHashSet<>();
        String ss = indent > 0 ? " " : ""; //Space after colon
        String tt = indent > 0 ? " ".repeat(indent + previous) : ""; //Space of the body
        String t = indent > 0 ? " ".repeat(previous) : ""; //Space before closing curly bracket
        String nn = indent > 0 ? "\n" : ""; //New line or not
        for (AbstractEntry entry : entries())
            s.add(new MilliValue(entry.getKey()) + ":" + ss + entry.getValue().toString(indent, previous + indent));
        return "{" + nn + tt + String.join("," + nn + tt, s) + nn + t + "}";
    }

    protected Map<String, MilliData> asMap() {
        return map;
    }

    protected static abstract class AbstractEntry {
        private final String key;
        private final MilliData value;

        protected AbstractEntry(String key, MilliData value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AbstractEntry abstractEntry = (AbstractEntry) o;
            return Objects.equals(key, abstractEntry.key) && Objects.equals(value, abstractEntry.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }

        protected String getKey() {
            return key;
        }

        protected MilliData getValue() {
            return value;
        }
    }
}
