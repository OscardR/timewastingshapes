
package shape;


import java.io.Serializable;
import java.math.BigDecimal;


/**
 * AbstractPoint - Define una clase abstracta para usar
 * puntos en clases que necesiten trabajar con geometría.
 * Puede implementarse para puntos en el plano o en el
 * espacio. Proporciona métodos para obtener datos a partir
 * de las interacciones de éstos puntos.
 * 
 * @author Óscar Gómez Alcañiz
 */
public abstract class AbstractPoint implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 3587287793879380624L;

	protected double			x;
	protected double			y;
	protected double			z;
	protected double			calculatingResolution;
	protected double			displayResolution;


	public AbstractPoint() {
		setX(0);
		setY(0);
		setZ(0);
		setCalculatingResolution(100);
		setDisplayResolution(10);
	}


	/**
	 * Calcula la distancia entre dos puntos, en la misma
	 * unidad en la que estén definidas las coordenadas de
	 * éstos.
	 * 
	 * @param aPoint un punto cualquiera.
	 * @return un double que expresa la distancia entre los
	 *         puntos.
	 */
	public abstract double distanceTo(AbstractPoint aPoint);


	/**
	 * Calcula el punto que se encuentra geométricamente en
	 * el centro del segmento que une dos puntos.
	 * 
	 * @param aPoint un punto cualquiera.
	 * @return un nuevo punto que expresa el punto
	 *         intermedio entre los puntos.
	 */
	public abstract AbstractPoint mean(AbstractPoint aPoint);


	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}


	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = getCoordinateValue(x);
	}


	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}


	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = getCoordinateValue(y);
	}


	/**
	 * @return the z
	 */
	public double getZ() {
		return z;
	}


	/**
	 * @param z the z to set
	 */
	public void setZ(double z) {
		this.z = getCoordinateValue(z);
	}


	private double getCoordinateValue(double theValue) {
		BigDecimal coordinateValue = new BigDecimal(theValue);
		coordinateValue.setScale(2, BigDecimal.ROUND_HALF_UP);
		if (coordinateValue.doubleValue() < 1 / getCalculatingResolution()
				&& coordinateValue.doubleValue() > -1
						/ getCalculatingResolution())
			return 0;
		else return coordinateValue.doubleValue();
	}


	/**
	 * @return the calculatingResolution
	 */
	public double getCalculatingResolution() {
		return this.calculatingResolution;
	}


	/**
	 * @param resolution the resolution to set
	 */
	public void setCalculatingResolution(double resolution) {
		this.calculatingResolution = resolution;
	}


	/**
	 * @return the displayResolution
	 */
	public double getDisplayResolution() {
		return this.displayResolution;
	}


	/**
	 * @param displayResolution the displayResolution to set
	 */
	public void setDisplayResolution(double displayResolution) {
		this.displayResolution = displayResolution;
	}

}
