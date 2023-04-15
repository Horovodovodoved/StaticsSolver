public class HingedConnection {
  Force force_1;
  Force force_2;
  
  public HingedConnection(Force force_1, Force force_2) {
    this.force_1 = force_1;
    this.force_2 = force_2;
  }
  
  
  public double[] getXCoefs() {
    double[] row = new double[Task.getVariablesNum()];
    row[force_1.getXId() - 1] = 1; // 'cause 1-numeration!!!!!!
    row[force_2.getXId() - 1] = 1;
    return row;
  }
  
  public double[] getYCoefs() {
    double[] row = new double[Task.getVariablesNum()];
    row[force_1.getYId() - 1] = 1;
    row[force_2.getYId() - 1] = 1;
    return row;
  }
}
