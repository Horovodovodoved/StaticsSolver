import java.util.Scanner;

/*
Тесты:

- 0 переменных

+ 1 тело, 3 силы, 1 неизв.

- 1 стержень, 1 шарнир. опор., 1 изв., 1 неизв. вдвое ближе
Имеет бесконечно решений.

+ 1 стержень, 1 шарнир. опор., 1 изв., 1 ч. неизв. вдвое ближе против. направ.
    task.setBodies(1);
    task.addHingedSupport(0, new Point(0, 0));
    task.addKnownForce(0,new Point(0,2),1, 180);
    task.addUnknownForceWithKnownAngle(0, new Point(0,1), 0);

+ Водонапорная башня, неизв. ветер
    task.setBodies(1);
    task.addHingedSupport(0, new Point(0, 0));
    task.addKnownForce(0, new Point(-15f / 2, 20), 80000, -90);
    task.addUnknownForceWithKnownAngle(0, new Point(-15f / 2 - 2, 20),0);
    
+? Трёхшарнирная арка
    task.setBodies(2);
    Point A = new Point(0, 0);
    Point B = new Point(2, 0);
    Point C = new Point(1, 1);
    Point P = new Point(0, 1);
    task.addHingedSupport(0, A);
    task.addHingedSupport(1, B);
    task.addHingedConnection(0, 1, C);
    task.addKnownForce(0, P, 1, 0);
    
- Шарнирный четырёхугольник
    task.setBodies(3);
    Point A = new Point(0, 1);
    Point B = new Point(1, 2);
    Point C = new Point(0, 0);
    Point D = new Point(3, 0);
    task.addHingedSupport(0, C);
    task.addKnownForce(0, A, 100, 0);
    task.addHingedConnection(0, 1, A);
    task.addUnknownForceWithKnownAngle(1, B, 105);
    task.addHingedConnection(1, 2, B);
    task.addHingedSupport(2, D);
14 уравнений
Бесконечная рекурсия в методе Matrix.determinant()

- Мост
    task.setBodies(4);
    Point A = new Point(0, 0);
    Point B = new Point(2, 0);
    Point C = new Point(4, 0);
    Point D = new Point(6, 0);
    Point P = new Point(0, 1);
    Point E = new Point(1, 1);
    Point F = new Point(3, 1);
    Point G = new Point(5, 1);
    task.addHingedSupport(0, A);
    task.addHingedSupport(3, D);
    task.addUnknownForceWithKnownAngle(1, B, 90);
    task.addUnknownForceWithKnownAngle(2, C, 90);
    task.addHingedConnection(0, 1, E);
    task.addHingedConnection(1, 2, F);
    task.addHingedConnection(2, 3, G);
    task.addKnownForce(0, P, 1, 0);

20 уравнений
После makeMatrixEquation() почему-то в b 19 строк
Бесконечная рекурсия в методе Matrix.determinant()

 */
public class Main {
  static Scanner scan = new Scanner(System.in);
  
  
  public static void main(String[] args) {
    Task task = new Task();
    setTask(task);
    Solver solver = new Solver(task);
    solver.printAnswer();
  }
  
  private static void setTask(Task task) {
    task.setBodies(2);
    Point A = new Point(0, 0);
    Point B = new Point(2, 0);
    Point C = new Point(1, 1);
    Point P = new Point(0, 1);
    task.addHingedSupport(0, A);
    task.addHingedSupport(1, B);
    task.addHingedConnection(0, 1, C);
    task.addKnownForce(0, P, 1, 0);
  }
}
