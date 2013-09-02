
package app;


import javax.swing.JComponent;


public interface View extends ViewForController, ViewForModel {

	int getHorizontalSliderValue();


	int getVerticalSliderValue();


	void setController(Controller controller);


	JComponent getComponent();

}
