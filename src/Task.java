import java.util.ArrayList;

public class Task {
  ArrayList<Body> bodies = new ArrayList<>();
  int num_variables = 0; // projections; needless
  ArrayList<UnknownForce> unknown_forces = new ArrayList<>();
  ArrayList<UnknownForceProjection> unknown_projections = new ArrayList<>();
  
  public void addBody() {
    bodies.add(new Body());
  }
  
  public void addExternalForce(String id, int body_idx, Point point,
                               double value, double angle) {
    KnownForce force = new KnownForce(id, body_idx, point, value, angle);
    bodies.get(body_idx).known_forces.add(force);
    KnownForceProjection x_projection =
        new KnownForceProjection(force, "x");
    KnownForceProjection y_projection =
        new KnownForceProjection(force, "y");
    bodies.get(body_idx).x_known_map.put(x_projection.id, x_projection);
    bodies.get(body_idx).y_known_map.put(y_projection.id, y_projection);
  }
  
  public void addUnknownExternalForce(String id, int body_idx, Point point) {
    UnknownForce force = new UnknownForce(id, body_idx, point);
    unknown_forces.add(force);
    num_variables += 2;
    
    bodies.get(body_idx).unknown_forces.add(force);
    UnknownForceProjection x_projection =
        new UnknownForceProjection(force, "x");
    UnknownForceProjection y_projection =
        new UnknownForceProjection(force, "y");
    bodies.get(body_idx).x_unknown_map.put(x_projection.id, x_projection);
    bodies.get(body_idx).y_unknown_map.put(y_projection.id, y_projection);
    unknown_projections.add(x_projection);
    unknown_projections.add(y_projection);
  }
  
  public void addUnknownExternalForce(String id, int body_idx, Point point,
                                      boolean isAbsoluteValueKnown,
                                      double known_param) {
    UnknownForce force = new UnknownForce(id, body_idx, point,
        isAbsoluteValueKnown, known_param);
    unknown_forces.add(force);
    num_variables += 1;
    
    bodies.get(body_idx).unknown_forces.add(force);
    UnknownForceProjection x_projection =
        new UnknownForceProjection(force, "x");
    UnknownForceProjection y_projection =
        new UnknownForceProjection(force, "y");
    bodies.get(body_idx).x_unknown_map.put(x_projection.id, x_projection);
    bodies.get(body_idx).y_unknown_map.put(y_projection.id, y_projection);
    unknown_projections.add(x_projection);
    unknown_projections.add(y_projection);
  }
  
  public void addHingedConnection(String id_1, String id_2,
                                  int body_1_idx, int body_2_idx, Point point) {
    addUnknownExternalForce(id_1, body_1_idx, point);
    addUnknownExternalForce(id_2, body_2_idx, point);
    // todo: Каким-то образом оставить в id_1 и id_2 информацию для решателя
    //  о том, что
    //  force_1 = -force_2
    num_variables += 2;
  }
  
  public void addHingedSupport(String id, int body_idx, Point point) {
    addUnknownExternalForce(id, body_idx, point);
  }
  
  public void addSmoothSupport(String id, int body_idx,
                               Point point, double normal_angle) {
    addUnknownExternalForce(id, body_idx, point,
        false, normal_angle);
  }
}
