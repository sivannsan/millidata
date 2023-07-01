package com.sivannsan.millidata;

public abstract class AbstractMilliDataParser {
    protected static MilliData parse(String millidata) {
        try {
            return checkedParse(millidata);
        } catch (Exception e) {
            return MilliNull.INSTANCE;
        }
    }

    protected static MilliData checkedParse(String millidata) throws NullPointerException, MilliDataException {
        millidata = millidata.trim();
        if (millidata.equals("null")) return MilliNull.INSTANCE;
        char[] array = millidata.toCharArray();
        if (array.length < 2) throw new MilliDataException("The trimmed millidata length less than 2 for not an empty string");
        if (array[0] == '"' && array[array.length - 1] == '"') {
            char p = '?';
            for (int i = 1; i < array.length - 1; i++) {
                char c = array[i];
                if (c == '"' && p != '\\') throw new MilliDataException("Use double quotes inside a MilliValue without escaping character!");
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
                    list.add(checkedParse(f));
                    e = new StringBuilder();
                    continue;
                }
                e.append(c);
            }
            if (e.length() > 0 || list.size() > 0) {
                String f = e.toString();
                list.add(checkedParse(f));
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
                    if (entry == null) throw new MilliDataException();
                    map.put(checkedParse(entry.key()).asMilliValue().asString(), checkedParse(entry.value()));
                    e = new StringBuilder();
                    continue;
                }
                e.append(c);
            }
            if (e.length() > 0 || map.size() > 0) {
                String f = e.toString();
                Entry entry = entry(f);
                if (entry == null) throw new MilliDataException();
                map.put(checkedParse(entry.key()).asMilliValue().asString(), checkedParse(entry.value()));
            }
            return map;
        }
        throw new MilliDataException("Not found a MilliNull, MilliValue, MilliList, or MilliMap");
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
