package asDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import asModel.FreeLessonTicketVO;
import asModel.SubsidyVO;

public class FreeTicketDAO {
//학년도 입력
	public  SubsidyVO getYearInsert(SubsidyVO subvo) {
		String dml = "insert into subsidy (year, year_subsidy) values (?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		SubsidyVO revo = null;
		try {
			con=DBUtill.getConnection();
			System.out.println("freeTicketDAO연결");
			pstmt = con.prepareStatement(dml);
			pstmt.setInt(1, subvo.getYear());
			pstmt.setInt(2, subvo.getYear_subsidy());
			int i = pstmt.executeUpdate();
			if(i ==1) {
				revo = new SubsidyVO();
				
			}
		}catch (SQLException e) {
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

	public FreeLessonTicketVO getLeftUpdate(String stuNo, FreeLessonTicketVO fvo ) {
		String dml = "Update freelessonTicket set left_subsidy =? where stuNo = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		FreeLessonTicketVO refvo = null;
		try {
			con = DBUtill.getConnection();
			pstmt = con.prepareStatement(dml);
			pstmt.setInt(1, fvo.getLeft_subsidy());
			pstmt.setString(2, stuNo);
			
			int i = pstmt.executeUpdate();
			if(i ==1) {
				refvo = new FreeLessonTicketVO();
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
		return refvo;
	}


	
	
}
