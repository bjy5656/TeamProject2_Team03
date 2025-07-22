package functionDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bookDTO.BookDTO;

public class BookDAO {
   
   public Connection connection;
   public PreparedStatement preparedStatement;
   public ResultSet resultSet;
   
   //책 추가
   public void addBook(BookDTO book) {
      String query = "INSERT INTO TBL_BOOKS"
            + "(BOOK_NUMBER, BOOK_TITLE, BOOK_AUTHOR) "
            + "VALUES(SEQ_BOOK.NEXTVAL, ?, ?)";
      try {
      connection = DBConnecter.getConnection();
         preparedStatement = connection.prepareStatement(query);
         preparedStatement.setString(1, book.getBookTitle());
         preparedStatement.setString(2, book.getBookAuthor());
         preparedStatement.executeUpdate();
   } catch (SQLException e) {
      System.out.println("addBook() sql 오류");
      e.printStackTrace();
   } finally {
      try {
         if(preparedStatement != null) {
            preparedStatement.close();
         }
         if(connection != null) {
            connection.close();
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
         
   }
   
   //책 제거 
   public void removeBook(String title) {
      String query = "DELETE FROM TBL_BOOKS "
            + "WHERE BOOK_TITLE = ?";
      try {
      connection = DBConnecter.getConnection();
         preparedStatement = connection.prepareStatement(query);
         preparedStatement.setString(1, title);
         preparedStatement.executeUpdate();
   } catch (SQLException e) {
      System.out.println("removeBook() sql 오류");
      e.printStackTrace();
   } finally {
      try {
         if(preparedStatement != null) {
            preparedStatement.close();
         }
         if(connection != null) {
            connection.close();
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
   }
   
}
