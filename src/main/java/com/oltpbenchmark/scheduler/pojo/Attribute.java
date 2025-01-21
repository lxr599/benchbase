package com.oltpbenchmark.scheduler.pojo;

import com.oltpbenchmark.benchmarks.tpcc.gen.data.DataType;
import com.oltpbenchmark.catalog.Table;
import lombok.Data;

/**
 * @program: benchbase
 * @description:
 * @author: Ling Xiangrong
 * @create: 2025-01-10 15:03
 */
@Data
public class Attribute {
  private final int attrId;
  private final String attrName;
  private final DataType attrType;
  private final Table table;

  public Attribute(int attributeID, Table table, String attributeName, DataType attrType) {
    this.attrId = attributeID;
    this.table = table;
    this.attrName = attributeName;
    this.attrType = attrType;
  }

  @Override
  public String toString() {
    return table.getName() + "." + attrName + "(" + attrType.toString() + ")";
  }
}
