package com.oop.proj;

import java.util.Comparator;

/**
 * Created by aum on 12/15/16.
 */
public class UnitComparator implements Comparator<Unit> {
    @Override
    public int compare(Unit o1, Unit o2) {
        return (int)(o1.getPosition().x - o2.getPosition().x);
    }
}
