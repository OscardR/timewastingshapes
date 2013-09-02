
package event;




public interface RotationObservable {

	public void addRotationObserver(RotationObserver anObserver);


	public void removeRotationObserver(RotationObserver anObserver);


	public void notifyRotationObservers(RotationEvent e);
}
