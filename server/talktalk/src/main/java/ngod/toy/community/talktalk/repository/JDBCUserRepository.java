package ngod.toy.community.talktalk.repository;

import ngod.toy.community.talktalk.entity.Account;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Deprecated
public class JDBCUserRepository implements AccountRepository {
    private final DataSource dataSource;

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    public JDBCUserRepository(DataSource datasource) {
        this.dataSource = datasource;
    }

    @Override
    public Account save(Account account) {
        String sql = "INSERT INTO USER_T (user_id, user_name, password, profile_url, user_desc) VALUES (?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, account.getUserId());
            pstmt.setString(2, account.getUserName());
            pstmt.setString(3, account.getPassword());
            pstmt.setString(4, account.getProfileUrl());
            pstmt.setString(5, account.getUserDesc());
            pstmt.executeUpdate();
            resultSet = pstmt.getGeneratedKeys();
            if(resultSet.next()){
                account.setId(resultSet.getLong(resultSet.findColumn("id")));
                account.setCreatedAt(resultSet.getString(resultSet.findColumn("created_at")));
                return account;
            }else{
                throw new IllegalStateException("회원가입하는 도중 오류가 발생했습니다 다시 시도해주세요");
            }

        }catch (SQLException e){
            throw new IllegalStateException("데이터베이스 에러 : "+e);
        }
        finally {
            close(conn,pstmt,resultSet);
        }
    }

    @Override
    public List<Account> findAll() {
        String sql = "SELECT * from USER_T";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            List<Account> accountList = new ArrayList<>();
            while(resultSet.next()){
                Account account = new Account();
                account.setId(resultSet.getLong(resultSet.findColumn("id")));

            }


        }catch (SQLException e){
            throw new IllegalStateException("데이터베이스 에러 : "+e);
        }
        finally {
            close(conn,pstmt,resultSet);
        }

        return List.of();
    }

    @Override
    public Optional<Account> findById(Long id) {
        String sql = "SELECT * from USER_T where id = ? limit 1";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1,id);
            resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                Account account = new Account();
                account.setId(resultSet.getLong(resultSet.findColumn("id")));
                account.setCreatedAt(resultSet.getString(resultSet.findColumn("created_at")));
                account.setProfileUrl(resultSet.getString(resultSet.findColumn("profile_url")));
                account.setPassword(resultSet.getString(resultSet.findColumn("password")));
                account.setUserId(resultSet.getString(resultSet.findColumn("user_id")));
                account.setUserName(resultSet.getString(resultSet.findColumn("user_name")));
                account.setUserDesc(resultSet.getString(resultSet.findColumn("user_desc")));
                return Optional.of(account);
            }else{
                return Optional.empty();
            }
        }catch (SQLException e){
            throw new IllegalStateException("데이터베이스 에러 : "+e);
        }
        finally {
            close(conn,pstmt,resultSet);
        }
    }

    @Override
    public Optional<Account> findByUserId(String userId) {
        String sql = "SELECT * from USER_T where user_id = ? limit 1";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,userId);
            resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                Account account = new Account();
                account.setId(resultSet.getLong(resultSet.findColumn("id")));
                account.setCreatedAt(resultSet.getString(resultSet.findColumn("created_at")));
                account.setProfileUrl(resultSet.getString(resultSet.findColumn("profile_url")));
                account.setPassword(resultSet.getString(resultSet.findColumn("password")));
                account.setUserId(resultSet.getString(resultSet.findColumn("user_id")));
                account.setUserName(resultSet.getString(resultSet.findColumn("user_name")));
                account.setUserDesc(resultSet.getString(resultSet.findColumn("user_desc")));
                return Optional.of(account);
            }else{
                return Optional.empty();
            }
        }catch (SQLException e){
            throw new IllegalStateException("데이터베이스 에러 : "+e);
        }
        finally {
            close(conn,pstmt,resultSet);
        }
    }

    @Override
    public Optional<Account> findByIdByPassword(String userId, String password) {
        String sql = "SELECT * from USER_T where user_id = ? and password = ? limit 1";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,userId);
            pstmt.setString(2,password);
            resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                Account account = new Account();
                account.setId(resultSet.getLong(resultSet.findColumn("id")));
                account.setCreatedAt(resultSet.getString(resultSet.findColumn("created_at")));
                account.setProfileUrl(resultSet.getString(resultSet.findColumn("profile_url")));
                account.setPassword(resultSet.getString(resultSet.findColumn("password")));
                account.setUserId(resultSet.getString(resultSet.findColumn("user_id")));
                account.setUserName(resultSet.getString(resultSet.findColumn("user_name")));
                account.setUserDesc(resultSet.getString(resultSet.findColumn("user_desc")));
                return Optional.of(account);
            }else{
                return Optional.empty();
            }
        }catch (SQLException e){
            throw new IllegalStateException("데이터베이스 에러 : "+e);
        }
        finally {
            close(conn,pstmt,resultSet);
        }
    }
    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}
