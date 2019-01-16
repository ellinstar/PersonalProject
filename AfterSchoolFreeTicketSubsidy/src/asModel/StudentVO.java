package asModel;

public class StudentVO {
	
	private String stuNO; //일련번호
	private int stu_grade; //학년
	private int stu_class; //반
	private String stu_num; //번호
	private String stu_name;//학생이름
	private String pa_name;//보호자 이름
	private String pa_phone;//보호자연락처
	private String section;//가구지원자격
	private int year_subsidy;//연간 지원금
	private String etc; //비고
	
	public String getStuNO() {
		return stuNO;
	}

	public void setStuNO(String stuNO) {
		this.stuNO = stuNO;
	}

	public StudentVO(String stuNO, int stu_grade, int stu_class, String stu_num, String stu_name, String pa_name,
			String pa_phone, String section, int year_subsidy, String etc) {
		super();
		this.stuNO = stuNO;
		this.stu_grade = stu_grade;
		this.stu_class = stu_class;
		this.stu_num = stu_num;
		this.stu_name = stu_name;
		this.pa_name = pa_name;
		this.pa_phone = pa_phone;
		this.section = section;
		this.year_subsidy = year_subsidy;
		this.etc = etc;
	}

	public int getStu_grade() {
		return stu_grade;
	}

	public void setStu_grade(int stu_grade) {
		this.stu_grade = stu_grade;
	}

	public int getStu_class() {
		return stu_class;
	}

	public void setStu_class(int stu_class) {
		this.stu_class = stu_class;
	}

	public String getStu_num() {
		return stu_num;
	}

	public void setStu_num(String stu_num) {
		this.stu_num = stu_num;
	}

	public String getStu_name() {
		return stu_name;
	}

	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}

	public String getPa_name() {
		return pa_name;
	}

	public void setPa_name(String pa_name) {
		this.pa_name = pa_name;
	}

	public String getPa_phone() {
		return pa_phone;
	}

	public void setPa_phone(String pa_phone) {
		this.pa_phone = pa_phone;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public int getYear_subsidy() {
		return year_subsidy;
	}

	public void setYear_subsidy(int year_subsidy) {
		this.year_subsidy = year_subsidy;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}
	
//메인 테이블뷰 출력내용 생성자
	public StudentVO(String stuNO, int stu_grade, int stu_class, String stu_name, String section, String etc) {
		super();
		this.stuNO = stuNO;
		this.stu_grade = stu_grade;
		this.stu_class = stu_class;
		this.stu_name = stu_name;
		this.section = section;
		this.etc = etc;
	}
	
//전입생등록 생성자
public StudentVO(int stu_grade, int stu_class, String stu_name, String section, int year_subsidy,
			String etc) {
		super();
		this.stu_grade = stu_grade;
		this.stu_class = stu_class;
		this.stu_name = stu_name;
		this.section = section;
		this.year_subsidy = year_subsidy;
		this.etc = etc;
	}

public StudentVO() {
	super();
}
//연간지원금 생성자
public StudentVO(int year_subsidy) {
	super();
	this.year_subsidy = year_subsidy;
}


	
	
	

}
