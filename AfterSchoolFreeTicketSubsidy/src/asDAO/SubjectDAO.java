package asDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import asModel.SubjectVO;

public class SubjectDAO {

	public SubjectVO getSubjectInsert(SubjectVO sjvo) {
		String dml = "insert into afterschool_class (class_num, class_name, class_part, class_tuition, textbook_fee, material_cost, class_week, class_start, class_end) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		SubjectVO resjvo = null;
		try {
			con = DBUtill.getConnection();
			System.out.println("sjDAO연결");
			pstmt = con.prepareStatement(dml);
			pstmt.setString(1, sjvo.getClass_num());
			pstmt.setString(2, sjvo.getClass_name());
			pstmt.setString(3, sjvo.getClass_part());
			pstmt.setInt(4, sjvo.getClass_tuition());
			pstmt.setInt(5, sjvo.getTextbook_fee());
			pstmt.setInt(6, sjvo.getMaterial_cost());
			pstmt.setInt(7, sjvo.getClass_week());
			pstmt.setString(8, sjvo.getClass_start());
			pstmt.setString(9, sjvo.getClass_end());

			int i = pstmt.executeUpdate();
			if (i == 1) {
				resjvo = new SubjectVO();
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
		return resjvo;
	}

	public ArrayList<SubjectVO> getSubList() {
		ArrayList<SubjectVO> list = new ArrayList<SubjectVO>();
		String tml = "select class_num, class_name, class_part, class_tuition, textbook_fee, material_cost from afterschool_class order by class_num";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //실행된 SQL문의 결과를 열과 행의 형태로 얻어냅니다.
		SubjectVO emVo = null;
		try {
		con = DBUtill.getConnection();
		System.out.println("DAO SubList연결 성공");
		pstmt = con.prepareStatement(tml);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			emVo = new SubjectVO(rs.getString(1), rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6));
			list.add(emVo);
			
		
		}
	} catch (SQLException se) {
		System.out.println(se);
	} catch (Exception e) {
		System.out.println(e);
	} finally {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (SQLException se) {

		}
	}
		return list;
	}

}
