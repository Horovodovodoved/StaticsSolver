public class RoughSupport {
  private Force normal_force;
  private Force friction_force;
  private double friction_coef;
  public RoughSupport(Force normal_force, Force friction_force, double friction_coef) {
    this.normal_force = normal_force;
    this.friction_force = friction_force;
    this.friction_coef = friction_coef;
  }
  
//  public double[] getXCoefs() {
//    double[] row = new double[Task.getVariablesNum()];
//    row[normal_force.getXId() - 1] = 1; // 'cause 1-numeration!!!!!!
//    row[friction_force.getXId() - 1] = 1;
//    return row;
//  }
//
//  public double[] getYCoefs() {
//    double[] row = new double[Task.getVariablesNum()];
//    row[normal_force.getYId() - 1] = 1;
//    row[friction_force.getYId() - 1] = 1;
//    return row;
//  }
}
