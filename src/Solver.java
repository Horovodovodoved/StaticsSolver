import java.util.ArrayList;

public class Solver {
  Task task;
  Answer answer;
  double[][] answer_column;
  ArrayList<double[]> A = new ArrayList<>();
  ArrayList<double[]> b = new ArrayList<>();
  
  public Solver(Task task) {
    this.task = task;
    makeMatrixEquation();
    // answer = new Answer(Matrix.getAnswer(new Matrix(A), new Matrix(b)));
    answer_column = Matrix.getAnswer(new Matrix(A), new Matrix(b));
    setDiscoveredForcesValues();
    setAnswer();
  }
  
  // костыльный, для тестов, todo: нормальный getAnswer()
//  public ArrayList<ArrayList<double[]>> getAnswer() {
//    ArrayList<ArrayList<double[]>> a = new ArrayList<>();
//    a.add(A);
//    a.add(b);
//    return a;
//  }
  
  // What do we have? 5 types of equations!
  // What do we need? As many equations, as unknown variables!
  // Who are we?! Mathematicians!!!
  private void makeMatrixEquation() {
    addXEquations();
    addYEquations();
    addMEquations();
    addKnownAngleEquations();
    addHingedConnectionEquations();
    checkEquationsNumber();
  }
  
  private void setDiscoveredForcesValues() {
    for (Body body : task.getBodies()) {
      for (Force force : body.getForces()) {
        if (!force.IsForceKnown()) {
          // "... - 1" is because of 1-numeration!!!!!!!!!!!!
          // f = sqrt(f_x**2 + f_y**2)
          force.setValue(Math.sqrt(
              Math.pow(answer_column[force.getXId() - 1][0], 2) +
                  Math.pow(answer_column[force.getYId() - 1][0], 2)));
          // a = tg(f_y / f_x)
          force.setAngle(Math.toDegrees(
              Math.atan(answer_column[force.getYId() - 1][0] /
                  answer_column[force.getXId() - 1][0])));
        }
      }
    }
  }
  
  private void setAnswer() { // Your eyes don't deceive you. It's private.
    for (Body body : task.getBodies()) {
      answer.addNextBody();
      for (Force force : body.getForces()) {
        answer.addNextForce(force);
      }
    }
  }
  
  public void printAnswer() {
    for (String s : answer.getStrings()) {
      System.out.println(s);
    }
  }
  
  private void addXEquations() {
    for (Body body : task.getBodies()) {
      A.add(body.getXCoefs());
      // "right" because it's after "=" mark
      b.add(body.getXRightConst());
    }
  }
  
  private void addYEquations() {
    for (Body body : task.getBodies()) {
      A.add(body.getYCoefs());
      b.add(body.getYRightConst());
    }
  }
  
  private void addMEquations() {
    for (Body body : task.getBodies()) {
      A.add(body.getMCoefs());
      b.add(body.getMRightConst());
    }
  }
  
  private void addKnownAngleEquations() {
    for (Body body : task.getBodies()) {
      A.addAll(body.getKnownAngleCoefs());
    }
    for (int i = 0; i < A.size() - b.size(); i++) {
      b.add(new double[]{0});
    }
  }
  
  private void addHingedConnectionEquations() {
    for (HingedConnection hc : task.getHingedConnections()) {
      A.add(hc.getXCoefs());
      A.add(hc.getYCoefs());
      b.add(new double[]{0});
      b.add(new double[]{0});
    }
  }
  
  private void checkEquationsNumber() {
    //todo
    // если больше:
    //   выкинуть лишние
    // если меньше:
    //   выдать ошибку
    
    // пока просто выкинем нулевые строки
    iterating_by_rows:
    for (int i = 0; i < A.size(); ++i) {
      for (int j = 0; j < Task.getVariablesNum(); j++) {
        if (A.get(i)[j] != 0) {
          continue iterating_by_rows;
        }
      }
      A.remove(i);
      b.remove(i);
      --i;
    }
  }
}