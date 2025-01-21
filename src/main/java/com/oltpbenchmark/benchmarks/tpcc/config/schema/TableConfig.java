package com.oltpbenchmark.benchmarks.tpcc.config.schema;

import com.oltpbenchmark.util.xml.Seed;

/**
 * @program: benchbase
 * @description:
 * @author: Ling Xiangrong
 * @create: 2025-01-07 14:20
 */
public class TableConfig {

  PrimaryKeyConfig primaryKey = null;

  AttributeConfig attribute = null;

  Seed recordNumber = null;

  public TableConfig(){}
}
