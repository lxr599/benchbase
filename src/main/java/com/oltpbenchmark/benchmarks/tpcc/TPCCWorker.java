/*
 * Copyright 2020 by OLTPBenchmark Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.oltpbenchmark.benchmarks.tpcc;

import com.oltpbenchmark.api.Procedure.UserAbortException;
import com.oltpbenchmark.api.TransactionType;
import com.oltpbenchmark.api.Worker;
import com.oltpbenchmark.benchmarks.tpcc.procedures.NewOrder;
import com.oltpbenchmark.benchmarks.tpcc.procedures.Payment;
import com.oltpbenchmark.benchmarks.tpcc.procedures.TPCCProcedure;
import com.oltpbenchmark.distributions.ZipfianGenerator;
import com.oltpbenchmark.types.TransactionStatus;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

import com.oltpbenchmark.util.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TPCCWorker extends Worker<TPCCBenchmark> {

  private static final Logger LOG = LoggerFactory.getLogger(TPCCWorker.class);

  private final int terminalWarehouseID;

  /** Forms a range [lower, upper] (inclusive). */
  private final int terminalDistrictLowerID;

  private final int terminalDistrictUpperID;
  private final Random gen = new Random();

  private final int numWarehouses;

  public TPCCWorker(
      TPCCBenchmark benchmarkModule,
      int id,
      int terminalWarehouseID,
      int terminalDistrictLowerID,
      int terminalDistrictUpperID,
      int numWarehouses) {
    super(benchmarkModule, id);

    this.terminalWarehouseID = terminalWarehouseID;
    this.terminalDistrictLowerID = terminalDistrictLowerID;
    this.terminalDistrictUpperID = terminalDistrictUpperID;

    this.numWarehouses = numWarehouses;
  }

  /** Executes a single TPCC transaction of type transactionType. */
  @Override
  protected TransactionStatus executeWork(Connection conn, TransactionType nextTransaction)
      throws UserAbortException, SQLException {
    try {
      TPCCProcedure proc = (TPCCProcedure) this.getProcedure(nextTransaction.getProcedureClass());
//      int wid = TPCCUtil.randomNumber(1, terminalWarehouseID, gen);
//      ZipfianGenerator zipfianGenerator = new ZipfianGenerator(gen, 1, terminalWarehouseID, 0.5);
//      int wid = zipfianGenerator.nextInt();
      int wid = 1;
//      int districtUpperID = terminalDistrictLowerID + 1;
      proc.run(
          conn,
          gen,
//          terminalWarehouseID,
          wid,
          numWarehouses,
          1,
//          terminalDistrictUpperID,
          1,
          this);
    } catch (ClassCastException ex) {
      // fail gracefully
      LOG.error("We have been invoked with an INVALID transactionType?!", ex);
      throw new RuntimeException("Bad transaction type = " + nextTransaction);
    }
    return (TransactionStatus.SUCCESS);
  }

  @Override
  protected long getPreExecutionWaitInMillis(TransactionType type) {
    // TPC-C 5.2.5.2: For keying times for each type of transaction.
    return type.getPreExecutionWait();
  }

  @Override
  protected long getPostExecutionWaitInMillis(TransactionType type) {
    // TPC-C 5.2.5.4: For think times for each type of transaction.
    long mean = type.getPostExecutionWait();

    float c = this.getBenchmark().rng().nextFloat();
    long thinkTime = (long) (-1 * Math.log(c) * mean);
    if (thinkTime > 10 * mean) {
      thinkTime = 10 * mean;
    }

    return thinkTime;
  }
}
