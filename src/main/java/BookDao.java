import java.sql.*;

public class BookDao {
    private static final String URL = "jdbc:mysql://localhost:3306/library?characterEncoding=utf8&serverTimezone=UTC";
        private static final String PASS = "root";
    private static final String USER = "root";
    private Connection connection;

    public BookDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASS);

        } catch (ClassNotFoundException e) {
            System.out.println("No driver found");
        } catch (SQLException e) {
            System.out.println("Could not establish connection");
        }

    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void librarySave(Books book) {
        final String zapytanie = "insert into books(title, author, year, isbn) values(?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(zapytanie);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2,book.getAuthor());
            preparedStatement.setInt(3, book.getYear());
            preparedStatement.setInt(4, book.getIsbn());
            preparedStatement.executeUpdate();
//            PreparedStatement prepStmt1 = connection.prepareStatement(sql1);
//            prepStmt1.setString(1, book.getTitle());
//            prepStmt1.setString(2, book.getAuthor());
//            prepStmt1.setInt(3, book.getYear());
//            prepStmt1.setInt(4, book.getIsbn());
//            prepStmt1.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Could not save record");
            e.printStackTrace();
        }
    }

    public Books libraryRead(int isbn) {
        final String sql = "select id, title, author, year, isbn from books where isbn = ?";
        try {
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setLong(1, isbn);
            ResultSet result = prepStmt.executeQuery();
            if (result.next()) {
                Books book = new Books();
                book.setId(result.getInt("id"));
                book.setTitle(result.getString("title"));
                book.setAuthor(result.getString("author"));
                book.setYear(result.getInt("year"));
                book.setIsbn(result.getInt("isbn"));
                return book;
            }
        } catch (SQLException e) {
            System.out.println("Could not get book");
        }
        return null;
    }

    public void libraryUpdate(Books book) {
        final String sql = "update books set title=?, author=?, year=? where isbn = ?";
        try {
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setString(1, book.getTitle());
            prepStmt.setString(2, book.getAuthor());
            prepStmt.setInt(3, book.getYear());
            prepStmt.setInt(4, book.getIsbn());
            prepStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Could not update record");
            e.printStackTrace();
        }
    }

    public void delete(int isbn) {
        final String sql = "delete from books where isbn = ?";
        try {
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setInt(1, isbn);
            prepStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Could not delete row");
        }
    }


}
