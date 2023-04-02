import java.util.ArrayList;

public class Task {
  ArrayList<Body> bodies = new ArrayList<>();
  int num_variables = 0;
  
  public void addBody() {
    bodies.add(new Body());
  }
  
  public void addExternalForce(int body_idx, Point point,
                               double value, double angle) {
    KnownForce force = new KnownForce(body_idx, point, value, angle);
    bodies.get(body_idx).known_forces.add(force);
  }
  
  public void addUnknownExternalForce(int body_idx, Point point) {
    UnknownForce force = new UnknownForce(body_idx, point);
    num_variables += 2;
    bodies.get(body_idx).unknown_forces.add(force);
  }
  
  public void addUnknownExternalForce(int body_idx, Point point,
                                      boolean isAbsoluteValueKnown,
                                      double known_param) {
    UnknownForce force = new UnknownForce(body_idx, point,
        isAbsoluteValueKnown, known_param);
    num_variables += 1;
    bodies.get(body_idx).unknown_forces.add(force);
  }
  
  public void addHingedConnection(int body_1_idx, int body_2_idx, Point point) {
    UnknownForce force_1 = new UnknownForce(body_1_idx, point);
    UnknownForce force_2 = new UnknownForce(body_2_idx, point);
    bodies.get(body_1_idx).unknown_forces.add(force_1);
    bodies.get(body_2_idx).unknown_forces.add(force_2);
    // todo: Каким-то образом оставить информацию для решателя о том, что
    //  force_1 = -force_2
    num_variables += 2;
  }
  
  public void addHingedSupport(int body_idx, Point point) {
    addUnknownExternalForce(body_idx, point);
  }
  
  public void addSmoothSupport(int body_idx, Point point, double normal_angle) {
    addUnknownExternalForce(body_idx, point,
        false, normal_angle);
  }
}
