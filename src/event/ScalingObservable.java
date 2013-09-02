
package event;




public interface ScalingObservable {

	public void addScaleObserver(ScalingObserver anObserver);


	public void removeScaleObserver(ScalingObserver anObserver);


	public void notifyScaleObservers(ScalingEvent e);

}
