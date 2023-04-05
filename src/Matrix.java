public class Matrix {
    private double[][] matrix;
    private int dimension;

    public Matrix (double[][] matrix) {
        dimension = matrix.length;

        for (int i = 0; i < dimension; i++) {
            if (matrix[i].length != dimension) {
                throw new IllegalArgumentException("All rows must have the same length.");
            }
        }

        this.matrix = matrix;
    }

    public Matrix (int dimension) {
        this.dimension = dimension;
        matrix = new double[dimension][dimension];
    }

    /*
    Единственный метод, который нужно использовать - solve(для решения матричного уравнения).
    Остальные за ненадобностью в остальной части преокта были помечены модификатором private.
     */

    public static double[][] solve(Matrix mainMatrix, Matrix column) {
        return multiplyMatrices(column.getArray(), inverse(mainMatrix).getArray());
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
