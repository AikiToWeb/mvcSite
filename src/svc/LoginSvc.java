package svc;

import static db.JdbcUtil.*;	
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class LoginSvc {
	public MemberInfo getLoginMember(String uid, String pwd) {
		Connection conn = getConnection();
		LoginDao loginDao = LoginDao.getInstance();
		loginDao.setConnection(conn);
		MemberInfo memberInfo = loginDao.getLoginMember(uid, pwd);
		// 쿼리에서 select 날리는 건 ViewSvc
		close(conn);
		
		return memberInfo;
	}
}
