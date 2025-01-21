package com.oltpbenchmark.benchmarks.tpcc.config.schema;

import com.oltpbenchmark.benchmarks.tpcc.gen.data.DataType;
import com.oltpbenchmark.util.xml.Seed;
import com.oltpbenchmark.util.xml.SeedUtils;
import lombok.Data;

import java.util.Map;

/**
 * @program: benchbase
 * @description:
 * @author: Ling Xiangrong
 * @create: 2025-01-09 13:05
 */
@Data
public class PrimaryKeyConfig {
  Seed attributeNumber = null;
  Seed pkTypeSeed = null;
  Seed increaseSeed = null;

  Map<String ,Object> dataType;

  public Seed getPkTypeSeed() {
    if (pkTypeSeed == null) {
      pkTypeSeed = SeedUtils.initSeed(dataType, DataType.class);
    }
    return pkTypeSeed;
  }

  public Seed getIncreaseSeed() {
    if (increaseSeed == null) {
      increaseSeed = SeedUtils.initSeed(0,1);
    }
    return increaseSeed;
  }

  public PrimaryKeyConfig() {}
}
