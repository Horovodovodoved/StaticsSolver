import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Body {
  ArrayList<KnownForce> knownForces = new ArrayList<>();
  ArrayList<UnknownForce> unknownForces = new ArrayList<>();
  private double[] xForces;
  private double[] yForces;
  private double[] moments;


  Map<String, KnownForceProjection> x_knownMap = new HashMap<>();
  Map<String, KnownForceProjection> y_knownMap = new HashMap<>();
  Map<String, UnknownForceProjection> x_unknownMap = new HashMap<>();
  Map<String, UnknownForceProjection> y_unknownMap = new HashMap<>();


  public void setMoments(double[] moments) {
    this.moments = moments;
  }
  public void setXForces(double[] xForces) {
    this.xForces = xForces;
  }

  public void setYForces(double[] yForces) {
    this.yForces = yForces;
  }

  /*todo добавить массив известных и неизвестных сил по двум осям
   * продумать момент с моментами

   */
}
