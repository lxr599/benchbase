package com.oltpbenchmark.benchmarks.tpcc.config.schema;

import com.oltpbenchmark.benchmarks.tpcc.gen.data.DataType;
import com.oltpbenchmark.util.xml.Seed;
import com.oltpbenchmark.util.xml.SeedUtils;

import java.util.Map;

/**
 * @program: benchbase
 * @description:
 * @author: Ling Xiangrong
 * @create: 2025-01-09 15:33
 */
public class AttributeConfig {
  Seed groupNumberSeed = new Seed();
  Seed attributeNumber = null;
  Seed attrType;

  Map<String, Object> dataType;

  public Seed getAttrType() {
    if (attrType == null) {
      attrType = SeedUtils.initSeed(dataType, DataType.class);
    }
    return attrType;
  }

  public AttributeConfig(){}
}
