package com.sivannsan.millidata;

import java.util.*;

public final class MilliList extends AbstractMilliList {
    public MilliList() {
        super();
    }

    public MilliList(int size) {
        super(size);
    }

    public MilliList(MilliData... elements) {
        super(elements);
    }

    public MilliList(Collection<?> collection) {
        super(collection);
    }

    public MilliList append(MilliData element) {
        add(element);
        return this;
    }

    /**
     * @param level negative value for infinite deep
     */
    @Override
    public boolean superOf(MilliList subMilliList, int level) {
        return super.superOf(subMilliList, level);
    }

    @Override
    public boolean superOf(MilliList subMilliList) {
        return super.superOf(subMilliList);
    }

    @Override
    public boolean contains(MilliData element) {
        return super.contains(element);
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public void add(MilliData element) {
        super.add(element);
    }

    @Override
    public void update(int index, MilliData element) {
        super.update(index, element);
    }

    @Override
    public void remove(int index) {
        super.remove(index);
    }

    @Override
    public MilliData get(int index) {
        return super.get(index);
    }

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public List<MilliData> asList() {
        return super.asList();
    }
}
