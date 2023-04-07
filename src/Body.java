import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Body {
  private ArrayList<KnownForce> knownForces = new ArrayList<>();
  @Deprecated
  private ArrayList<UnknownForce> unknownForces = new ArrayList<>();

  private Map<String, KnownForceProjection> x_knownMap = new HashMap<>();
  private Map<String, KnownForceProjection> y_knownMap = new HashMap<>();
  @Deprecated
  private Map<String, UnknownForceProjection> x_unknownMap = new HashMap<>();
  @Deprecated
  private Map<String, UnknownForceProjection> y_unknownMap = new HashMap<>();

  public ArrayList<KnownForce> getKnownForces() {
    return knownForces;
  }

  public void setKnownForces(ArrayList<KnownForce> knownForces) {
    this.knownForces = knownForces;
  }

  public ArrayList<UnknownForce> getUnknownForces() {
    return unknownForces;
  }

  public void setUnknownForces(ArrayList<UnknownForce> unknownForces) {
    this.unknownForces = unknownForces;
  }

  public Map<String, KnownForceProjection> getX_knownMap() {
    return x_knownMap;
  }

  public void setX_knownMap(Map<String, KnownForceProjection> x_knownMap) {
    this.x_knownMap = x_knownMap;
  }

  public Map<String, KnownForceProjection> getY_knownMap() {
    return y_knownMap;
  }

  public void setY_knownMap(Map<String, KnownForceProjection> y_knownMap) {
    this.y_knownMap = y_knownMap;
  }

  public Map<String, UnknownForceProjection> getX_unknownMap() {
    return x_unknownMap;
  }

  public void setX_unknownMap(Map<String, UnknownForceProjection> x_unknownMap) {
    this.x_unknownMap = x_unknownMap;
  }

  public Map<String, UnknownForceProjection> getY_unknownMap() {
    return y_unknownMap;
  }

  public void setY_unknownMap(Map<String, UnknownForceProjection> y_unknownMap) {
    this.y_unknownMap = y_unknownMap;
  }

  public void addForce(KnownForce force) {
    knownForces.add(force);
  }
}
