package com.oltpbenchmark.benchmarks.tpcc.gen.operations.basic;

import com.oltpbenchmark.benchmarks.tpcc.gen.operations.OperationID;
import com.oltpbenchmark.benchmarks.tpcc.gen.operations.OperationTemplate;
import com.oltpbenchmark.benchmarks.tpcc.gen.operations.basic.where.Predicates;
import com.oltpbenchmark.catalog.Table;
import com.oltpbenchmark.scheduler.enums.OperationType;
import com.oltpbenchmark.scheduler.pojo.AccessObject;
import com.oltpbenchmark.scheduler.pojo.Attribute;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: benchbase
 * @description:
 * @author: Ling Xiangrong
 * @create: 2024-12-02 15:55
 */
public class OperationSelect extends OperationTemplate {
  private List<Table> fromTables = null;
  private List<Predicates> whereClause;
  private List<Attribute> attributeList;
  private boolean orderBy = false;
  private boolean limit = false;

  public OperationSelect(OperationID operationID, List<Table> fromTables,
                         List<Attribute> attributes, List<Predicates> predicates) {
    super(OperationType.SELECT, operationID);
    this.fromTables = fromTables;
    this.attributeList = attributes;
    this.whereClause = predicates;
  }

  /**
   * 如何确定一个 AccessObject？（目前只考虑行锁，且 TPC-C 中 fromTables 只有一个 table）
   * table + predicates
   */
  @Override
  public List<AccessObject> getAccessObject() {
    List<AccessObject> accessObjects = new ArrayList<>();

    for (Predicates predicates : whereClause) {

    }
    return accessObjects;
  }

  @Override
  public String toSQL() {
    return String.format("SELECT %s FROM %s WHERE %s");
  }
}
