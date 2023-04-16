import java.util.ArrayList;

public class Body {
  private ArrayList<Force> forces = new ArrayList<>();
  
  public void addForce(Force force) {
    forces.add(force);
  }
  
  
  public double[] getXCoefs() {
    double[] row = new double[Task.getVariablesNum()];
    for (Force force : forces) {
      if (!force.IsForceKnown()) {
        row[force.getXId() - 1] = 1; // 'cause 1-numeration!!!!!!
      }
    }
    return row;
  }
  
  public double[] getXRightConst() { // "right" because it's after "=" mark
    double sum = 0;
    for (Force force : forces) {
      if (force.IsForceKnown()) {
        sum += force.getProjection(Axis.X);
      }
    }
    double right_const = -sum;
    return new double[] {right_const};
  }
  
  public double[] getYCoefs() {
    double[] row = new double[Task.getVariablesNum()];
    for (Force force : forces) {
      if (!force.IsForceKnown()) {
        row[force.getYId() - 1] = 1;
      }
    }
    return row;
  }
  
  public double[] getYRightConst() {
    double sum = 0;
    for (Force force : forces) {
      if (force.IsForceKnown()) {
        sum += force.getProjection(Axis.Y);
      }
    }
    double right_const = -sum;
    return new double[] {right_const};
  }
  
  public double[] getMCoefs() {
    double[] row = new double[Task.getVariablesNum()];
    for (Force force : forces) {
      if (!force.IsForceKnown()) {
        // любопытно: следующие выражения для моментов
        // не зависят от положения точки на плоскости;
        // я нашел поистине удивительное доказательство этого предложения,
        // но комментарии здесь слишком узки для того, чтобы вместить его
        row[force.getXId() - 1] = force.getPoint().getY();
        row[force.getYId() - 1] = -force.getPoint().getX();
      }
    }
    return row;
  }
  
  public double[] getMRightConst() {
    double sum = 0;
    for (Force force : forces) {
      if (force.IsForceKnown()) {
        sum += force.getProjection(Axis.X) * force.getPoint().getY();
        sum += force.getProjection(Axis.Y) * -force.getPoint().getX();
      }
    }
    double right_const = -sum;
    return new double[] {right_const};
  }
  
  public ArrayList<double[]> getKnownAngleCoefs() {
    ArrayList<double[]> rows = new ArrayList<>();
    for (Force force : forces) {
      if (!force.IsForceKnown() && force.IsAngleKnown()) {
        double[] row = new double[Task.getVariablesNum()];
        row[force.getYId() - 1] = 1;
        row[force.getXId() - 1] = -Math.tan(Math.toRadians(force.getAngle()));
        rows.add(row);
      }
    }
    return rows;
  }
  
  public ArrayList<Force> getForces() { return forces; }
}
