package asDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import asModel.StudentVO;

public class StudentDAO {
//전입학생 정보 데이터베이스에 등록
	public StudentVO getStudentInsert(StudentVO svo) {
		String dml = "insert into student (stuNo, stu_grade, stu_class, stu_name, section, etc)"
				+" values (to_char(sysdate,'YYYY')||LPAD(student_seq.nextval,3,'0'), ?, ?, ?, ?, sysdate ||'전입생')";
		Connection con = null; //데이터베이스와 개발환경 사이의 연결을 제공
		PreparedStatement pstmt = null; //미리 컴파일 된  SQL문을 즉시 실행하는 기능
		StudentVO revo = null;
		try {
			con=DBUtill.getConnection();
			System.out.println("DAO연결");
			pstmt = con.prepareStatement(dml);
			pstmt.setInt(1, svo.getStu_grade());
			pstmt.setInt(2, svo.getStu_class());
			pstmt.setString(3, svo.getStu_name());
			pstmt.setString(4, svo.getSection());
			
			int i = pstmt.executeUpdate();
			if(i ==1) {
				revo = new StudentVO();
			}
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}
		}
		return revo;
	}

	public StudentVO getSubsidyInsert(StudentVO svo) {
		String dml = "update Student set year_subsidy=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		StudentVO resvo = null;
		try {
			con=DBUtill.getConnection();
			pstmt = con.prepareStatement(dml);
			pstmt.setInt(1, svo.getYear_subsidy());
			
			int i = pstmt.executeUpdate();
			if(i==1) {
				resvo = new StudentVO();
				
			}
		}catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {// 데이터베이스와의 연결에 사용되었던 오브젝트 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return resvo;
	}

}
