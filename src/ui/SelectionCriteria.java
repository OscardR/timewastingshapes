
package ui;


import java.io.Serializable;

import shape.Point;
import shape.Shape;


public interface SelectionCriteria extends Serializable {

	public boolean select(Point p, Shape s);
}
