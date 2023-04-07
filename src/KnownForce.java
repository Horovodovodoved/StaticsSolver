public class KnownForce {
  private String id;
  private int body_idx;
  private Point point;
  private Double value;
  private Double angle;
  
  public KnownForce(String id, int body_idx, Point point,
                    Double value, Double angle) {
    this.id = id;
    this.body_idx = body_idx;
    this.point = point;
    this.value = value;
    this.angle = angle;
  }



  public double getProjection(Axis axis) {
    switch(axis) {
      case X:
        return Math.cos(angle * (Math.PI/180)) * value;
      case Y:
        return Math.sin(angle * (Math.PI/180)) * value;
    }

    return 0;
  }




/*
Далее - геттеры и сеттеры
 */
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getBody_idx() {
    return body_idx;
  }

  public void setBody_idx(int body_idx) {
    this.body_idx = body_idx;
  }

  public Point getPoint() {
    return point;
  }

  public void setPoint(Point point) {
    this.point = point;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public Double getAngle() {
    return angle;
  }

  public void setAngle(Double angle) {
    this.angle = angle;
  }
}
