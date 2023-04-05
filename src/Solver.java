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

  //У тела будет массив с телами, у которых есть массивы сил для каждого тела
  //Т.е условно будет 2 с X и Y
  public static double[][] setAMatrix(){
    double[][] A = new double[n][n];
    int indexForForces = arrayForces.length;
    String forceForMatrix = "X";
    for(int i = 1;i<=A.length;i++){
      for (int j = 1;j<=A.length;j++){
        switch (forceForMatrix){
          case "X":
            A[i][j] = bodyArray[i].getXForce(j); // getForce смотрит и сопоставляет силы
            break;
          case "Y":
            A[i][j] = bodyArray[i].getYForce(j);
            break;
          case "M":
            A[i][j] = bodyArray[i].getMForce(j);
            break;
        }

      }
    }
    return A;
  }
  public static double[] setBMatrix(){
    double b[] = new double[n];
    for(int i = 0 ;i<n;i++){
      b[i] = -sumB(knownForcesArray);
    }
    return b;
  }

  public static double sumB(double [] knownForcesArray){
    double sum = 0;
    for(int i = 0; i<knownForcesArray.length;i++){
      sum+=knownForcesArray[i];
    }
    return sum;
  }


  //как ты и сказал, нужен массив с неизвестными силами
  //arrayUnknownForces - такой массив
  public void ifWeKnowAngle(){
    int index = 0;
    while (true) {
      Body body = bodyArray[index];
      for (int i = 0; i < body.unknownXForcesArray.length; i++) {
        for (int j = 0; j < body.knownYForcesArray.length; j++) {
          if ((body.unknownYFroces[i]  - body.unknownXForces[j]*Math.tan(body.unknownXForces[i].getAngle)) == 0)) {
            return ;

          }
        }
      }
    }
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

//        if(array[j].id == j){
//          boolean flag = true;
//          switch (array[j].isOneParameterKnown){
//            case true:
//              A[i][j] = 1 * knownParameter;
//              flag = false;
//              break;
//            case false:
//              A[i][j] = 1; // посмотреть какая переменная отвечает за направление
//              flag = false;
//              break;
//          }
//          if(flag){
//            A[i][j] = 0;
//          }
//        }