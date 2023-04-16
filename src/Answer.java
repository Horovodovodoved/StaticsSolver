import java.util.ArrayList;

public class Answer {
  ArrayList<String> strings = new ArrayList<>();
  int current_body_number;
  int current_force_number;
  
  public void addNextBody() {
    ++current_body_number;
    strings.add(String.format("\nСилы, приложенные к телу №%d:\n",
        current_body_number));
  }
  
  public void addNextForce(Force force) {
    ++current_force_number;
    strings.add(String.format("Сила F%d:", current_force_number));
    strings.add(String.format("точка: (%.1f; %.1f)",
        force.getPoint().getX(), force.getPoint().getY()));
    strings.add(String.format("величина: %.2f:", force.getValue()));
    strings.add(String.format("угол: %.1f°\n", force.getAngle()));
  }
  
  public ArrayList<String> getStrings() {
    return strings;
  }
}
