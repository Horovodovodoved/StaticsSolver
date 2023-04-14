public class Force {
  private int x_id;
  private int y_id;
  private boolean is_force_known = true;
  private boolean is_angle_known = true;
  private final int body_index;
  private final Point point;
  private double value;
  private double angle; // in degrees!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  
  // Known force
  public Force(int body_index, Point point, double value, double angle) {
    this.body_index = body_index;
    this.point = point;
    this.value = value;
    this.angle = angle;
  }
  
  // Partially known force
  public Force(int body_index, Point point, double angle, int x_id, int y_id) {
    is_force_known = false;
    this.body_index = body_index;
    this.point = point;
    this.angle = angle;
    this.x_id = x_id;
    this.y_id = y_id;
  }
  
  // Unknown force
  public Force(int body_index, Point point, int x_id, int y_id) {
    is_force_known = false;
    is_angle_known = false;
    this.body_index = body_index;
    this.point = point;
    this.x_id = x_id;
    this.y_id = y_id;
  }



  public double getProjection(Axis axis) throws Exception {
    if (!is_force_known) {
      throw new Exception("Нельзя найти проекцию неизвестной силы!");
    }
    switch(axis) {
      case X:
        return Math.cos(Math.toRadians(angle)) * value;
      case Y:
        return Math.sin(Math.toRadians(angle)) * value;
      default:
        return 0;
    }
  }
  
// Далее - геттеры; сеттеры - только для value и angle
  public int getXId() {
    return x_id;
  }
  
  public int getYId() {
    return y_id;
  }
  
  public boolean IsForceKnown() {
    return is_force_known;
  }
  
  public boolean IsAngleKnown() {
    return is_angle_known;
  }
  

  public int getBodyIndex() {
    return body_index;
  }

  public Point getPoint() {
    return point;
  }

  public double getValue() {
    return value;
  }

  public double getAngle() {
    return angle;
  }
  
  public void setValue(double value) {
    this.value = value;
  }
  
  public void setAngle(double angle) {
    this.angle = angle;
  }
}
