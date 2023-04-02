public class KnownForce {
  int body_idx;
  Point point;
  double value;
  double angle;
  
  public KnownForce(int body_idx, Point point, double value, double angle) {
    this.body_idx = body_idx;
    this.point = point;
    this.value = value;
    this.angle = angle;
  }
}
