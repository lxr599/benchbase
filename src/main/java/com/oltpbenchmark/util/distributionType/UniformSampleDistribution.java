package com.oltpbenchmark.util.distributionType;

import java.util.Random;

/**
 * @program: benchbase
 * @description:
 * @author: Ling Xiangrong
 * @create: 2025-01-07 14:28
 */
public class UniformSampleDistribution implements SampleDistribution{
  private final int begin;

  private final int end;

  private Random rand;

  public UniformSampleDistribution(int begin, int end) {
    super();
    rand = new Random();
    this.begin = begin;
    this.end = end;
  }

  @Override
  public double getData() {
    return this.getIntegerData() + rand.nextDouble();
  }

  @Override
  public int getIntegerData() {
    return rand.nextInt(this.end - this.begin) + this.begin;
  }
}
