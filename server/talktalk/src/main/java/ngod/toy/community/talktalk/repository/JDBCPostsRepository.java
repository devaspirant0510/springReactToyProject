package ngod.toy.community.talktalk.repository;

import ngod.toy.community.talktalk.model.Posts;
import ngod.toy.community.talktalk.model.User;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCPostsRepository implements PostsRepository{
    private final DataSource dataSource;

    public JDBCPostsRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Posts save(Posts post) {
        String sql = "INSERT INTO POSTS_T (title, content, user_id) VALUES (?,?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,post.getTitle());
            pstmt.setString(2,post.getContent());
            pstmt.setLong(3,post.getUserId());
            pstmt.executeUpdate();
            resultSet = pstmt.getGeneratedKeys();
            if(resultSet.next()){
                post.setCratedAt(resultSet.getString(resultSet.findColumn("created_at")));
                post.setUpdatedAt(resultSet.getString(resultSet.findColumn("updated_at")));
                return post;
            }else{
                throw new IllegalStateException("글 작성하는 도중 오류가 발생했습니다 다시 시도해주세요");
            }

        }catch (SQLException e){
            throw new IllegalStateException("sql 에러 "+e.toString());
        }
    }
    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    @Override
    public List<Posts> findAll() {
        String sql = "SELECT * from POSTS_T";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            List<Posts> postList = new ArrayList<>();
            while(resultSet.next()){
                Posts post = new Posts();
                post.setId(resultSet.getLong(resultSet.findColumn("id")));
                post.setUpdatedAt(resultSet.getString(resultSet.findColumn("updated_at")));
                post.setCratedAt(resultSet.getString(resultSet.findColumn("created_at")));
                post.setTitle(resultSet.getString(resultSet.findColumn("title")));
                post.setUserId(resultSet.getLong(resultSet.findColumn("user_id")));
                post.setContent(resultSet.getString(resultSet.findColumn("content")));
                postList.add(post);

            }
            return postList;


        }catch (SQLException e){
            throw new IllegalStateException("데이터베이스 에러 : "+e);
        }
        finally {
            close(conn,pstmt,resultSet);
        }

    }

    @Override
    public Optional<Posts> findById(Long id) {
        return Optional.empty();
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
