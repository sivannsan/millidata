package com.sivannsan.millidata;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class MilliMap extends MilliData {
    @NotNull
    private final Map<String, MilliData> map = new LinkedHashMap<>();

    public MilliMap() {
    }

    public MilliMap(@NotNull String key, @NotNull MilliData value) {
        put(key, value);
    }

    public MilliMap(@NotNull String k1, @NotNull MilliData v1, @NotNull String k2, @NotNull MilliData v2) {
        put(k1, v1);
        put(k2, v2);
    }

    public MilliMap(@NotNull String k1, @NotNull MilliData v1, @NotNull String k2, @NotNull MilliData v2, @NotNull String k3, @NotNull MilliData v3) {
        put(k1, v1);
        put(k2, v2);
        put(k3, v3);
    }

    public MilliMap(Map<?, ?> map) {
        if (map != null) {
            this.map.putAll(MilliDataConverter.convert(map).asMilliMap().asMap());
        }
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

    @NotNull
    public MilliMap append(@NotNull String key, @NotNull MilliData value) {
        put(key, value);
        return this;
    }

    public boolean superOf(@NotNull MilliMap subMilliMap, int level) {
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

    public boolean superOf(@NotNull MilliMap subMilliMap) {
        return superOf(subMilliMap, 0);
    }

    public boolean contains(@NotNull String key) {
        return map.containsKey(key);
    }

    public boolean contains(@NotNull MilliData value) {
        return map.containsValue(value);
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public void clear() {
        map.clear();
    }

    public void put(@NotNull String key, @NotNull MilliData value) {
        map.put(key, value);
    }

    public void remove(@NotNull String key) {
        map.remove(key);
    }

    @NotNull
    public MilliData get(@NotNull String key) {
        MilliData data = map.get(key);
        return data == null ? MilliNull.INSTANCE : data;
    }

    public int size() {
        return map.size();
    }

    /**
     * @return a view-only list of entries
     */
    @NotNull
    public List<MilliMap.Entry> entries() {
        MilliMap.Entry[] entries = new MilliMap.Entry[map.size()];
        String[] keys = map.keySet().toArray(new String[0]);
        for (int i = 0; i < map.size(); i++) {
            entries[i] = new MilliMap.Entry(keys[i], map.get(keys[i]));
        }
        return Arrays.asList(entries);
    }

    /**
     * @return a view-only list of keys
     */
    @NotNull
    public List<String> keys() {
        return Arrays.asList(map.keySet().toArray(new String[0]));
    }

    /**
     * @return a view-only list of values
     */
    @NotNull
    public List<MilliData> values() {
        return Arrays.asList(map.values().toArray(new MilliData[0]));
    }

    @Override
    protected final String toString(int indent, int previous) {
        Set<String> s = new LinkedHashSet<>();
        String ss = indent > 0 ? " " : ""; //Space after colon
        String tt = indent > 0 ? repeat(" ", indent + previous) : ""; //Space of the body
        String t = indent > 0 ? repeat(" ", previous) : ""; //Space before closing curly bracket
        String nn = indent > 0 ? "\n" : ""; //New line or not
        for (Entry entry : entries())
            s.add(new MilliValue(entry.getKey()) + ":" + ss + entry.getValue().toString(indent, previous + indent));
        return "{" + nn + tt + String.join("," + nn + tt, s) + nn + t + "}";
    }

    @NotNull
    public Map<String, MilliData> asMap() {
        return map;
    }

    public static class Entry {
        @NotNull
        private final String key;
        @NotNull
        private final MilliData value;

        public Entry(@NotNull String key, @NotNull MilliData value) {
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

        @NotNull
        public String getKey() {
            return key;
        }

        @NotNull
        public MilliData getValue() {
            return value;
        }
    }
}
