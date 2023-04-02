public class UnknownForce {
  int body_idx;
  Point point;
  boolean isOneParameterKnown = false;
  boolean isAbsoluteValueKnown;
  double known_parameter;
  
  public UnknownForce(int body_idx, Point point) {
    this.body_idx = body_idx;
    this.point = point;
  }
  
  public UnknownForce(int body_idx, Point point,
                      boolean isAbsoluteValueKnown, double known_parameter) {
    this.body_idx = body_idx;
    this.point = point;
    isOneParameterKnown = true;
    this.isAbsoluteValueKnown = isAbsoluteValueKnown;
    this.known_parameter = known_parameter;
  }
}
