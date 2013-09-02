
package event;




public interface MovementObservable {

	public void addMoveObserver(MoveObserver anObserver);


	public void removeMoveObserver(MoveObserver anObserver);


	public void notifyMoveObservers(MoveEvent e);

}
