package com.raptor.music;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class userSql {
   public static void write(String user) {
      Connection conn = null;
      Statement stmt = null;
      try {
         try {
            Class.forName("com.mysql.jdbc.Driver");
         } catch (Exception e) {
            System.out.println(e);
         }
         conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/users", "Tyler", "Blackrobin7");
         stmt = (Statement) conn.createStatement();
         String query = "INSERT INTO data " + "VALUES (NULL, '" + user + "','temp', 'tbd')";
         stmt.executeUpdate(query);
         System.out.println(user + " Added");

      } catch (SQLException excep) {
         excep.printStackTrace();
      } catch (Exception excep) {
         excep.printStackTrace();
      } finally {
         try {
            if (stmt != null)
               stmt.close();
         } catch (SQLException se) {
            // handle the exception
         }
         try {
            if (conn != null)
               conn.close();
         } catch (SQLException se) {
            // handle the exception
         }
      }
   }

   public static String read() {
      String data = "";
      try {
         // create our mysql database connection
         String myDriver = "com.mysql.jdbc.Driver";
         String myUrl = "jdbc:mysql://localhost/users";
         Class.forName(myDriver);
         Connection conn = DriverManager.getConnection(myUrl, "Tyler", "Blackrobin7");

         // our SQL SELECT query.
         // if you only need a few columns, specify them by name instead of using "*"
         String query = "SELECT * FROM data";

         // create the java statement
         Statement st = conn.createStatement();

         // execute the query, and get a java resultset
         ResultSet rs = st.executeQuery(query);

         // iterate through the java resultset
         while (rs.next()) {
            String username = rs.getString("username");

            // print the results
            data = data + username + "  ";
         }
         st.close();
      } catch (Exception e) {
         System.err.println("Got an exception! ");
         System.err.println(e.getMessage());
      }
      return data;
   }
}