package com.oltpbenchmark.benchmarks.tpcc.gen.operations;

import com.oltpbenchmark.catalog.Table;
import com.oltpbenchmark.scheduler.enums.OperationType;
import com.oltpbenchmark.scheduler.pojo.AccessObject;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.Map;

/**
 * Operation Template
 * members:
 *  1. operation type
 *  2. tables that the operation would access
 *  3. data objects that the operation would access
 */

@Data
public abstract class OperationTemplate {

  private OperationType operationType;
  private OperationID operationID;
  private List<Table> fromTables;
//  private List<AccessObject> accessObjects;
  private Map<String, Object> parameters;

  public OperationTemplate(OperationType operationType, OperationID operationID) {
    this.setOperationType(operationType);
    this.setOperationID(operationID);
  }

  public abstract List<AccessObject> getAccessObject();

  public abstract String toSQL();
}
