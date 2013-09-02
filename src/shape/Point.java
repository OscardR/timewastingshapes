
package shape;


public class Point extends AbstractPoint {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -5352773188503727696L;


	/**
	 * Constructor por defecto, en las coordenadas (0, 0).
	 */
	public Point() {
		setX(0);
		setY(0);
	}


	/**
	 * Podemos construir un punto con las coordenadas de
	 * otro punto.
	 * 
	 * @param aPoint punto a copiar.
	 */
	public Point(Point aPoint) {
		setX(aPoint.getX());
		setY(aPoint.getY());
	}


	/**
	 * Constructor que admite las coordenadas para x e y.
	 * 
	 * @param x
	 * @param y
	 */
	public Point(double x, double y) {
		setX(x);
		setY(y);
	}


	/**
	 * Devuelve el ángulo entre dos puntos. Para ello, se
	 * mira la inclinación del segmento que los une respecto
	 * al ángulo 0 (la horizontal).
	 * 
	 * @param aPoint el punto al cual trazar el ángulo.
	 * @return el ángulo en radianes.
	 */
	public double angleTo(AbstractPoint aPoint) {
		double dx = aPoint.getX() - getX();
		double dy = aPoint.getY() - getY();
		return Math.atan2(dy, dx);
	}


	/**
	 * @return la distancia de un punto a otro punto.
	 */
	public double distanceTo(AbstractPoint aPoint) {
		double distX = getX() - aPoint.getX();
		double distY = getY() - aPoint.getY();
		return Math.sqrt(distX * distX + distY * distY);
	}


	/**
	 * El punto medio de dos puntos, geométricamente
	 * hablando.
	 * 
	 * @return un punto que está en el centro del segmento
	 *         que une esos dos puntos.
	 */
	public Point mean(AbstractPoint aPoint) {
		return new Point(
			(getX() + aPoint.getX()) / 2,
			(getY() + aPoint.getY()) / 2);
	}


	public String toString() {
		return "(" + Math.rint(getX() * getDisplayResolution())
				/ getDisplayResolution() + ", "
				+ Math.rint(getY() * getDisplayResolution())
				/ getDisplayResolution() + ")";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}


	/**
	 * Método equals sobrecargado para adecuarlo a nuestro
	 * modelo de coordenadas, que considera la centésima de
	 * píxel como la resolución máxima admitida para los
	 * cálculos.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;

		/*
		 * Consideramos las centésimas como la resolución
		 * máxima permitida para las coordenadas de un punto
		 */
		if (Math.abs(getX() - other.getX()) >= 0.01)
			return false;
		if (Math.abs(getY() - other.getY()) >= 0.01)
			return false;
		return true;
	}
}
