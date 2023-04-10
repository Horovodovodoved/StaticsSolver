@Deprecated
public class UnknownForceProjection {
  String id;
  UnknownForce force;
  
  public UnknownForceProjection(UnknownForce force, String axis) {
    this.id = force.id + axis;
    this.force = force;
  }
}
