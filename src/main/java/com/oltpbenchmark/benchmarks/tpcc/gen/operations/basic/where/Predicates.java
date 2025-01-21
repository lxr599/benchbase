package com.oltpbenchmark.benchmarks.tpcc.gen.operations.basic.where;

import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;
import com.oltpbenchmark.benchmarks.tpcc.gen.data.DataType;
import com.oltpbenchmark.scheduler.enums.PredicateOperator;
import com.oltpbenchmark.scheduler.pojo.Attribute;

import lombok.Data;

/**
 * @program: benchbase
 * @description:
 * @author: Ling Xiangrong
 * @create: 2024-12-02 16:07
 */
@Data
public class Predicates {
  // TPC-C 中没有 `IN`，这里暂时不考虑 List，且 rightValues 中没有逗号
  private String rightValues;
  private Attribute leftValues;
  private PredicateOperator predicateOperator;

  public Predicates(String rightValues, Attribute leftValues, PredicateOperator predicateOperator) {
    this.rightValues = rightValues;
    this.leftValues = leftValues;
    this.predicateOperator = predicateOperator;
  }

  // 如果范围是一些不相交的集合的并集，那么用 RangeSet 表示
  public <T extends Comparable<T>> RangeSet<T> parseRange() {
    T values = convertValue(leftValues.getAttrType(), rightValues);
    RangeSet<T> rangeSet = TreeRangeSet.create();
    switch (predicateOperator) {
      case EQUAL_TO -> {
        rangeSet.add(Range.closed(values, values));
      }
      case NOT_EQUAL -> {
        rangeSet.add(Range.lessThan(values));
        rangeSet.add(Range.greaterThan(values));
      }
      case GREATER_THAN -> {
        rangeSet.add(Range.greaterThan(values));
      }
      case LESS_THAN -> {
        rangeSet.add(Range.lessThan(values));
      }
      case GREATER_EQUAL -> {
        rangeSet.add(Range.atLeast(values));
      }
      case LESS_EQUAL -> {
        rangeSet.add(Range.atMost(values));
      }
      default ->
        throw new IllegalArgumentException("Unknown operator: " + predicateOperator);
    }
    return rangeSet;
  }

  @SuppressWarnings("unchecked")
  private <T extends Comparable<T>> T convertValue(DataType dataType, String value) {
    switch (dataType) {
      case INTEGER -> {
        return (T) Integer.valueOf(value);
      }
      case VARCHAR -> {
        return (T) value;
      }
      case DOUBLE -> {
        return (T) Double.valueOf(value);
      }
      case DECIMAL -> {
        return (T) Double.valueOf(value);
      }
      case TIMESTAMP -> {
        return (T) Long.valueOf(value);
      }
      case BOOL -> {
        return (T) Boolean.valueOf(value);
      }
      default -> {
        throw new IllegalArgumentException("Unknown data type: " + dataType);
      }
    }
  }
}
