
package shape;


import java.util.LinkedList;


public class Polygon extends AbstractPolygon {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 9195233626241203444L;


	public Polygon() {
		this.vertices = new LinkedList<Point>();
	}


	/**
	 * Devuelve un rectángulo en el que está contenido el
	 * polígono.
	 */
	public Rectangle boundingBox() {

		double left = getVertex(0).getX();
		double top = getVertex(0).getY();
		double right = left;
		double bottom = top;

		for (Point point : getVertices()) {

			if (point.getX() < left)
				left = point.getX();
			else if (point.getX() > right)
				right = point.getX();
			if (point.getY() < top)
				top = point.getY();
			else if (point.getY() > bottom)
				bottom = point.getY();

		}

		return new Rectangle(left, top, right, bottom);
	}


	/**
	 * <p>
	 * Método contains. Implementación JAVA del algoritmo
	 * <strong>PNPOLY Copyright (c) 1970-2003, Wm. Randolph
	 * Franklin</strong>
	 * </p>
	 * <p>
	 * Permission is hereby granted, free of charge, to any
	 * person obtaining a copy of this software and
	 * associated documentation files (the "Software"), to
	 * deal in the Software without restriction, including
	 * without limitation the rights to use, copy, modify,
	 * merge, publish, distribute, sublicense, and/or sell
	 * copies of the Software, and to permit persons to whom
	 * the Software is furnished to do so, subject to the
	 * following conditions: Redistributions of source code
	 * must retain the above copyright notice, this list of
	 * conditions and the following disclaimers.
	 * Redistributions in binary form must reproduce the
	 * above copyright notice in the documentation and/or
	 * other materials provided with the distribution. The
	 * name of W. Randolph Franklin may not be used to
	 * endorse or promote products derived from this
	 * Software without specific prior written permission.
	 * </p>
	 * <p>
	 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF
	 * ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
	 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
	 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO
	 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
	 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
	 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
	 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE
	 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	 * SOFTWARE.
	 * </p>
	 * 
	 * @author W. Randolph Franklin (WRF)
	 */
	@Override
	public boolean contains(Point thePoint) {

		int numVertices = getSize();

		double[] xVector = new double[numVertices];
		double[] yVector = new double[numVertices];

		int i = 0;
		for (Point aPoint : getVertices()) {
			xVector[i] = aPoint.getX();
			yVector[i] = aPoint.getY();
			i++;
		}

		boolean contained = false;
		i = 0;
		int j = numVertices - 1;
		while (i < numVertices) {
			if (((yVector[i] > thePoint.getY()) != (yVector[j] > thePoint.getY()))
					&& (thePoint.getX() < (xVector[j] - xVector[i])
							* (thePoint.getY() - yVector[i])
							/ (yVector[j] - yVector[i]) + xVector[i]))
				contained = !contained;
			j = i++;
		}
		return contained;
	}


	@Override
	public void move(double offsetX, double offsetY) {

		setPos(new Point(getPos().getX() + offsetX, getPos().getY() + offsetY));

		for (Point p : getVertices()) {

			p.setX(p.getX() + offsetX);
			p.setY(p.getY() + offsetY);

		}
	}


	@Override
	public void moveTo(Point p) {

		double distX = p.getX() - getPos().getX();
		double distY = p.getY() - getPos().getY();

		move(distX, distY);
	}


	@Override
	public void scale(double factor) {

		// Nos guardamos la posición actual:
		Rectangle oldBoundingBox = boundingBox();

		// Recalculamos los vértices uno a uno...
		for (Point v : getVertices()) {

			v.setX(v.getX() * factor);
			v.setY(v.getY() * factor);
		}

		// Incluido el punto de posición
		getPos().setX(getPos().getX() * factor);
		getPos().setY(getPos().getY() * factor);

		// ...y corregimos el desplazamiento
		Rectangle newBoundingBox = boundingBox();

		move(
			oldBoundingBox.getPos().getX() - newBoundingBox.getPos().getX(),
			oldBoundingBox.getPos().getY() - newBoundingBox.getPos().getY());
	}


	@Override
	public String toString() {
		return "Polygon";
	}


	@Override
	public boolean equals(Object anObject) {
		if (anObject == this) {
			return true;
		} else if (anObject == null) {
			return false;
		} else if (!anObject.getClass().getCanonicalName().equals(
			getClass().getCanonicalName())) {
			// System.out.println("anObject: "
			// + anObject.getClass().getCanonicalName());
			return false;
		}

		Polygon aPolygon = (Polygon) anObject;

		for (int i = 0; i < getSize(); i++) {
			// System.out.print("Vértice " + i +
			// ":\tthis>\t" + getVertex(i)
			// + "\t\tother>\t" + aPolygon.getVertex(i));
			if (!getVertex(i).equals(aPolygon.getVertex(i))) {
				// System.out.print(" - NO!\n");
				return false;
			} else {
				// System.out.print(" - OK\n");
			}
		}

		return true;
	}


	public void rotate(double angleIncrement) {

		// System.out.println("Polygon#rotate:");
		for (int i = 0; i < getVertices().size(); i++) {
			// System.out.print("Rotar " + angleIncrement +
			// " rad. el vértice "
			// + getVertex(i) + ": ");

			double distance = getPos().distanceTo(getVertex(i));
			double currentAngle = getPos().angleTo(getVertex(i));
			currentAngle = currentAngle + angleIncrement;

			Point nuevoVertice = new Point(getPos().getX() + distance
					* Math.cos(currentAngle), getPos().getY() + distance
					* Math.sin(currentAngle));

			getVertex(i).setX(nuevoVertice.getX());
			getVertex(i).setY(nuevoVertice.getY());

			// System.out.print(getVertex(i) + "\n");
		}
	}


	/**
	 * Método para calcular el área. Se basa en cortar el
	 * polígono en 'rebanadas' horizontales, calcular el
	 * área de cada corte del polígono por separado, sumando
	 * las de bajada y restando las de subida. {@link http
	 * ://www.mathopenref.com/coordpolygonarea2.html}
	 */
	@Override
	public double getArea() {
		int numVertices = getSize();

		double[] xVector = new double[numVertices];
		double[] yVector = new double[numVertices];

		int i = 0;
		for (Point aPoint : getVertices()) {
			xVector[i] = aPoint.getX();
			yVector[i] = aPoint.getY();
			i++;
		}

		// Accumulates area in the loop
		double area = 0;
		// The last vertex is the
		// 'previous' one to the
		// first
		int j = numVertices - 1;

		for (i = 0; i < numVertices; i++) {
			area = area + (xVector[j] + xVector[i]) * (yVector[j] - yVector[i]);
			j = i;  // j is previous vertex to i
		}
		return area / 2;
	}

}
