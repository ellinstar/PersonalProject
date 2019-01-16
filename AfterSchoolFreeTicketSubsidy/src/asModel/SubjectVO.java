package asModel;

import java.util.Date;

public class SubjectVO {
	private String class_num; //강좌번호
	private String class_name; //강좌명
	private String class_part; //강좌분반
	//private int class_personnel; //인원
	private int class_tuition; //수강료
	private int textbook_fee; //교재비
	private int material_cost; //재료비
	private int class_week; //교육주수
	private String class_start;//교육시작일
	private String class_end;//교육종료일
	
	public SubjectVO() {
		super();
	}
	//강좌등록 전체 생성자
public SubjectVO(String class_num, String class_name, String class_part, int class_tuition, int textbook_fee,
			int material_cost, int class_week, String class_start, String class_end) {
		super();
		this.class_num = class_num;
		this.class_name = class_name;
		this.class_part = class_part;
		this.class_tuition = class_tuition;
		this.textbook_fee = textbook_fee;
		this.material_cost = material_cost;
		this.class_week = class_week;
		this.class_start = class_start;
		this.class_end = class_end;
	}
//테이블뷰 생성자
	public SubjectVO(String class_num, String class_name, String class_part, int class_tuition,
			int textbook_fee, int material_cost) {
		super();
		this.class_num = class_num;
		this.class_name = class_name;
		this.class_part = class_part;
		this.class_tuition = class_tuition;
		this.textbook_fee = textbook_fee;
		this.material_cost = material_cost;
	}
	
	public String getClass_num() {
		return class_num;
	}
	public void setClass_num(String class_num) {
		this.class_num = class_num;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public String getClass_part() {
		return class_part;
	}
	public void setClass_part(String class_part) {
		this.class_part = class_part;
	}
	public int getClass_tuition() {
		return class_tuition;
	}
	public void setClass_tuition(int class_tuition) {
		this.class_tuition = class_tuition;
	}
	public int getTextbook_fee() {
		return textbook_fee;
	}
	public void setTextbook_fee(int textbook_fee) {
		this.textbook_fee = textbook_fee;
	}
	public int getMaterial_cost() {
		return material_cost;
	}
	public void setMaterial_cost(int material_cost) {
		this.material_cost = material_cost;
	}
	public int getClass_week() {
		return class_week;
	}
	public void setClass_week(int class_week) {
		this.class_week = class_week;
	}
	public String getClass_start() {
		return class_start;
	}
	public void setClass_start(String class_start) {
		this.class_start = class_start;
	}
	public String getClass_end() {
		return class_end;
	}
	public void setClass_end(String class_end) {
		this.class_end = class_end;
	}
	
	
	
}
