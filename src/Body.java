import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Body {
  ArrayList<KnownForce> known_forces = new ArrayList<>();
  ArrayList<UnknownForce> unknown_forces = new ArrayList<>();

  Map<String, KnownForceProjection> x_known_map = new HashMap<>();
  Map<String, KnownForceProjection> y_known_map = new HashMap<>();
  Map<String, UnknownForceProjection> x_unknown_map = new HashMap<>();
  Map<String, UnknownForceProjection> y_unknown_map = new HashMap<>();



}
