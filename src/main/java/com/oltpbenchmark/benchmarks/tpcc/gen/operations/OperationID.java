package com.oltpbenchmark.benchmarks.tpcc.gen.operations;

/**
 * @program: benchbase
 * @description:
 * @author: Ling Xiangrong
 * @create: 2024-12-02 19:39
 */
public final class OperationID {
  private final int txnID;
  private final int opID;

  public OperationID(int txnID, int opID) {
    this.txnID = txnID;
    this.opID = opID;
  }
}
