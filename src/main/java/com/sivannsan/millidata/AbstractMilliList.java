package com.sivannsan.millidata;

import java.util.*;

public abstract class AbstractMilliList extends MilliData implements Iterable<MilliData> {
    private final List<MilliData> list = new ArrayList<>();

    protected AbstractMilliList() {
    }

    protected AbstractMilliList(int size) {
        for (int i = 0; i < size; i++) {
            add(MilliNull.INSTANCE);
        }
    }

    protected AbstractMilliList(MilliData... elements) {
        if (elements == null) {
            return;
        }
        for (MilliData element : elements) {
            add(element);
        }
    }

    protected AbstractMilliList(Collection<?> collection) {
        if (collection == null) {
            return;
        }
        list.addAll(MilliDataConverter.convert(collection).asMilliList().asList());
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractMilliList milliData = (AbstractMilliList) o;
        return Objects.equals(list, milliData.list);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(list);
    }

    protected boolean superOf(MilliList subMilliList, int level) {
        for (MilliData subData : subMilliList) {
            if (level == 0) {
                if (!list.contains(subData)) return false;
            } else {
                boolean found = false;
                for (MilliData currentData : list) {
                    if (!(currentData.superOf(subData, level - 1))) continue;
                    found = true;
                    break;
                }
                if (!found) return false;
            }
        }
        /*
         * What about?
         * Current-List: ["abc"]
         * Sub-List: ["a", "ab"]
         *
         * For Map, it doesn't occur
         */
        return true;
    }

    protected boolean superOf(MilliList subMilliList) {
        return superOf(subMilliList, 0);
    }

    protected boolean contains(MilliData element) {
        return list.contains(element);
    }

    protected boolean isEmpty() {
        return list.isEmpty();
    }

    protected void clear() {
        list.clear();
    }

    protected void add(MilliData element) {
        list.add(Objects.requireNonNull(element));
    }

    protected void update(int index, MilliData element) {
        if (index < 0 || index >= list.size()) return;
        list.set(index, Objects.requireNonNull(element));
    }

    protected void remove(int index) {
        if (index < 0 || index >= list.size()) return;
        list.remove(index);
    }

    protected MilliData get(int index) {
        if (index < 0 || index >= list.size()) return MilliNull.INSTANCE;
        MilliData data = list.get(index);
        return data == null ? MilliNull.INSTANCE : data;
    }

    protected int size() {
        return list.size();
    }

    @Override
    protected final String toString(int indent, int previous) {
        List<String> l = new ArrayList<>();
        for (MilliData element : list) l.add(element.toString(indent, previous + indent));
        String tt = indent > 0 ? " ".repeat(indent + previous) : "";
        String t = indent > 0 ? " ".repeat(previous) : "";
        String nn = indent > 0 ? "\n" : "";
        return "[" + nn + tt + String.join("," + nn + tt, l) + nn + t + "]";
    }

    protected List<MilliData> asList() {
        return list;
    }

    @Override
    public final Iterator<MilliData> iterator() {
        return list.iterator();
    }
}
