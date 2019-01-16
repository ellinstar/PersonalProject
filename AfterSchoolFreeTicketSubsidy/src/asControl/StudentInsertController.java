package asControl;

import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.Collection;
import java.util.ResourceBundle;

import org.apache.xmlbeans.impl.tool.Extension;

import asDAO.FreeTicketDAO;
import asModel.SubsidyVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;

public class StudentInsertController implements Initializable {
	//학생등록
		@FXML private TextField txtFile; //업로드파일 위치
		@FXML private Button btnFileSearch;//파일찾기
		@FXML private TextField txtYear; //학년도
		@FXML private TextField txtSubsidy; //연간 지원금
		@FXML private Button btnStuInsert;//학생목록등록
		private Window primaryStage;
		

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnFileSearch.setOnAction(event->handlerBtnFileSearchAction(event));
		//학년도 입력 형식
		DecimalFormat form = new DecimalFormat("####");
		txtYear.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = form.parse(event.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 5) {
				return null;
			} else {
				return event;
			}

		}));
		DecimalFormat form2 = new DecimalFormat("###,###");
		txtSubsidy.setTextFormatter(new TextFormatter<>(event -> {
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
		
		btnStuInsert.setOnAction(event->handlerBtnStuInsert(event));
	}


	public void handlerBtnFileSearchAction(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll((Collection<? extends ExtensionFilter>) new Extension());
		File SelectedFile = fileChooser.showOpenDialog(primaryStage);
		String selectedFilePath = SelectedFile.getPath();
		
	}


	public void handlerBtnStuInsert(ActionEvent event) {
		try {
		Stage inStage = (Stage) btnStuInsert.getScene().getWindow();
		SubsidyVO subvo = null;
		FreeTicketDAO fdao = new FreeTicketDAO();
		subvo = new SubsidyVO(Integer.parseInt(txtYear.getText()), Integer.parseInt(txtSubsidy.getText()));
		subvo = fdao.getYearInsert(subvo);
		if(subvo != null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("정보입력");
			alert.setHeaderText("학년도 연간지원금 입력이 완료되었습니다.");
			inStage.close();
		}
		}catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("정보 누락");
			alert.setHeaderText("정보가 비어있습니다.");
			alert.setContentText("정보를 모두 입력하세요");
			alert.showAndWait();
		}
		
		
		
		
		/*
		FreeLessonTicketVO fvo = null;
		FreeTicketDAO fdao = new FreeTicketDAO();
		fvo=new FreeLessonTicketVO(txtYear.getText());//학년도 입력
		fvo = fdao.getYearInsert(fvo);
		StudentVO svo = null;
		StudentDAO sdao = new StudentDAO();
		svo = new StudentVO(Integer.parseInt(txtSubsidy.getText()));//연간 지원금 입력
		svo = sdao.getSubsidyInsert(svo);*/
		
	}
	
}
