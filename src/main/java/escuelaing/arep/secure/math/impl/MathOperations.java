package escuelaing.arep.secure.math.impl;

import escuelaing.arep.secure.math.Functions;

/**
 * MathOperations
 */
public class MathOperations implements Functions {

  public MathOperations() {
  }

  @Override
  public Double sum(Double a, Double b) {
    return a + b;
  }

  @Override
  public Double substrac(Double a, Double b) {
    return a - b;
  }

  @Override
  public Double multiply(Double a, Double b) {
    return a * b;
  }

  @Override
  public Double divide(Double a, Double b) {
    if (b == 0)
      return Double.NaN;
    return a / b;
  }

  @Override
  public Double exponent(Double a, Double b) {
    Double res = 0.0;
    for (Integer i = 0; i < b; i++)
      res += a;
    return res;
  }

  @Override
  public Double square(Double a) {
    return a * a;
  }

}