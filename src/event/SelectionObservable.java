
package event;




public interface SelectionObservable {

	public void addSelectObserver(SelectionObserver anObserver);


	public void removeSelectObserver(SelectionObserver anObserver);


	public void notifySelectObservers(SelectionEvent e);

}
