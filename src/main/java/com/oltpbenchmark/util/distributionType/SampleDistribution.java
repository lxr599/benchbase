package com.oltpbenchmark.util.distributionType;

import com.oltpbenchmark.scheduler.enums.DistributionType;

/**
 * @descriptions 数据分布接口
 * @author Ling Xiangrong
 * @date 2025/1/7 14:25
 */
public interface SampleDistribution {

  /**
   * @descriptions 按照分布获得指定区间[a,b]内的一个数
   * @author Ling Xiangrong
   * @date 2025/1/7 14:26
   * @param
   * @return double
   */
  double getData();

  int getIntegerData();

  static SampleDistribution getAccessDistribution(DistributionType type, int begin, int end, Double[] parameter) {

    if (begin >= end)
      throw new RuntimeException("begin must be less than end, begin < end!");

    switch (type) {
      case Uniform:
        return new UniformSampleDistribution(begin, end);
      case Normal:
        return new NormalSampleDistribution(begin, end);
      case Zipfian:
        if (parameter.length == 0) {
          throw new RuntimeException("Parameter missed: exponent is needed for Zipfian distribution!");
        }
        return new ZipfSampleDistribution(begin, end, parameter[0]);
      default:
        throw new RuntimeException("Parameter error: The distribution '" + type + "' does not exist in the lib!");
    }
  }
}
