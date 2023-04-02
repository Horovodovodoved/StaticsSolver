public class KnownForce {
  String id;
  int body_idx;
  Point point;
  double value;
  double angle;
  
  public KnownForce(String id, int body_idx, Point point,
                    double value, double angle) {
    this.id = id;
    this.body_idx = body_idx;
    this.point = point;
    this.value = value;
    this.angle = angle;
  }
}
