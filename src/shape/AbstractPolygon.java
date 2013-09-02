
package shape;


import java.util.List;


public abstract class AbstractPolygon implements Shape {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -6491862329670666639L;

	protected List<Point>		vertices;
	private Point				pos;


	/**
	 * Podemos consultar cuántos vértices tiene un polígono.
	 * 
	 * @return el número de vértices.
	 */
	public int getSize() {
		return vertices.size();
	}


	/**
	 * Método para añadir un vértice al polígono.
	 * 
	 * @param aPoint el punto a añadir.
	 */
	public void addVertex(Point aPoint) {
		getVertices().add(aPoint);
	}


	/**
	 * Obtener el vértice con el índice pasado como
	 * parámetro.
	 * 
	 * @param numVertex el número de vértice.
	 * @return el vértice en la posición especificada como
	 *         índice.
	 */
	public Point getVertex(int numVertex) {
		return getVertices().get(numVertex);
	}


	/**
	 * Podemos asignar un Punto directamente como vértice en
	 * la posición numVertex.
	 * 
	 * @param numVertex la posición en la lista de puntos.
	 * @param theVertex el objeto Point que representa dicho
	 *        vértice.
	 */
	public void setVertex(int numVertex, Point theVertex) {
		this.vertices.set(numVertex, theVertex);
	}


	/**
	 * Podemos obtener una lista con los vértices del
	 * polígono.
	 * 
	 * @return la lista de objetos Point que guardan los
	 *         vértices.
	 */
	public List<Point> getVertices() {
		return vertices;
	}


	/**
	 * Podemos sustituir una lista de puntos por otra,
	 * efectivamente 'convirtiendo' el polígono en otro
	 * polígono distinto.
	 * 
	 * @param points
	 */
	public void setVertices(List<Point> points) {
		this.vertices = points;
	}


	@Override
	public Point getPos() {
		return pos;
	}


	@Override
	public void setPos(Point newPos) {
		this.pos = new Point(newPos);
	}

}
