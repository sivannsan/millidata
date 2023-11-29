package com.sivannsan.millidata;

import com.sivannsan.foundation.annotation.Nonnegative;
import com.sivannsan.foundation.annotation.Nonnull;
import com.sivannsan.foundation.common.Validate;

import java.util.*;

public class MilliMap extends MilliData {
    @Nonnull
    private final Map<String, MilliData> map = new LinkedHashMap<>();

    public MilliMap() {
    }

    public MilliMap(@Nonnull String key, @Nonnull MilliData value) {
        put(key, value);
    }

    public MilliMap(@Nonnull String k1, @Nonnull MilliData v1, @Nonnull String k2, @Nonnull MilliData v2) {
        put(k1, v1);
        put(k2, v2);
    }

    public MilliMap(@Nonnull String k1, @Nonnull MilliData v1, @Nonnull String k2, @Nonnull MilliData v2, @Nonnull String k3, @Nonnull MilliData v3) {
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

    @Nonnull
    public MilliMap append(@Nonnull String key, @Nonnull MilliData value) {
        put(key, value);
        return this;
    }

    public boolean superOf(@Nonnull MilliMap subMilliMap, int level) {
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

    public boolean superOf(@Nonnull MilliMap subMilliMap) {
        return superOf(subMilliMap, 0);
    }

    public boolean contains(@Nonnull String key) {
        return map.containsKey(key);
    }

    public boolean contains(@Nonnull MilliData value) {
        return map.containsValue(value);
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public void clear() {
        map.clear();
    }

    public void put(@Nonnull String key, @Nonnull MilliData value) {
        map.put(Validate.nonnull(key), Validate.nonnull(value));
    }

    public void remove(@Nonnull String key) {
        map.remove(key);
    }

    @Nonnull
    public MilliData get(@Nonnull String key) {
        MilliData data = map.get(key);
        return data == null ? MilliNull.INSTANCE : data;
    }

    @Nonnegative
    public int size() {
        return map.size();
    }

    /**
     * @return a view-only list of entries
     */
    @Nonnull
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
    @Nonnull
    public List<String> keys() {
        return Arrays.asList(map.keySet().toArray(new String[0]));
    }

    /**
     * @return a view-only list of values
     */
    @Nonnull
    public List<MilliData> values() {
        return Arrays.asList(map.values().toArray(new MilliData[0]));
    }

    @Override
    protected final String toString(int indent, int previous) {
        Set<String> s = new LinkedHashSet<>();
        String ss = indent > 0 ? " " : ""; //Space after colon
        String tt = indent > 0 ? " ".repeat(indent + previous) : ""; //Space of the body
        String t = indent > 0 ? " ".repeat(previous) : ""; //Space before closing curly bracket
        String nn = indent > 0 ? "\n" : ""; //New line or not
        for (Entry entry : entries())
            s.add(new MilliValue(entry.getKey()) + ":" + ss + entry.getValue().toString(indent, previous + indent));
        return "{" + nn + tt + String.join("," + nn + tt, s) + nn + t + "}";
    }

    @Nonnull
    public Map<String, MilliData> asMap() {
        return map;
    }

    public static class Entry {
        @Nonnull
        private final String key;
        @Nonnull
        private final MilliData value;

        public Entry(@Nonnull String key, @Nonnull MilliData value) {
            this.key = Validate.nonnull(key);
            this.value = Validate.nonnull(value);
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

        @Nonnull
        public String getKey() {
            return key;
        }

        @Nonnull
        public MilliData getValue() {
            return value;
        }
    }
}
