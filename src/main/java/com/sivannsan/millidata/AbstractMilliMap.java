package com.sivannsan.millidata;

import com.sivannsan.foundation.annotation.Nonnegative;
import com.sivannsan.foundation.annotation.Nonnull;
import com.sivannsan.foundation.common.Validate;

import java.util.*;

public abstract class AbstractMilliMap extends MilliData {
    @Nonnull
    private final Map<String, MilliData> map = new LinkedHashMap<>();

    protected AbstractMilliMap() {
    }

    protected AbstractMilliMap(@Nonnull String key, @Nonnull MilliData value) {
        put(key, value);
    }

    protected AbstractMilliMap(@Nonnull String k1, @Nonnull MilliData v1, @Nonnull String k2, @Nonnull MilliData v2) {
        put(k1, v1);
        put(k2, v2);
    }

    protected AbstractMilliMap(@Nonnull String k1, @Nonnull MilliData v1, @Nonnull String k2, @Nonnull MilliData v2, @Nonnull String k3, @Nonnull MilliData v3) {
        put(k1, v1);
        put(k2, v2);
        put(k3, v3);
    }

    protected AbstractMilliMap(Map<?, ?> map) {
        if (map != null) {
            this.map.putAll(MilliDataConverter.convert(map).asMilliMap().asMap());
        }
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

    protected boolean superOf(@Nonnull MilliMap subMilliMap, int level) {
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

    protected boolean superOf(@Nonnull MilliMap subMilliMap) {
        return superOf(subMilliMap, 0);
    }

    protected boolean contains(@Nonnull String key) {
        return map.containsKey(key);
    }

    protected boolean contains(@Nonnull MilliData value) {
        return map.containsValue(value);
    }

    protected boolean isEmpty() {
        return map.isEmpty();
    }

    protected void clear() {
        map.clear();
    }

    protected void put(@Nonnull String key, @Nonnull MilliData value) {
        map.put(Validate.nonnull(key), Validate.nonnull(value));
    }

    protected void remove(@Nonnull String key) {
        map.remove(key);
    }

    @Nonnull
    protected MilliData get(@Nonnull String key) {
        MilliData data = map.get(key);
        return data == null ? MilliNull.INSTANCE : data;
    }

    @Nonnegative
    protected int size() {
        return map.size();
    }

    @Nonnull
    protected List<MilliMap.Entry> entries() {
        MilliMap.Entry[] entries = new MilliMap.Entry[map.size()];
        String[] keys = map.keySet().toArray(new String[0]);
        for (int i = 0; i < map.size(); i++) entries[i] = new MilliMap.Entry(keys[i], map.get(keys[i]));
        return Arrays.asList(entries);
    }

    @Nonnull
    protected List<String> keys() {
        return Arrays.asList(map.keySet().toArray(new String[0]));
    }

    @Nonnull
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

    @Nonnull
    protected Map<String, MilliData> asMap() {
        return map;
    }

    protected static abstract class AbstractEntry {
        @Nonnull
        private final String key;
        @Nonnull
        private final MilliData value;

        protected AbstractEntry(@Nonnull String key, @Nonnull MilliData value) {
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

        @Nonnull
        protected String getKey() {
            return key;
        }

        @Nonnull
        protected MilliData getValue() {
            return value;
        }
    }
}
