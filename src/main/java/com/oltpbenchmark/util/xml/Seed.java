package com.oltpbenchmark.util.xml;

import com.oltpbenchmark.util.distributionType.NormalSampleDistribution;
import com.oltpbenchmark.util.distributionType.SampleDistribution;
import com.oltpbenchmark.util.distributionType.UniformSampleDistribution;
import com.oltpbenchmark.util.distributionType.ZipfSampleDistribution;
import com.oltpbenchmark.util.rand.RandUtils;
import lombok.Data;
import lombok.Getter;

import java.util.Map;

/**
 * Code from Github by Coconut and others.
 * Code URL: https://github.com/Coconut-DB1024/Orca
 */
@Data
public class Seed {
  public static final String NORMAL_DISTRIBUTION = "NORMAL";

  public static final String UNIFORM_DISTRIBUTION = "UNIFORM";

  public static final String ZIPF_DISTRIBUTION = "ZIPF";

  private Integer begin = 1;

  private Integer end = 2;

  @Getter
  private String distribution = UNIFORM_DISTRIBUTION;

  @Getter
  private Double[] parameter;

  private SampleDistribution sampleDistribution;

  private Map<Integer, Integer> distributionMap = null;

  public Seed() {}

  public boolean isAvailable() {
    return distribution != null || distributionMap != null;
  }

  public Seed(Map<Integer, Integer> disMap) {
    this.distributionMap = disMap;
  }

  public Seed(Integer begin, Integer end, String distributionDesc) {
    super();
    this.begin = begin;
    this.end = end;

    String[] distributionDescribeArray = (distributionDesc.split(","));
    String distribution = distributionDescribeArray[0];
    Double[] parameter = new Double[distributionDescribeArray.length - 1];
    for (int i = 1; i < distributionDescribeArray.length; ++i) {
      parameter[i - 1] = Double.parseDouble(distributionDescribeArray[i]);
    }

    this.distribution = distribution;
    this.parameter = parameter;
    if (this.begin != null && this.end != null) {
      this.updateDistribution();
    }
  }

  /**
   * 获取begin和end之间的一个随机值,采取左闭右开的方法
   *
   */
  public int getRandomValue() {
    if (distributionMap != null) {
      return RandUtils.randSelectByProbability(distributionMap);
    }

    if (this.sampleDistribution == null) {
      updateDistribution();
    }
    int randomValue = this.sampleDistribution.getIntegerData();
    while (randomValue >= this.end || randomValue < this.begin) {
      randomValue = this.sampleDistribution.getIntegerData();
    }

    return randomValue;
  }

  public void setDistribution(String distribution) {
    this.distribution = distribution;
  }

  public void setRange(Integer begin, Integer end) {
    this.begin = begin;
    this.end = end;
    if (begin != null && end != null) {
      this.updateDistribution();
    }
  }

  private void updateDistribution() {
    switch (this.distribution.toUpperCase()) {
      case UNIFORM_DISTRIBUTION -> sampleDistribution = new UniformSampleDistribution(begin, end);
      case NORMAL_DISTRIBUTION -> sampleDistribution = new NormalSampleDistribution(begin, end);
      case ZIPF_DISTRIBUTION -> {
        if (parameter.length == 0) {
          throw new RuntimeException("Parameter missed:exponent is needed for Zipf distribution!");
        }
        sampleDistribution = new ZipfSampleDistribution(begin, end, parameter[0]);
        break;
      }
      default -> 	throw new RuntimeException("Parameter error: The distribution '"
                    + this.distribution
                    + "' does not exist in the lib!");
    }
  }
}
