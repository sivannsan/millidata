package com.sivannsan.millidata;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class MilliList extends MilliData implements Iterable<MilliData> {
    @NotNull
    private final List<MilliData> list = new ArrayList<>();

    public MilliList() {
    }

    public MilliList(int size) {
        for (int i = 0; i < size; i++) {
            add(MilliNull.INSTANCE);
        }
    }

    public MilliList(MilliData... elements) {
        if (elements == null) {
            return;
        }
        for (MilliData element : elements) {
            add(element);
        }
    }

    public MilliList(Collection<?> collection) {
        if (collection == null) {
            return;
        }
        list.addAll(MilliDataConverter.convert(collection).asMilliList().asList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MilliList milliList = (MilliList) o;
        return Objects.equals(list, milliList.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }

    @NotNull
    public MilliList append(@NotNull MilliData element) {
        add(element);
        return this;
    }

    public boolean superOf(@NotNull MilliList subMilliList, int level) {
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

    public boolean superOf(@NotNull MilliList subMilliList) {
        return superOf(subMilliList, 0);
    }

    public boolean contains(@NotNull MilliData element) {
        return list.contains(element);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void clear() {
        list.clear();
    }

    public void add(@NotNull MilliData element) {
        list.add(element);
    }

    public void update(int index, @NotNull MilliData element) {
        if (index < 0 || index >= list.size()) return;
        list.set(index, element);
    }

    public void remove(int index) {
        if (index < 0 || index >= list.size()) return;
        list.remove(index);
    }

    @NotNull
    public MilliData get(int index) {
        if (index < 0 || index >= list.size()) return MilliNull.INSTANCE;
        MilliData data = list.get(index);
        return data == null ? MilliNull.INSTANCE : data;
    }

    public int size() {
        return list.size();
    }

    @NotNull
    @Override
    protected String toString(int indent, int previous) {
        List<String> l = new ArrayList<>();
        for (MilliData element : list) l.add(element.toString(indent, previous + indent));
        String tt = indent > 0 ? repeat(" ", indent + previous) : "";
        String t = indent > 0 ? repeat(" ", previous) : "";
        String nn = indent > 0 ? "\n" : "";
        return "[" + nn + tt + String.join("," + nn + tt, l) + nn + t + "]";
    }

    @NotNull
    public List<MilliData> asList() {
        return list;
    }

    @Override
    public Iterator<MilliData> iterator() {
        return list.iterator();
    }
}
