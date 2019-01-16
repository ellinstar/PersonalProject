package asModel;

public class SubsidyVO {
	private int year; //학년도
	private int year_subsidy; //연간 지원금
	
	public SubsidyVO() {
		super();
	}

	public SubsidyVO(int year, int year_subsidy) {
		super();
		this.year = year;
		this.year_subsidy = year_subsidy;
	}

	public SubsidyVO(int year) {
		super();
		this.year = year;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getYear_subsidy() {
		return year_subsidy;
	}

	public void setYear_subsidy(int year_subsidy) {
		this.year_subsidy = year_subsidy;
	}

	
	

}
