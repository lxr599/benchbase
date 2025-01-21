package com.oltpbenchmark.util;

import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;

public class GenericRangeSet<T extends Comparable<T>> {
  private RangeSet<T> rangeSet;

  public GenericRangeSet() {
    this.rangeSet = TreeRangeSet.create();
  }

  public void addRange(Range<T> range) {
    rangeSet.add(range);
  }

  public void removeRange(Range<T> range) {
    rangeSet.remove(range);
  }

  public boolean contains(T value) {
    return rangeSet.contains(value);
  }
}
