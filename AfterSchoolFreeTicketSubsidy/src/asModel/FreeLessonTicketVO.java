package asModel;

public class FreeLessonTicketVO {
	private String ticket_num;//자유수강번호
	private String stuNo; //학생번호
	private String class_num; //과목번호
	private int used_subsidy; //지원금 사용액
	private int left_subsidy; //지원금 잔액
	private int return_subsidy; //지원금 반환액
	private int stu_payment; //본인부담금
	private String year; //학년도
	
	public FreeLessonTicketVO() {
		super();
	}

	public FreeLessonTicketVO(String year) {
		super();
		this.year = year;
	}

	public String getTicket_num() {
		return ticket_num;
	}

	public void setTicket_num(String ticket_num) {
		this.ticket_num = ticket_num;
	}

	public String getStuNo() {
		return stuNo;
	}

	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}

	public String getClass_num() {
		return class_num;
	}

	public void setClass_num(String class_num) {
		this.class_num = class_num;
	}

	public int getUsed_subsidy() {
		return used_subsidy;
	}

	public void setUsed_subsidy(int used_subsidy) {
		this.used_subsidy = used_subsidy;
	}

	public int getLeft_subsidy() {
		return left_subsidy;
	}

	public void setLeft_subsidy(int left_subsidy) {
		this.left_subsidy = left_subsidy;
	}

	public int getReturn_subsidy() {
		return return_subsidy;
	}

	public void setReturn_subsidy(int return_subsidy) {
		this.return_subsidy = return_subsidy;
	}

	public int getStu_payment() {
		return stu_payment;
	}

	public void setStu_payment(int stu_payment) {
		this.stu_payment = stu_payment;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public FreeLessonTicketVO(String stuNo, int left_subsidy) {
		super();
		this.stuNo = stuNo;
		this.left_subsidy = left_subsidy;
	}

	
	
	

}
