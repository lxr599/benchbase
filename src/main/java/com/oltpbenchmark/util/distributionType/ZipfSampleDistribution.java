package com.oltpbenchmark.util.distributionType;

import org.apache.commons.math3.distribution.ZipfDistribution;

/**
 * @program: benchbase
 * @description:
 * @author: Ling Xiangrong
 * @create: 2025-01-07 14:32
 */
public class ZipfSampleDistribution implements SampleDistribution{

  private final ZipfDistribution zipfGenerator;

  private final int begin;

  private final int end;

  private final double skew;

  public ZipfSampleDistribution(int begin, int end, double skew) {
    super();
    this.begin = begin;
    this.end = end;
    this.skew = skew;
    zipfGenerator = new ZipfDistribution(this.end - this.end, skew);
  }
  @Override
  public double getData() {
    return this.getIntegerData();
  }

  @Override
  public int getIntegerData() {
    return zipfGenerator.sample() + this.begin - 1;
  }
}
