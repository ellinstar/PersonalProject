package asControl;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.Optional;
import java.util.ResourceBundle;

import asDAO.SubjectDAO;
import asModel.SubjectVO;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

public class SubjectOpenController implements Initializable{
	@FXML private TextField txtSubNum; //강좌번호
	@FXML private TextField txtSubName; //강좌명 
	@FXML private CheckBox chClassA; //분반A
	@FXML private CheckBox chClassB; //분반B
	@FXML private CheckBox chClassC; //분반C
	@FXML private TextField txtTuition; //수강료
	@FXML private TextField txtWeek; //교육주수
	@FXML private TextField txtTextbook; //교재비
	@FXML private TextField txtMaterial; //재료비
	@FXML private DatePicker DpClassStart; //교육시작일
	@FXML private DatePicker DpClassEnd; //교육종료일
	@FXML private Button btnSubjectOpen; //강좌등록버튼
	@FXML private Button btnCancle; //취소버튼
	
	private String selectedCheckBoxes="";
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnSubjectOpen.setOnAction(event ->handlerBtnSubjectOpenAction(event));
		btnCancle.setOnAction(event -> handlerBtnCancleAction(event));
		
		DecimalFormat form = new DecimalFormat("###,###");
		txtTuition.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = form.parse(event.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 7) {
				return null;
			} else {
				return event;
			}

		}));
		txtTextbook.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = form.parse(event.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 7) {
				return null;
			} else {
				return event;
			}

		}));
		txtMaterial.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = form.parse(event.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 7) {
				return null;
			} else {
				return event;
			}

		}));
		txtWeek.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = form.parse(event.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 3) {
				return null;
			} else {
				return event;
			}

		}));
				
	}
	
	public void handlerBtnCancleAction(ActionEvent event) {
		Stage addStage = (Stage) btnCancle.getScene().getWindow();
		if (txtSubName.getText() != null || txtSubNum.getText() != null
				|| txtTuition.getText() != null || txtWeek.getText() != null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("정보확인!");
			alert.setHeaderText("기입된 정보가 있습니다!");
			alert.setContentText("입력된 내용이 삭제됩니다!");

			ButtonType btnOK = new ButtonType("예");
			ButtonType btnNO = new ButtonType("취소");
			alert.getButtonTypes().setAll(btnOK, btnNO);
			Optional<ButtonType> result = alert.showAndWait();

			if (result.get() == btnOK) {
				addStage.close();
			} else {

			}
		}
	}

	public void handlerBtnSubjectOpenAction(ActionEvent event) {
		
		if(chClassA.isSelected()) {
			selectedCheckBoxes += "A\t";
		}if(chClassB.isSelected()) {
			selectedCheckBoxes += "B\t";
		}if(chClassC.isSelected()) {
			selectedCheckBoxes += "C\t";
		}
		try {
		SubjectVO sjvo =null;
		SubjectDAO sjdao = new SubjectDAO();
		sjvo =  new SubjectVO(txtSubNum.getText(), txtSubName.getText(), selectedCheckBoxes,
				Integer.parseInt(txtTuition.getText()), Integer.parseInt(txtTextbook.getText()),Integer.parseInt(txtMaterial.getText()), Integer.parseInt(txtWeek.getText()),
				DpClassStart.getValue().toString(), DpClassEnd.getValue().toString());
		sjvo = sjdao.getSubjectInsert(sjvo);
		
		if(sjvo != null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("강좌등록 완료");
			alert.setHeaderText(txtSubName.getText() + "강좌가 등록되었습니다");
			alert.showAndWait();
			
		}
		}catch(Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("강좌 정보 누락");
			alert.setHeaderText("강좌 정보가 비어있습니다.");
			alert.setContentText("정보를 모두 입력하세요");
			alert.showAndWait();
		}
		selectedCheckBoxes="";
		
	}
	
	
	

}
