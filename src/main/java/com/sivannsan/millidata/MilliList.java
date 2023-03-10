package com.sivannsan.millidata;

import com.sivannsan.foundation.annotation.Nonnull;
import com.sivannsan.foundation.Validate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * MilliList
 */
public final class MilliList extends MilliData implements Iterable<MilliData> {
    @Nonnull
    private final List<MilliData> list = new ArrayList<>();

    public MilliList() {
    }

    public MilliList(int size) {
        for (int i = 0; i < size; i++) add(MilliNull.INSTANCE);
    }

    public MilliList(MilliData... elements) {
        if (elements != null) for (MilliData element : elements) add(element);
    }

    public MilliList(Collection<?> list) {
        if (list != null) this.list.addAll(Converter.convert(list).asMilliList().asList());
    }

    @Nonnull
    public MilliList append(@Nonnull MilliData element) {
        add(Validate.nonnull(element));
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj instanceof MilliList && list.equals(((MilliList) obj).list);
    }

    //Should be moved to MilliData?
    public boolean superOf(@Nonnull MilliList subMilliList, int level) {
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

    public boolean superOf(@Nonnull MilliList subMilliList) {
        return superOf(subMilliList, 0);
    }

    public boolean contains(@Nonnull MilliData element) {
        return list.contains(Validate.nonnull(element));
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
    public void add(@Nonnull MilliData element) {
        list.add(Validate.nonnull(element));
    }

    /**
     * Updates at the specified index to the specified element, or in another word, replaces the element at the specified index with the specified element
     */
    public void update(int index, @Nonnull MilliData element) {
        if (index >= 0 || index < size()) list.set(index, Validate.nonnull(element));
    }

    /**
     * Removes the element at the specified index
     */
    public void remove(int index) {
        if (index >= 0 || index < size()) list.remove(index);
    }

    @Nonnull
    public MilliData get(int index) {
        MilliData data = index >= 0 && index < size() ? list.get(index) : null;
        return data == null ? MilliNull.INSTANCE : data;
    }

    public int size() {
        return list.size();
    }

    @Override
    @Nonnull
    protected String toString(int indent, int previous) {
        List<String> l = new ArrayList<>();
        for (MilliData element : list) l.add(element.toString(indent, previous + indent));
        String tt = indent > 0 ? " ".repeat(indent + previous) : "";
        String t = indent > 0 ? " ".repeat(previous) : "";
        String nn = indent > 0 ? "\n" : "";
        return "[" + nn + tt + String.join("," + nn + tt, l) + nn + t + "]";
    }

    @Nonnull
    public List<MilliData> asList() {
        return list;
    }

    @Override
    @Nonnull
    public Iterator<MilliData> iterator() {
        return list.iterator();
    }
}
