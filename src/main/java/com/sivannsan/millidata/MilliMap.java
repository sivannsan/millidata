package com.sivannsan.millidata;

import com.sivannsan.foundation.annotation.Nonnull;
import com.sivannsan.foundation.Validate;

import java.util.*;

/**
 * MilliMap
 */
public final class MilliMap extends MilliData {
    @Nonnull
    private final Map<String, MilliData> map = new TreeMap<>();

    public MilliMap() {
    }

    public MilliMap(@Nonnull String key, @Nonnull MilliData value) {
        put(Validate.nonnull(key), Validate.nonnull(value));
    }

    public MilliMap(Map<?, ?> map) {
        if (map != null) this.map.putAll(Converter.convert(map).asMilliMap().asMap());
    }

    @Nonnull
    public MilliMap append(@Nonnull String key, @Nonnull MilliData value) {
        put(key, value);
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj instanceof MilliMap && map.equals(((MilliMap) obj).map);
    }

    //Should be moved to MilliData?
    public boolean superOf(@Nonnull MilliMap subMilliMap, int level) {
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

    public boolean superOf(@Nonnull MilliMap subMilliMap) {
        return superOf(subMilliMap, 0);
    }

    public boolean contains(@Nonnull String key) {
        return map.containsKey(Validate.nonnull(key));
    }

    public boolean contains(@Nonnull MilliData value) {
        return map.containsValue(Validate.nonnull(value));
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
        map.remove(Validate.nonnull(key));
    }

    @Nonnull
    public MilliData get(@Nonnull String key) {
        MilliData data = map.get(Validate.nonnull(key));
        return data == null ? MilliNull.INSTANCE : data;
    }

    public int size() {
        return map.size();
    }

    /**
     * @return a view only entry list
     */
    @Nonnull
    public List<Entry> entries() {
        Entry[] entries = new Entry[map.size()];
        String[] keys = map.keySet().toArray(new String[0]);
        for (int i = 0; i < map.size(); i++) entries[i] = new Entry(keys[i], map.get(keys[i]));
        return Arrays.asList(entries);
    }

    /**
     * @return a view only key list
     */
    @Nonnull
    public List<String> keys() {
        return Arrays.asList(map.keySet().toArray(new String[0]));
    }

    /**
     * @return a view only value list
     */
    @Nonnull
    public List<MilliData> values() {
        return Arrays.asList(map.values().toArray(new MilliData[0]));
    }

    @Override
    @Nonnull
    protected String toString(int indent, int previous) {
        Set<String> s = new TreeSet<>();
        String ss = indent > 0 ? " " : ""; //Space after :
        String tt = indent > 0 ? " ".repeat(indent + previous) : ""; //Space of the body
        String t = indent > 0 ? " ".repeat(previous) : ""; //Space before closing }
        String nn = indent > 0 ? "\n" : ""; //New line or not
        for (Entry entry : entries())
            s.add(new MilliValue(entry.getKey()) + ":" + ss + entry.getValue().toString(indent, previous + indent));
        return "{" + nn + tt + String.join("," + nn + tt, s) + nn + t + "}";
    }

    @Nonnull
    public Map<String, MilliData> asMap() {
        return map;
    }

    public static final class Entry {
        @Nonnull
        private final String key;
        @Nonnull
        private final MilliData value;

        private Entry(@Nonnull String key, @Nonnull MilliData value) {
            this.key = key;
            this.value = value;
        }

        public boolean equals(Object object) {
            return this == object || object instanceof Entry && key.equals(((Entry) object).key) && value.equals(((Entry) object).value);
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
