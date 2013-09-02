
package shape;

import java.io.Serializable;


public interface Shape extends Serializable {

	/**
	 * Obtener la caja que contiene a la figura. Es un
	 * rectángulo.
	 * 
	 * @return un rectángulo que contiene a la figura.
	 */
	public Rectangle boundingBox();


	/**
	 * Comprueba que un punto está contenido en la figura
	 * 
	 * @param p el punto a comprobar
	 * @return true si está contenido, false de lo contrario
	 */
	public boolean contains(Point p);


	/**
	 * Obtener la posición de la figura.
	 * 
	 * @return un punto que indica la posición de la figura.
	 */
	public Point getPos();


	/**
	 * Inyectar la posición de la figura. Nota: Este método
	 * no mueve la figura, sólo cambia el punto de
	 * referencia respecto al cual calcular el resto de
	 * puntos. Puede afectar a la rotación y el escalado.
	 * 
	 * @param newPos un punto que indica el punto de
	 *        referencia de posición de la figura.
	 */
	public void setPos(Point newPos);


	/**
	 * Obtener el área de la figura.
	 * 
	 * @return el área.
	 */
	public double getArea();


	/**
	 * Desplazar la figura. Éste método actualiza la
	 * posición de la figura y el resto de puntos relativos
	 * a dicha posición.
	 * 
	 * @param x desplazamiento horizontal.
	 * @param y desplazamiento vertical.
	 */
	public void move(double x, double y);


	/**
	 * Desplazar la figura a un punto. Éste método actualiza
	 * la posición de la figura y el resto de puntos
	 * relativos a dicha posición.
	 * 
	 * @param aPoint el punto al que desplazar la posición.
	 */
	public void moveTo(Point aPoint);


	/**
	 * Rotar la figura. Los puntos rotan alrededor de la
	 * posición.
	 * 
	 * @param angleOffset el incremento respecto al ángulo
	 *        actual, en radianes.
	 */
	public void rotate(double angleOffset);


	/**
	 * Escalar la figura. Los puntos se escalan respecto al
	 * punto posición de la figura.
	 * 
	 * @param factor el factor de escalado respecto al
	 *        tamaño actual.
	 */
	public void scale(double factor);

}
