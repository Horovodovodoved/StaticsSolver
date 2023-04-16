import java.util.ArrayList;

public class Solver {
  Task task;
  Answer answer;
  ArrayList<double[]> A = new ArrayList<>();
  ArrayList<double[]> b = new ArrayList<>();

  public Solver(Task task) {
    this.task = task;
    makeMatrixEquation();
    //answer = new Answer(Matrix.getAnswer(new Matrix(A), b));
  }

  public Answer getAnswer() { //todo
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
    // todo: решить Ax=b, т. е. найти x;
    //  придумать, каким будет Answer и подогнать под него имеющийся ответ
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
  }
}