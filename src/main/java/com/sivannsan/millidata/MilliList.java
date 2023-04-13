package com.sivannsan.millidata;

import com.sivannsan.foundation.annotation.Nonnegative;
import com.sivannsan.foundation.annotation.Nonnull;
import com.sivannsan.foundation.common.Check;
import com.sivannsan.foundation.common.Ensure;
import com.sivannsan.foundation.common.Validate;

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

    @SuppressWarnings("unused")
    public MilliList(@Nonnegative int size) {
        for (int i = 0; i < Validate.nonnegative(size); i++) add(MilliNull.INSTANCE);
    }

    @SuppressWarnings("unused")
    public MilliList(MilliData... elements) {
        if (elements != null) for (MilliData element : elements) add(element);
    }

    @SuppressWarnings("unused")
    public MilliList(Collection<?> collection) {
        if (collection != null) list.addAll(Converter.convert(collection).asMilliList().asList());
    }

    @SuppressWarnings("UnusedReturnValue")
    @Nonnull
    public MilliList append(@Nonnull MilliData element) {
        add(element);
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj instanceof MilliList && list.equals(((MilliList) obj).list);
    }

    /**
     * @param level negative value for infinite deep
     */
    public boolean superOf(@Nonnull MilliList subMilliList, int level) {
        for (MilliData subData : Validate.nonnull(subMilliList)) {
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

    @SuppressWarnings("unused")
    public boolean superOf(@Nonnull MilliList subMilliList) {
        return superOf(subMilliList, 0);
    }

    @SuppressWarnings("unused")
    public boolean contains(@Nonnull MilliData element) {
        return list.contains(Validate.nonnull(element));
    }

    @SuppressWarnings("unused")
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Removes all elements from
     */
    @SuppressWarnings("unused")
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
    @SuppressWarnings("unused")
    public void update(int index, @Nonnull MilliData element) {
        if (index >= 0 || index < size()) list.set(index, Validate.nonnull(element));
    }

    /**
     * Removes the element at the specified index
     */
    @SuppressWarnings("unused")
    public void remove(int index) {
        if (index >= 0 || index < size()) list.remove(index);
    }

    @SuppressWarnings("unused")
    @Nonnull
    public MilliData get(int index) {
        MilliData data = Check.withinBounds(list, index) ? list.get(index) : null;
        return Ensure.ifNull(data, MilliNull.INSTANCE);
    }

    public int size() {
        return list.size();
    }

    @Override
    @Nonnull
    protected String toString(@Nonnegative int indent, @Nonnegative int previous) {
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
