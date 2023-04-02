public class Solver {
  static int n = 12; //условно количество переменных


  public static Answer getSolution(Task task) {
    double[][] A;
    double[] b;
  }
  //заполнение матриц
  public static void setAMatrix(){

  }
  public static void setBMatrix(double[] b){
    for(int i = 0 ;i<n;i++){
      b[i]+= getBodyForces(i);
    }
  }

  //методы для заполнения матриц
  public static void setMoments(){}
  public static void setXForces(Body body){}
  public static void setYForces(Body body){}


  public static double getBodyForces(int index){
    //здесь будут выдаваться силы из массива тел
    double sum = 23;
    return sum;
  }
}

/*todo
    предположнеи по поводу того как реализовать уравнения

*/
