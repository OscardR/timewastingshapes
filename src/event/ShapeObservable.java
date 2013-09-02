
package event;


import java.util.HashSet;


/**
 * Esta interfaz proporciona métodos para añadir
 * observadores para los distintos eventos que puedan darse
 * a través de la GUI. Por ejemplo, para notificar a las
 * clases que forman parte del modelo de estos cambios.
 * 
 * @author oscar
 */
public abstract class ShapeObservable implements MovementObservable,
	ScalingObservable, RotationObservable, SelectionObservable {

	private HashSet<SelectionObserver>	selectObservers;
	private HashSet<MoveObserver>		moveObservers;
	private HashSet<ScalingObserver>	scaleObservers;
	private HashSet<RotationObserver>	rotationObservers;


	public ShapeObservable() {
		selectObservers = new HashSet<SelectionObserver>();
		moveObservers = new HashSet<MoveObserver>();
		scaleObservers = new HashSet<ScalingObserver>();
		rotationObservers = new HashSet<RotationObserver>();
	}


	public void addSelectObserver(SelectionObserver o) {
		selectObservers.add(o);
	}


	public void removeSelectObserver(SelectionObserver o) {
		selectObservers.remove(o);
	}


	public void notifySelectObservers(SelectionEvent e) {
		for (SelectionObserver o : selectObservers)
			o.selectionPerformed(e);
	}


	public void addMoveObserver(MoveObserver anObserver) {
		moveObservers.add(anObserver);
	}


	public void removeMoveObserver(MoveObserver anObserver) {
		moveObservers.remove(anObserver);
	}


	public void notifyMoveObservers(MoveEvent e) {
		for (MoveObserver o : moveObservers)
			o.movementPerformed(e);
	}


	public void addScaleObserver(ScalingObserver anObserver) {
		scaleObservers.add(anObserver);
	}


	public void removeScaleObserver(ScalingObserver anObserver) {
		scaleObservers.remove(anObserver);
	}


	public void notifyScaleObservers(ScalingEvent e) {
		for (ScalingObserver o : scaleObservers)
			o.scalingPerformed(e);
	}


	@Override
	public void addRotationObserver(RotationObserver anObserver) {
		rotationObservers.add(anObserver);
	}


	@Override
	public void removeRotationObserver(RotationObserver anObserver) {
		rotationObservers.remove(anObserver);
	}


	@Override
	public void notifyRotationObservers(RotationEvent e) {
		for (RotationObserver o : rotationObservers)
			o.rotationPerformed(e);
	}

}
