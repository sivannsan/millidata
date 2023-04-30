package com.sivannsan.millidata;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * @see com.sivannsan.millidata.MilliNull
 * @see com.sivannsan.millidata.MilliValue
 * @see com.sivannsan.millidata.MilliList
 * @see com.sivannsan.millidata.MilliMap
 */
@SuppressWarnings({"unused", "SameParameterValue"})
public abstract class MilliData {
    public static class Parser {
        private Parser() {
        }

        public static MilliData parse(String millidata, MilliData defaultValue) {
            if (millidata == null) {
                return Objects.requireNonNull(defaultValue);
            }
            try {
                return parse(millidata);
            } catch (MilliDataParseException e) {
                return Objects.requireNonNull(defaultValue);
            }
        }

        public static MilliData parse(String millidata) throws NullPointerException, MilliDataParseException {
            millidata = millidata.trim();
            if (millidata.equals("null")) return MilliNull.INSTANCE;
            char[] array = millidata.toCharArray();
            if (array.length < 2) throw new MilliDataParseException("The trimmed millidata length less than 2 for not an empty string");
            if (array[0] == '"' && array[array.length - 1] == '"') {
                char p = '?';
                for (int i = 1; i < array.length - 1; i++) {
                    char c = array[i];
                    if (c == '"' && p != '\\') throw new MilliDataParseException("Use double quotes inside a MilliValue without escaping character!");
                    p = c == '\\' && p == '\\' ? '?' : c;
                }
                return new MilliValue(millidata.substring(1, array.length - 1).replace("\\\\", "_bsl").replace("\\t", "\t").replace("\\n", "\n").replace("\\\"", "\"").replace("_bsl", "\\"));
            }
            if (array[0] == '[' && array[array.length - 1] == ']') {
                MilliList list = new MilliList();
                StringBuilder e = new StringBuilder();
                for (int i = 1; i < array.length - 1; i++) {
                    char c = array[i];
                    String f = e.toString();
                    if (c == ',' && balance(f)) {
                        list.add(parse(f));
                        e = new StringBuilder();
                        continue;
                    }
                    e.append(c);
                }
                if (e.length() > 0 || list.size() > 0) {
                    String f = e.toString();
                    list.add(parse(f));
                }
                return list;
            }
            if (array[0] == '{' && array[array.length - 1] == '}') {
                MilliMap map = new MilliMap();
                StringBuilder e = new StringBuilder();
                for (int i = 1; i < array.length - 1; i++) {
                    char c = array[i];
                    String f = e.toString();
                    if (c == ',' && balance(f)) {
                        Entry entry = entry(f);
                        if (entry == null) throw new MilliDataParseException();
                        map.put(parse(entry.key()).asMilliValue().asString(), parse(entry.value()));
                        e = new StringBuilder();
                        continue;
                    }
                    e.append(c);
                }
                if (e.length() > 0 || map.size() > 0) {
                    String f = e.toString();
                    Entry entry = entry(f);
                    if (entry == null) throw new MilliDataParseException();
                    map.put(parse(entry.key()).asMilliValue().asString(), parse(entry.value()));
                }
                return map;
            }
            throw new MilliDataParseException("Not found a MilliNull, MilliValue, MilliList, or MilliMap");
        }

        private static boolean balance(String e) {
            boolean str = false;
            int cb = 0;
            int sb = 0;
            char p = '?';
            for (char c : e.toCharArray()) {
                if (c == '{' && !str) cb++;
                if (c == '}' && !str) cb--;
                if (c == '[' && !str) sb++;
                if (c == ']' && !str) sb--;
                if (c == '"' && p != '\\') str = !str;
                p = c == '\\' && p == '\\' ? '?' : c;
            }
            return cb == 0 && sb == 0 && !str;
        }

        private static Entry entry(String e) {
            e = e.trim();
            if (e.length() < 3 || e.charAt(0) != '"') return null;
            int i = 0;
            boolean str = false;
            char p = '?';
            for (char c : e.toCharArray()) {
                if (c == ':' && !str) return new Entry(e.substring(0, i), e.substring(i + 1));
                if (c == '"' && p != '\\') str = !str;
                p = c == '\\' && p == '\\' ? '?' : c;
                i++;
            }
            return null;
        }

        private static class Entry {
            private final String key;
            private final String value;

            public Entry(String key, String value) {
                this.key = key;
                this.value = value;
            }

            public String key() {
                return key;
            }

            public String value() {
                return value;
            }
        }
    }

    protected static class Converter {
        private Converter() {
        }

        /**
         * Convert from java object to millidata
         * Null input will return MilliNull
         */
        protected static MilliData convert(Object o) {
            if (o == null) {
                return MilliNull.INSTANCE;
            } else if (o instanceof MilliData) {
                return (MilliData) o;
            } else if (o instanceof Map<?, ?>) {
                MilliMap m = new MilliMap();
                for (Map.Entry<?, ?> e : ((Map<?, ?>) o).entrySet()) if (e.getValue() != null) m.append(convert(e.getKey()).asMilliValue().asString(), convert(e.getValue()));
                return m;
            } else if (o instanceof Collection<?>) {
                MilliList l = new MilliList();
                for (Object e : (Collection<?>) o) if (e != null) l.append(convert(e));
                return l;
            } else {
                return new MilliValue(o);
            }
        }
    }

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public String toString() {
        return toString(0);
    }

    public String toString(int indent) {
        return toString(indent, 0);
    }

    protected abstract String toString(int indent, int previous);

    /**
     * @param level negative value for infinite deep
     */
    public final boolean superOf(MilliData subMilliData, int level) {
        if (isMilliNull() && subMilliData.isMilliNull()) return true;
        else if (isMilliValue() && subMilliData.isMilliValue()) return asMilliValue().superOf(subMilliData.asMilliValue());
        else if (isMilliList() && subMilliData.isMilliList()) return asMilliList().superOf(subMilliData.asMilliList(), level);
        else if (isMilliMap() && subMilliData.isMilliMap()) return asMilliMap().superOf(subMilliData.asMilliMap(), level);
        else return false;
    }

    public final boolean superOf(MilliData subMilliData) {
        return superOf(subMilliData, 0);
    }

    public final boolean isMilliNull() {
        return this instanceof MilliNull;
    }

    public final boolean isMilliValue() {
        return this instanceof MilliValue;
    }

    public final boolean isMilliList() {
        return this instanceof MilliList;
    }

    public final boolean isMilliMap() {
        return this instanceof MilliMap;
    }

    /**
     * Cast this MilliData as MilliValue
     */
    public final MilliValue asMilliValue() throws ClassCastException {
        if (isMilliValue()) return (MilliValue) this;
        throw new ClassCastException("Not a MilliValue");
    }

    /**
     * If this object is not a MilliValue, this method will return the defaultValue
     */
    public final MilliValue asMilliValue(MilliValue defaultValue) {
        return isMilliValue() ? asMilliValue() : Objects.requireNonNull(defaultValue);
    }

    /**
     * Cast this MilliData as MilliList
     */
    public final MilliList asMilliList() throws ClassCastException {
        if (isMilliList()) return (MilliList) this;
        throw new ClassCastException("Not a MilliList");
    }

    /**
     * If this object is not a MilliList, this method will return the defaultValue
     * But note that modifying the defaultValue won't affect the original data
     */
    public final MilliList asMilliList(MilliList defaultValue) {
        return isMilliList() ? asMilliList() : Objects.requireNonNull(defaultValue);
    }

    /**
     * Cast this MilliData as MilliMap
     */
    public final MilliMap asMilliMap() throws ClassCastException {
        if (isMilliMap()) return (MilliMap) this;
        throw new ClassCastException("Not a MilliMap");
    }

    /**
     * If this object is not a MilliMap, this method will return the defaultValue
     * But note that modifying the defaultValue won't affect the original data
     */
    public final MilliMap asMilliMap(MilliMap defaultValue) {
        return isMilliMap() ? asMilliMap() : Objects.requireNonNull(defaultValue);
    }

    protected String repeat(String s, int n) {
        Objects.requireNonNull(s);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            builder.append(s);
        }
        return builder.toString();
    }
}
