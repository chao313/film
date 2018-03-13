//package demo.spring.boot.other;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Date;
//
//public class Other {
//
//
//    public Integer queryByRecordtime(Date date) {
//        String sql = "select max(number) as number  from t_y1c_apply where date_format( ? ,'%Y-%m-%d' )=date_format(now( ) , '%Y-%m-%d' )";
//        Object[] p = {recordtime};
//        ResultSet rs = super.executeQuery(sq1, p);
//        Integer result = 0;
//        try {
//            while (rs.next()) {
//                result = rs.getInt("number") + 1;
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//
//        }
//        return result;
//    }
//}