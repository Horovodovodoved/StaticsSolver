@Deprecated
public class KnownForceProjection {
  String id;
  int body_idx;
  Point point;
  double value;
  
  public KnownForceProjection(KnownForce force, String axis) {
    this.id = force.id + axis;
    this.body_idx = force.body_idx;
    this.point = force.point;
    switch (axis.toLowerCase()) {
      case "x":
        this.value = Math.cos(force.angle)*force.value;
        break;
      case "y":
        this.value = Math.sin(force.angle)*force.value;
        break;
    }
  }
}
