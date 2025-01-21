package com.oltpbenchmark.util.distributionType;

import org.apache.commons.math3.distribution.NormalDistribution;

/**
 * @program: benchbase
 * @description:
 * @author: Ling Xiangrong
 * @create: 2025-01-07 14:39
 */
public class NormalSampleDistribution implements SampleDistribution{

  private final int begin;

  private final int end;

  private final NormalDistribution normalDisGenerator;

  private final double mean;

  private final double stdDeviation;

  public NormalSampleDistribution(int begin, int end) {
    super();

    this.begin = begin;
    this.end = end;
    this.mean = (this.end + this.begin) / 2.0d;
    // 3σ原则
    this.stdDeviation = (this.end - this.begin) / 6.0d;

    normalDisGenerator = new NormalDistribution(mean, stdDeviation);
  }

  @Override
  public double getData() {
    double value;
    do {
      value = normalDisGenerator.sample();
    } while (value < this.begin || value > this.end);
    return value;
  }

  @Override
  public int getIntegerData() {
    return (int)getData();
  }
}
