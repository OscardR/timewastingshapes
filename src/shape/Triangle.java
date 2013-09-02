
package shape;


import java.math.BigDecimal;
import java.util.HashMap;


/**
 * Triangle - Clase que sirve para representar triángulos de
 * todo tipo.
 * 
 * @author Óscar Gómez Alcañiz
 */
public class Triangle extends Polygon implements Shape {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 5160971775029751965L;


	/**
	 * enum CenterType. Sirve para especificar un tipo de
	 * centro cuando solicitemos uno de los centros del
	 * triángulo.
	 * 
	 * @author Óscar
	 */
	public enum CenterType {
		INCENTER("Incenter"),
		ORTHOCENTER("Orthocenter"),
		CIRCUMCENTER("Circumcenter"),
		CENTROID("Centroid");

		public final String	name;


		/**
		 * Lo inicializamos con un nombre, para poder luego
		 * escribir por pantalla datos interesantes sobre
		 * las operaciones realizadas.
		 * 
		 * @param aName el String que define cada tipo de
		 *        centro.
		 */
		CenterType(String aName) {
			this.name = aName;
		}
	}


	/**
	 * enum TriangleType. Sirve para especificar el tipo de
	 * triángulo que estamos representando. Es importante
	 * saberlo para hacer cálculos de áreas, centros, etc...
	 * 
	 * @author Óscar
	 */
	private enum TriangleType {
		EQUILATERAL("Equilateral"),
		ISOSCELES("Isosceles"),
		ESCALENE("Escalene");

		public final String	name;


		/**
		 * Una vez más, se inicializa con una cadena para
		 * poder formatear la salida de los mensajes de
		 * debug.
		 * 
		 * @param aName el String que define cada tipo.
		 */
		TriangleType(String aName) {
			this.name = aName;
		}
	}


	/**
	 * Constructor de triángulos escalenos
	 * 
	 * @param pA primer vértice
	 * @param pB segundo vértice
	 * @param pC tercer vértice
	 */
	public Triangle(Point pA, Point pB, Point pC) {

		this.A = pA;
		this.B = pB;
		this.C = pC;

		init(TriangleType.ESCALENE);
	}


	/**
	 * Constructor de triángulos isósceles.
	 * 
	 * @param c Un punto que representa el INCENTRO del
	 *        triángulo
	 * @param base Dimensiones de la base
	 * @param height Dimensiones de la altura
	 */
	public Triangle(Point inCenter, double base, double height) {

		setBase(base);
		setHeight(height);

		this.A = new Point(
			inCenter.getX() - base / 2,
			-calculateInradius(TriangleType.ISOSCELES));
		this.B = new Point(
			inCenter.getX() + base / 2,
			-calculateInradius(TriangleType.ISOSCELES));
		this.C = new Point(inCenter.getX(), height
				- calculateInradius(TriangleType.ISOSCELES));

		init(TriangleType.ISOSCELES);
	}


	/**
	 * Triángulos equiláteros
	 * 
	 * @param c Un punto que representa el INCENTRO del
	 *        triángulo
	 * @param side La longitud del lado del triángulo
	 */
	public Triangle(Point inCenter, double side) {

		setSide(side);

		this.A = new Point(
			inCenter.getX() - side / 2,
			-calculateInradius(TriangleType.EQUILATERAL));
		this.B = new Point(
			inCenter.getX() + side / 2,
			-calculateInradius(TriangleType.EQUILATERAL));
		this.C = new Point(inCenter.getX(), side * Math.sqrt(3) / 2
				- calculateInradius(TriangleType.EQUILATERAL));

		init(TriangleType.EQUILATERAL);
	}


	// @Override
	// public boolean contains(Point p) {
	// /*
	// * Un punto está contenido en un triángulo si las
	// * áreas sumadas de todos los triángulos que se
	// * pueden formar con el punto y los vértices de
	// * nuestro triángulo suman un área inferior al área
	// * del triángulo que estamos comprobando.
	// */
	// double currentArea = getArea();
	// double totalArea = new Triangle(A, B, p).getArea()
	// + new Triangle(B, C, p).getArea()
	// + new Triangle(C, A, p).getArea();
	// return currentArea <= totalArea;
	// }

	// @Override
	// public double getArea() {
	// System.out.println("Area AB*h/2: " + AB.getLength() *
	// heights.get(AB));
	// System.out.println("Area BC*h/2: " + BC.getLength() *
	// heights.get(BC));
	// System.out.println("Area CA*h/2: " + CA.getLength() *
	// heights.get(CA));
	// return Math.abs(AB.getLength() * heights.get(AB) /
	// 2);
	// }

	public Point getCenter(CenterType type) {
		switch (type) {
		case CIRCUMCENTER:
			return getCircumCenter();
		case INCENTER:
			return getInCenter();
		default:
			return getPos();
		}
	}


	public double getHeight(Segment aSegment) {
		return heights.get(aSegment);
	}


	/**
	 * @return the inradius
	 */
	public double getInradius() {
		return inradius;
	}


	/**
	 * @param theRadius the inradius to set
	 */
	public void setInradius(double theRadius) {
		this.inradius = theRadius;
	}


	public double getCircumRadius() {
		return circumradius;
	}


	public void setCircumradius(double theRadius) {
		this.circumradius = theRadius;
	}


	@Override
	public void move(double offsetX, double offsetY) {
		super.move(offsetX, offsetY);
		setCenterAndRadius();
	}


	@Override
	public void moveTo(Point aPoint) {
		super.moveTo(aPoint);
		setCenterAndRadius();
	}


	@Override
	public void rotate(double angleOffset) {
		super.rotate(angleOffset);
		setCenterAndRadius();
	}


	@Override
	public boolean equals(Object s) {

		Triangle otro = (Triangle) s;

		double dA = otro.A.distanceTo(this.A);
		double dB = otro.B.distanceTo(this.B);
		double dC = otro.C.distanceTo(this.C);

		return dA < 0.01 && dB < 0.01 && dC < 0.01;
	}


	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();

		sb.append(super.toString());
		sb.append(".Triangle<");
		sb.append(this.triangleType.name);
		sb.append("> - Points{");

		for (int p = 0; p < getSize(); p++) {
			sb.append(getVertex(p).toString());
			if (p < getSize() - 1)
				sb.append(", ");
		}

		sb.append("} Dimensions{ base: ");
		sb.append(BigDecimal.valueOf(AB.getLength()).setScale(
			2,
			BigDecimal.ROUND_HALF_UP));
		sb.append(", height: ");
		sb.append(BigDecimal.valueOf(heights.get(AB)).setScale(
			2,
			BigDecimal.ROUND_HALF_UP));
		sb.append(" }");

		return sb.toString();
	}


	/*
	 * Hay varios puntos centrales. Más info: {@link
	 * http://en.wikipedia.org/wiki/Triangle}
	 */

	// private Point getOrthocenter() {
	// TODO
	// return new Point(0, 0);
	// }
	//
	//
	// private Point getCentroid() {
	// TODO
	// return new Point(0, 0);
	// }

	/**
	 * @return the base
	 */
	private double getBase() {
		return this.base;
	}


	/**
	 * @param base the base to set
	 */
	private void setBase(double base) {
		this.base = base;
	}


	/**
	 * @return the height
	 */
	private double getHeight() {
		return this.height;
	}


	/**
	 * @param height the height to set
	 */
	private void setHeight(double height) {
		this.height = height;
	}


	/**
	 * @return the side
	 */
	private double getSide() {
		return this.side;
	}


	/**
	 * @param side the side to set
	 */
	private void setSide(double side) {
		this.side = side;
	}


	/**
	 * Obetener el centro del círculo que pasa por los tres
	 * vértices del triángulo. Más información en:
	 * {@link http
	 * ://en.wikipedia.org/wiki/Circumscribed_circle
	 * #Coordinates_of_a_triangle.27s_circumcenter}
	 * 
	 * @return el punto que representa el circuncentro.
	 */
	private Point getCircumCenter() {
		double diameter = 2 * (A.getX() * (B.getY() - C.getY()) + B.getX()
				* (C.getY() - A.getY()) + C.getX() * (A.getY() - B.getY()));
		double cX = ((Math.pow(A.getX(), 2) + Math.pow(A.getY(), 2))
				* (B.getY() - C.getY())
				+ (Math.pow(B.getX(), 2) + Math.pow(B.getY(), 2))
				* (C.getY() - A.getY()) + (Math.pow(C.getX(), 2) + Math.pow(
			C.getY(),
			2))
				* (A.getY() - B.getY()))
				/ diameter;
		double cY = ((Math.pow(A.getX(), 2) + Math.pow(A.getY(), 2))
				* (C.getX() - B.getX())
				+ (Math.pow(B.getX(), 2) + Math.pow(B.getY(), 2))
				* (A.getX() - C.getX()) + (Math.pow(C.getX(), 2) + Math.pow(
			C.getY(),
			2))
				* (B.getX() - A.getX()))
				/ diameter;
		return new Point(cX, cY);
	}


	/**
	 * Obtener el incentro del triángulo.
	 * 
	 * @return el punto que representa el incentro.
	 */
	private Point getInCenter() {

		double perimeter = AB.getLength() + BC.getLength() + CA.getLength();

		return new Point(
			(AB.getLength() * A.getX() + BC.getLength() * B.getX() + CA.getLength()
					* C.getX())
					/ perimeter,
			(AB.getLength() * A.getY() + BC.getLength() * B.getY() + CA.getLength()
					* C.getY())
					/ perimeter);
	}


	/**
	 * calcula el inradio en función del tipo de triángulo.
	 * 
	 * @return la distancia que hay desde cualquier lado al
	 *         centro de la circunferencia circunscrita.
	 */
	private double calculateInradius(TriangleType theType) {

		switch (theType) {
		case EQUILATERAL:
			return getSide() * Math.sqrt(3) / 6;

		case ISOSCELES:
			double a = Math.sqrt(getBase() * getBase() / 4 + getHeight()
					* getHeight());
			double b = getBase();
			double inr = b * Math.sqrt((2 * a - b) / (2 * a + b)) / 2;
			return inr;

		case ESCALENE:
		default:
			return getArea()
					/ ((A.distanceTo(B) + B.distanceTo(C) + C.distanceTo(A)) / 2);
		}
	}


	private double calculateInradius() {
		return calculateInradius(this.triangleType);
	}


	/**
	 * Calcular el circumradio.
	 * 
	 * @return el radio del círculo circunstrito en el
	 *         triángulo.
	 */
	private double calculateCircumradius() {
		return AB.getLength()
				* BC.getLength()
				* CA.getLength()
				/ Math.sqrt((AB.getLength() + BC.getLength() + CA.getLength())
						* (AB.getLength() + BC.getLength() - CA.getLength())
						* (AB.getLength() - BC.getLength() + CA.getLength())
						* (-AB.getLength() + BC.getLength() + CA.getLength()));
	}


	/**
	 * Método para inicializar la lista de puntos y la
	 * posición del triángulo
	 * 
	 * @param inCenter El incentro del triángulo, que
	 *        usaremos para posicionarlo
	 */
	private void init(TriangleType type) {

		this.triangleType = type;

		this.addVertex(A);
		this.addVertex(B);
		this.addVertex(C);

		this.AB = new Segment(A, B);
		this.BC = new Segment(B, C);
		this.CA = new Segment(C, A);

		this.heights.put(AB, Math.abs(B.distanceTo(C) * Math.sin(B.angleTo(C))));
		this.heights.put(BC, Math.abs(C.distanceTo(A) * Math.sin(C.angleTo(A))));
		this.heights.put(CA, Math.abs(A.distanceTo(B) * Math.sin(A.angleTo(B))));

		setCenterAndRadius();
	}


	private void setCenterAndRadius() {
		setInradius(calculateInradius());
		setCircumradius(calculateCircumradius());
		setPos(getCircumCenter());
	}


	private HashMap<Segment, Double>	heights	= new HashMap<Segment, Double>();
	private double						base;
	private double						height;

	private double						side;
	private double						inradius;
	private double						circumradius;
	private TriangleType				triangleType;

	private Point						A;
	private Point						B;
	private Point						C;

	private Segment						AB;
	private Segment						BC;
	private Segment						CA;
}
