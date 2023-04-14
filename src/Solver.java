import java.util.ArrayList;

public class Solver {
  Task task;
  ArrayList<double[]> A = new ArrayList<>();
  ArrayList<double[]> b = new ArrayList<>();
  
  public Solver(Task task) {
    this.task = task;
    makeMatrixEquation();
  }
  
  public Answer getAnswer() {
    return null;
  }
  
  // We have 5 types of equations
  // We need as many equations, as unknown variables
  // We are mathematicians
  private void makeMatrixEquation() {
    addXEquations();
    addYEquations();
    addMEquations();
    addKnownAngleEquations();
    addHingedConnectionEquations();
    checkEquationsNumber();
  }
  
  private void addXEquations() {
    for (Body body : task.getBodies()) {
      A.add(body.getXCoefs());
      b.add(body.getXLeftConst()); // "left" because it's before "=" mark
    }
  }
  
  private void addYEquations() {
    for (Body body : task.getBodies()) {
      A.add(body.getYCoefs());
      b.add(body.getYLeftConst());
    }
  }
  
  private void addMEquations() {
    for (Body body : task.getBodies()) {
      A.add(body.getMCoefs());
      b.add(body.getMLeftConst());
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
    //   проверить на совместность
    //   если совместна:
    //     выкинуть лишние
    //   иначе:
    //     выдать ошибку
    // если меньше:
    //   выдать ошибку
  }
}