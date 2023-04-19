package com.sivannsan.millidata;

import java.util.*;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public final class MilliList extends MilliData implements Iterable<MilliData> {
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
        list.addAll(Converter.convert(collection).asMilliList().asList());
    }

    public MilliList append(MilliData element) {
        add(element);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MilliList milliData = (MilliList) o;
        return Objects.equals(list, milliData.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }

    /**
     * @param level negative value for infinite deep
     */
    public boolean superOf(MilliList subMilliList, int level) {
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

    public boolean superOf(MilliList subMilliList) {
        return superOf(subMilliList, 0);
    }

    public boolean contains(MilliData element) {
        return list.contains(element);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Removes all elements from
     */
    public void clear() {
        list.clear();
    }

    /**
     * Adds the specified element to the end
     */
    public void add(MilliData element) {
        list.add(Objects.requireNonNull(element));
    }

    /**
     * Updates at the specified index to the specified element, or in another word, replaces the element at the specified index with the specified element
     */
    public void update(int index, MilliData element) {
        if (index < 0 || index >= list.size()) return;
        list.set(index, Objects.requireNonNull(element));
    }

    /**
     * Removes the element at the specified index
     */
    public void remove(int index) {
        if (index < 0 || index >= list.size()) return;
        list.remove(index);
    }

    public MilliData get(int index) {
        if (index < 0 || index >= list.size()) return MilliNull.INSTANCE;
        return Objects.requireNonNullElse(list.get(index), MilliNull.INSTANCE);
    }

    public int size() {
        return list.size();
    }

    @Override
    protected String toString(int indent, int previous) {
        List<String> l = new ArrayList<>();
        for (MilliData element : list) l.add(element.toString(indent, previous + indent));
        String tt = indent > 0 ? " ".repeat(indent + previous) : "";
        String t = indent > 0 ? " ".repeat(previous) : "";
        String nn = indent > 0 ? "\n" : "";
        return "[" + nn + tt + String.join("," + nn + tt, l) + nn + t + "]";
    }

    public List<MilliData> asList() {
        return list;
    }

    @Override
    public Iterator<MilliData> iterator() {
        return list.iterator();
    }
}
