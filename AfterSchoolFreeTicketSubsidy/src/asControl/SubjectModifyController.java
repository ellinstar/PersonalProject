package asControl;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SubjectModifyController extends MainController implements Initializable  {
	@FXML private Label lasubNum;
	@FXML private TextField txtSubNameMod;
	@FXML private CheckBox chModA;
	@FXML private CheckBox chModB;
	@FXML private CheckBox chModC;
	@FXML private TextField txtTuitionMod;
	@FXML private Label laClassWeek;
	@FXML private TextField txtTextbookMod;
	@FXML private TextField txtMaterialMod;
	@FXML private Label laClassStart;
	@FXML private Label laClassEnd;
	@FXML private Button btnSubMod;
	@FXML private Button btnSubModClose;
	@FXML private Button btnSubX;
	
	int selectedindex;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		selectedindex = super.selectedIndex;
		
		
		
	}

}
