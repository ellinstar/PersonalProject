package asControl;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import asDAO.FreeTicketDAO;
import asDAO.StudentDAO;
import asModel.FreeLessonTicketVO;
import asModel.StudentVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

public class StudentAddController implements Initializable {
	
	
	//전입학생 등록
	@FXML
	private ComboBox<Integer> comGrade; // 학년
	private ObservableList<Integer> grade = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6);
	@FXML
	private ComboBox<String> comSection; // 가구자격
	private ObservableList<String> section;
	@FXML
	private TextField txtClass; // 반
	@FXML
	private TextField txtStuName;// 학생이름
	@FXML
	private TextField txtLeft; // 잔액
	@FXML
	private DatePicker date;// 전입일
	@FXML
	private Button btnAdd; // 등록
	@FXML
	private Button btnCancle; // 취소
	@FXML
	private Button btnIni; // 초기화

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		comGrade.setItems(grade); // 학년 콤보박스 값 연결
		section = FXCollections.observableArrayList("기초수급자", "차상위본인부담경감", "한부모보호", "차상위계층확인서발급", "차상위장애연금", "차상위장애수당",
				"차상위자활", "기타(소득수준조사대상)", "작년도지원대상");
		comSection.setItems(section); // 가구자격 콤보박스 값 연결
		date.setValue(LocalDate.now()); // 현재날짜로 기본 처리
		btnAdd.setOnAction(event -> handlerBtnAddAction(event));
		btnCancle.setOnAction(event -> handlerBtnCancleAction(event));
		btnIni.setOnAction(event -> handlerBtnIniAction(event));

		// 반 입력 형식, 숫자 2자리까지 입력 가능
		DecimalFormat form = new DecimalFormat("##");
		txtClass.setTextFormatter(new TextFormatter<>(event -> {
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
		// 지원금 잔액 입력형식, 숫자6자리까지 입력가능
		DecimalFormat form2 = new DecimalFormat("###,###");
		txtLeft.setTextFormatter(new TextFormatter<>(event -> {
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

	}// initiallize메소드 끝

	public void handlerBtnIniAction(ActionEvent event) {
		txtClass.clear();
		txtLeft.clear();
		txtStuName.clear();
		comGrade.getSelectionModel().select(null);
		comSection.getSelectionModel().select(null);
		date.setValue(LocalDate.now());
	}

	// 취소버튼
	public void handlerBtnCancleAction(ActionEvent event) {
		Stage addStage = (Stage) btnCancle.getScene().getWindow();
		if (comGrade.getSelectionModel().getSelectedItem() != null || txtClass.getText() != null
				|| txtStuName.getText() != null || txtLeft.getText() != null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("정보확인!");
			alert.setHeaderText("기입된 정보가 있습니다!");
			alert.setContentText("입력된 내용이 삭제됩니다!");

			ButtonType btnOK = new ButtonType("예");
			ButtonType btnNO = new ButtonType("취소");
			alert.getButtonTypes().setAll(btnOK, btnNO);
			Optional<ButtonType> result = alert.showAndWait();

			if (result.get() == btnOK) {
				handlerBtnIniAction(event);

				addStage.close();
			} else {

			}
		}

	}

	// 등록버튼
	public void handlerBtnAddAction(ActionEvent event) {
		try {
			StudentVO svo = null;
			StudentDAO sdao = new StudentDAO();
			svo = new StudentVO(comGrade.getSelectionModel().getSelectedItem(),
					Integer.parseInt(txtClass.getText().trim()), txtStuName.getText().trim(),
					comSection.getSelectionModel().getSelectedItem(), Integer.parseInt(txtLeft.getText().trim()),
					date.getValue().toString());
			svo = sdao.getStudentInsert(svo);
			
			FreeLessonTicketVO fvo = null;
			FreeTicketDAO fdao = new FreeTicketDAO();
			fvo = new FreeLessonTicketVO(txtLeft.getText());
			fvo = fdao.getLeftUpdate(svo.getStuNO(), fvo);
			
			if (svo != null) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("전입학생 입력");
				alert.setHeaderText(txtStuName.getText() + "학생이 등록되었습니다");
				alert.showAndWait();

				handlerBtnIniAction(event);
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("학생 정보 누락");
			alert.setHeaderText("학생 정보가 비어있습니다.");
			alert.setContentText("정보를 모두 입력하세요");
			alert.showAndWait();
		}

	}

}
