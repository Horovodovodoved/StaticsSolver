public class Solver {
  static int n = 5; //условно количество переменных


  public static Answer getSolution(Task task) {
    double[][] A = new double[n][n];
    double[] b = new double[n];
    return null;
  }
  //заполнение матриц

  /*По поводу того, что написано ниже
  * array[j].id - сравнивает id для j столбца с id у силы
  * т.е они типо должны быть на одних позициях F1.id = 1, F2.id=2 и т.д
  * далее в свитче идет проверка, знаем ли мы что-нибудь об неизвестной силе
  * если у нее есть известный параметр, то добавляем его
  * если нет, то 1 * на +1 или -1 (где брать направление?)
  * setBMatrix сумма известных
  *
  * нужно: для body массив known/unknown forces,
  *       а так-же подумать над forceId
  *
  * */
  public static double[][] setAMatrix(){
    double[][] A = new double[n][n];
    for(int i = 0;i< A.length;i++){
      for (int j = 0;j<A.length;j++){
        if(array[j].id == j){
          boolean flag = true;
          switch (array[j].isOneParameterKnown){
            case true:
              A[i][j] = 1 * knownParameter;
              flag = false;
              break;
            case false:
              A[i][j] = 1; // посмотреть какая переменная отвечает за направление
              flag = false;
              break;
          }
          if(flag){
            A[i][j] = 0;
          }
        }
      }
    }
    return A;
  }
  public static double[] setBMatrix(){
    double b[] = new double[n];
    for(int i = 0 ;i<n;i++){
      b[i] = sumB(knownForcesArray);
    }
    return b;
  }

  public static double sumB(double [] knownForcesArray){
    double sum = 0;
    for(int i = 0; i<knownForcesArray.length;i++){
      knownForcesArray[i]+=sum;
    }
    return sum;
  }

  //методы для заполнения матриц
//  public static void setMoments(){}
//  public static void setXForces(Body body){
//
//  }
//  public static void setYForces(Body body){}

}

/*todo
    предположнеи по поводу того как реализовать уравнения

*/
