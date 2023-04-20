import java.util.List;

public class Matrix {
    private double[][] matrix;
    private final int dimension;
    public static final double EPS = 1E-5;

    public Matrix (double[][] matrix) {
        dimension = matrix.length;

        for (int i = 0; i < dimension; i++) {
            if (matrix[i].length != dimension) {
                throw new IllegalArgumentException("All rows must have the same length.");
            }
        }

        this.matrix = matrix;
    }

    public Matrix(List<double[]> matrix) {
        dimension = matrix.size();
        this.matrix = new double[dimension][dimension];

        for(int row = 0; row < dimension; row++) {
            System.arraycopy(matrix.get(row), 0, this.matrix[row], 0, dimension);
        }
    }

    public Matrix (int dimension) {
        this.dimension = dimension;
        matrix = new double[dimension][dimension];
    }

    /*
    Единственный метод, который нужно использовать - getAnswer(для решения матричного уравнения).
    Остальные за ненадобностью в остальной части преокта были помечены модификатором private.
     */

    public static double[] getAnswerGauss(Matrix mainMatrix, List<double[]> columnList) {
        double[][] A = new double[mainMatrix.getDimension()][mainMatrix.getDimension()+1];
        double[] answer = new double[mainMatrix.getDimension()];
        int size = mainMatrix.getDimension();

        //инициализация массива
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                A[i][j] = mainMatrix.getArray()[i][j];
            }
        }

        for(int i = 0; i <size; i++) {
            A[i][size] = columnList.get(i)[0];
        }

        //прямой ход мтеода Гаусса
        for (int k = 0; k < size; k++) {
            double maxv = 0; int position_of_line_with_maxv = k;
            for (int i = k; i <size; i++) {
                if (Math.abs(A[i][k]) > maxv) {
                    maxv = Math.abs(A[i][k]);
                    position_of_line_with_maxv = i;
                }
            }
            for (int j = 0; j < size+1; j++) {
                double tmp = A[k][j];
                A[k][j] = A[position_of_line_with_maxv][j];
                A[position_of_line_with_maxv][j] = tmp;
            }

            if (Math.abs(maxv) < EPS) {
                continue;
            }

            for (int i = 0; i < size; i++) {
                if (i == k) continue;

                double multiplier = A[i][k]/A[k][k];
                for (int j = k; j < size+1; j++) {
                    A[i][j] -= multiplier * A[k][j];
                }
            }
        }

        //Делим каждый коэффициент i- го уравнения на первый ненулевой коэффициент этого уравнения
        for (int k = 0; k < size; k++) {
            if (Math.abs(A[k][k]) > EPS) {
                double multiplier = A[k][k];
                if (Math.abs(multiplier) < EPS) continue;
                for (int j = k; j < size+1; j++) {
                    A[k][j] /= multiplier;
                }
            }
        }

        //Отмечаем одинаковые строки в расширенной матрице A СЛАУ
        Boolean[] mark = new Boolean[size];
        for (int i = 0; i < size; i++) {
            mark[i] = Boolean.FALSE;
        }

        for (int k1 = 0; k1 < size; k1++) {
            if (mark[k1] == Boolean.TRUE) continue;
            for (int k2 = k1+1; k2 < size; k2++) {
                boolean is_equal = true;
                for (int j = 0; j < size+1; j++) {
                    if (Math.abs(A[k1][j] - A[k2][j]) > EPS) {
                        is_equal = false;
                        break;
                    }
                }
                if (is_equal) {
                    mark[k2] = true;
                }
            }
        }

        //Проверяем СЛАУ на совместность
        for (int i = 0; i < size; i++) {
            int cnt_of_zeroes = 0;
            for (int j = 0; j < size+1; j++) {
                if (Math.abs(A[i][j]) < EPS) {
                    cnt_of_zeroes++;
                    A[i][j] = 0.0;
                }
            }
            if (cnt_of_zeroes == size+1) {
                mark[i] = Boolean.TRUE;
            }
            if (cnt_of_zeroes == size && Math.abs(A[i][size]) > EPS) {
                System.out.println("The system of equations is inconsistent");
                return null;
            }
        }

        //Все уникальные (по-сути, ненулевые) строки "переносим вперёд"
        for (int i = 0; i < size; i++) {
            for (int j = i+1; j < size; j++) {
                if (mark[i] == Boolean.TRUE && mark[j] == Boolean.FALSE) {
                    Swap_Lines(i, j, size, A, mark);
                }
            }
        }

        //Если количество ненулевых строк совпадает с количеством уравнений, то система имеет единственное решение
        int cnt_of_marks = 0;
        for (int i = 0; i < size; i++) {
            if (mark[i] == Boolean.TRUE) cnt_of_marks++;
        }
        int bottom_border = size-1-cnt_of_marks;

        if (bottom_border == size-1) {
            for (int k = size-1; k >= 0; k--) {
                answer[k] = A[k][size] / A[k][k];
            }

            return answer;
        }

        return answer;
    }

    public static double[][] getAnswer(Matrix mainMatrix, List<double[]> columnList) {
        double[][] column = new double[columnList.size()][1];

        for(int row = 0; row < columnList.size(); row++) {
            column[row][0] = columnList.get(row)[0];
        }

        return multiplyMatrices(inverse(mainMatrix).getArray(), column);
    }

    private static void Swap_Lines(int k1, int k2, int n, double[][] A, Boolean[] mark) {
        for (int j = 0; j < n; j++) {
            Double tmp;
            tmp = A[k1][j];
            A[k1][j] = A[k2][j];
            A[k2][j] = tmp;
        }
        Boolean tmp;
        tmp = mark[k1];
        mark[k1] = mark[k2];
        mark[k2] = tmp;
    }



    private double[][] getArray () {
        return matrix;
    }

    private int getDimension() {
        return dimension;
    }

    private double determinant()
    {
        if (dimension == 1) {
            return matrix[0][0];
        }

        double determinant = 0;

        for (int j = 0; j < dimension; j++)
        {
            if (j % 2 == 0)
                determinant += matrix[0][j] * getSubmatrix(this, 0, j).determinant();
            else
                determinant -= matrix[0][j] * getSubmatrix(this, 0, j).determinant();
        }

        return determinant;
    }

    private static Matrix getSubmatrix(Matrix m, int rowToExclude, int colToExclude)
    {
        double[][] values = new double[m.dimension - 1][];
        for (int i = 0; i < m.dimension; i++)
        {

            if (i < m.dimension - 1)
                values[i] = new double[m.dimension - 1];
            // copy values:
            for (int j = 0; j < m.dimension; j++)
                if (i != rowToExclude && j != colToExclude)
                    values[i < rowToExclude ? i : i - 1][j < colToExclude ? j : j - 1] = m.matrix[i][j];
        }
        return new Matrix(values);
    }

    private static Matrix inverse(Matrix matrix) {
        double[][] inverse = new double[matrix.getDimension()][matrix.getDimension()];

        // minors and cofactors
        for (int i = 0; i < matrix.getDimension(); i++)
            for (int j = 0; j < matrix.getArray()[i].length; j++)
                inverse[i][j] = Math.pow(-1, i + j)
                        * getSubmatrix(matrix, i, j).determinant();

        // adjugate and determinant
        double determinant = 1.0 / matrix.determinant();

        for (int i = 0; i < inverse.length; i++) {
            for (int j = 0; j <= i; j++) {
                double temp = inverse[i][j];
                inverse[i][j] = inverse[j][i] * determinant;
                inverse[j][i] = temp * determinant;
            }
        }

        return new Matrix(inverse);
    }

    private static double[][] multiplyMatrices(double[][] firstMatrix, double[][] secondMatrix) {
        double[][] result = new double[firstMatrix.length][secondMatrix[0].length];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = multiplyMatricesCell(firstMatrix, secondMatrix, row, col);
            }
        }

        return result;
    }

    private static double multiplyMatricesCell(double[][] firstMatrix, double[][] secondMatrix, int row, int col) {
        double cell = 0;
        for (int i = 0; i < secondMatrix.length; i++) {
            cell += firstMatrix[row][i] * secondMatrix[i][col];
        }
        return cell;
    }

}
