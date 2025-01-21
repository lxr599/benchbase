package com.oltpbenchmark.scheduler.pojo;

import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.oltpbenchmark.util.GenericRangeSet;

import lombok.Data;

/**
 * 1. 如果两个 SQL 的谓词条件有交集，即，访问的属性一样或者有重叠，那么他们的冲突概率比较方便计算
 * 2. 如果没有交集，此时需要给数据库发送统计信息的 SQL，根据结果估算冲突概率，并记录信息
 * 3. 如果是涉及到修改的 SQL，那么每次修改之后需要更新之前记录的信息
 */
@Data
public class AccessObject {
  private Attribute attribute;
  private DataDistribution dataDistribution;
  private GenericRangeSet<Integer> dataRange;

  public AccessObject(Attribute attribute ,DataDistribution dataDistribution, RangeSet<Integer> dataRange) {
    this.attribute = attribute;
    this.dataDistribution = dataDistribution;
    // this.dataRange = dataRange;
  }
}
