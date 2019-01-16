package asControl;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.poi.ss.formula.functions.Column;

import asDAO.SubjectDAO;
import asModel.StudentVO;
import asModel.SubjectVO;
import asModel.SubsidyVO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController implements Initializable {
	@FXML private TextField txtNameSearch; //검색학생이름 작성란
	@FXML private Button btnNameSearch; //학생이름검색 버튼
	@FXML private Button btnStu; //학생등록버튼
	@FXML private Button btnStuAdd; //전입학생 추가 버튼
	@FXML private Button btnHwpOut; //한글파일 출력 버튼
	@FXML private TableView<StudentVO> tbStudent = new TableView<>(); //학생 테이블뷰
	
	@FXML private static Label laYear; //학년도 출력 표시
	@FXML private static Label laYearSubsidy;//연간지원금 출력표시
	@FXML private Button btnClose; //프로그램 종료버튼
	
	@FXML private TextField txtSubSearch; //검색 강좌명 입력
	@FXML private Button btnSubSearch; //강좌 검색버튼
	@FXML private Button btnSubject; //강좌등록버튼
	@FXML private Button btnLessonlist; //수강생명단 출력버튼
	@FXML private TableView<SubjectVO> tbSubject = new TableView<>(); //강좌테이블뷰
	@FXML private TableColumn<SubjectVO, String> colClassNum;
	@FXML private TableColumn<SubjectVO, String> colClassName;
	@FXML private TableColumn<SubjectVO, String> colClassPart;
	@FXML private TableColumn<SubjectVO, Integer> colPersonnel;
	@FXML private TableColumn<SubjectVO, Integer> colTuition;
	@FXML private TableColumn<SubjectVO, Integer> colTextbook;
	@FXML private TableColumn<SubjectVO, Integer> colMaterial;

	
	int selectedindex; //테이블에서 선택된 인덱스 저장
	
	StudentVO stu = new StudentVO();
	ObservableList<StudentVO> data = FXCollections.observableArrayList();//학생 테이블
	ObservableList<StudentVO> selectStudent;//테이블 선택 정보 저장
	ObservableList<SubjectVO> subData = FXCollections.observableArrayList();
	SubsidyVO subvo = new SubsidyVO();
	ObservableList<SubsidyVO> ydata = FXCollections.observableArrayList();
	
	boolean editD = false;//수정할 때 확인 버튼 상태 설정
	int selectedIndex; //테이블 선택한 학생 정보 인덱스 저장
	int no; //삭제시 테이블에서 선택한 학생의 번호저장
	File selectedFile = null;
	private Stage PrimaryStage;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnNameSearch.setDisable(false);
		//tbStudent.setItems(data);
		//totalList(); //학생 전체 정보
		//학생관리탭
		txtNameSearch.setOnKeyPressed(event-> handlerTxtNameSearchKeyPressed(event));
		btnNameSearch.setOnAction(event->handlerBtnNameSearchAction(event));
		btnStu.setOnAction(event->handlerBtnStuAction(event));
		btnStuAdd.setOnAction(event->handlerBtnStuAddAction(event));
		btnHwpOut.setOnAction(event->handlerBtnHwpOutAction(event));
		tbStudent.setOnMouseClicked(event->handlerStuInfoAction(event));
		btnClose.setOnAction(event->{Platform.exit();});
		//강좌관리 탭
		txtSubSearch.setOnKeyPressed(event -> handlerTxtSubSearchKeyPressed(event));
		btnSubSearch.setOnAction(event -> handlerBtnSubSearchAction(event));
		btnSubject.setOnAction(event -> handlerBtnSubjectAction(event));
		btnLessonlist.setOnAction(event -> handlerBtnLessonListAction(event));
		tbSubject.setOnMouseClicked(event->handlerSubjectModifyAction(event));
		tbSubject.setItems(subData);
		totalSubList();
		
		

	}
	
	public void totalSubList() {
		Object[][] totalSubData;
		SubjectDAO sjdao = new SubjectDAO();
		SubjectVO sjvo = null;
		ArrayList<SubjectVO> list;
		
		list = sjdao.getSubList();
		int rowCount = list.size();
		totalSubData = new Object[rowCount][6];
		
		
		for(int index = 0; index<rowCount; index++) {
			sjvo = list.get(index);
//			subData.add(sjvo);
			colClassNum.setUserData(sjvo.getClass_num());
			
		}
		
	}
	
	//테이블에서 강좌 선택시 변경창
public void handlerSubjectModifyAction(MouseEvent event) {
	try {
		Parent root = FXMLLoader.load(getClass().getResource("/asView/SubjectModifyView.fxml"));
		Scene scene = new Scene(root);
		Stage mainStage = new Stage();
		mainStage.setTitle("학생세부정보");
		mainStage.setScene(scene);
		mainStage.setResizable(false);
		mainStage.show();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	}
//강좌 수강생명단 엑셀파일 출력
public void handlerBtnLessonListAction(ActionEvent event) {
		
	}
//강좌개설 등록버튼
public void handlerBtnSubjectAction(ActionEvent event) {
	try {
		Parent root = FXMLLoader.load(getClass().getResource("/asView/SubjectOpenView.fxml"));
		Scene scene = new Scene(root);
		Stage mainStage = new Stage();
		mainStage.setTitle("강좌정보 수정");
		mainStage.setScene(scene);
		mainStage.setResizable(false);
		mainStage.show();
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
//강좌검색
public void handlerBtnSubSearchAction(ActionEvent event) {
		
	}
//강좌검색 입력 후 엔터키->바로 검색가능
public void handlerTxtSubSearchKeyPressed(KeyEvent event) {
	if(event.getCode() == KeyCode.ENTER) {
		handlerBtnSubSearchAction(null);
	}
	}
//테이블에서 학생 선택시 세부정보 창
	public void handlerStuInfoAction(MouseEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/asView/StudentInfoView.fxml"));
			Scene scene = new Scene(root);
			Stage mainStage = new Stage();
			mainStage.setTitle("학생세부정보");
			mainStage.setScene(scene);
			mainStage.setResizable(false);
			mainStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
//전입생 등록버튼
	public void handlerBtnStuAddAction(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/asView/StudentAddView.fxml"));
			Scene scene = new Scene(root);
			Stage add = new Stage(StageStyle.DECORATED);
			add.setTitle("전입학생등록");
			add.setScene(scene);
			add.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//학생 등록 버튼
	public void handlerBtnStuAction(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/asView/StudentInsertView.fxml"));
			Scene scene = new Scene(root);
			Stage mainStage = new Stage();
			mainStage.setTitle("학생전체등록");
			mainStage.setScene(scene);
			mainStage.setResizable(false);
			mainStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

//이름 검색 버튼
public void handlerBtnNameSearchAction(ActionEvent event) {
	/*	StudentVO svo = new StudentVO();
		//StudentDAO sdao = null;
		
		Object[][] totalData=null;
		
		String searchName = "";
		boolean searchResult = false;
		
		try {
			searchName = txtNameSearch.getText().trim();
		//	sdao = new StudentDAO();
		//	svo = sdao.getStudentCheck(searchName);
			if (searchName.equals("")) {
				searchResult = true;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("학생 정보 검색");
				alert.setHeaderText("학생의 이름을 입력하세요");
				alert.setContentText("검색할 이름이 없습니다");
				alert.showAndWait();
			}
			if (!searchName.equals("") && (svo != null)) {
				ArrayList<String> title;
				ArrayList<StudentVO> list;
			//	title = sdao.getColumnName();
			//	int columnCount = title.size();

			//	list = sdao.getStudentTotal();
			//	int rowCount = list.size();

			// = new Object[rowCount][columnCount];
			
				if (svo.getStu_name().equals(searchName)) {
				txtNameSearch.clear();
				data.removeAll(data);
				for (int index = 0; index < rowCount; index++) {
					System.out.println(index);
					svo = list.get(index);
					if (svo.getStu_name().equals(searchName)) {
						data.add(svo);
						searchResult = true;
					}
				}
			}
		}
			if (!searchResult) {
				txtNameSearch.clear();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("학생 정보 검색");
				alert.setHeaderText(searchName + "학생이 리스트에 없습니다.");
				alert.setContentText("이름을 다시 입력해주세요.");
				alert.showAndWait();
			}

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("학생 점수 수정");
			alert.setHeaderText("점수를 입력하세요");
			alert.setContentText("학생 정보가 비어있습니다!");
			alert.showAndWait();

		}	*/
		
		
	}
	//학생이름 검색시 텍스트박스에서 엔터키 누르면 검색처리
	public void handlerTxtNameSearchKeyPressed(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER) {
			handlerBtnNameSearchAction(null);
		}
	}
//한글파일 출력
	public void handlerBtnHwpOutAction(ActionEvent event) {
		//파일 저장 폴더 선택
		final DirectoryChooser dirChooser = new DirectoryChooser();
		final File selectedDir = dirChooser.showDialog(PrimaryStage);
		if(selectedDir != null) {
			btnHwpOut.setDisable(false);
		}
		
	}
	

}
