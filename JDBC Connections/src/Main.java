// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        /*
        *  Import Packages
        *  Load Driver
        *  Register Driver
        *  Create Connection
        *  Create Statement
        *  Execute Statement
        *  Close
        */

        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:postgresql://localhost:5432/demo";
        String uname = "postgres";
        String pass = "0000";

        String sqlRead = "SELECT * FROM demo.public.\"Student\"\n" +
                "ORDER BY sid ASC ";

        String sqlInsert = "insert into demo.public.\"Student\" values (4,'MOhan',48)";

        String sqlUpdate = "update demo.public.\"Student\"\n set sname='Mohan' where sid=4";

        String sqlDelete = "delete from demo.public.\"Student\"\n where sid=4";

        int sid = 102;
        String sname = "Maju";
        int marks = 100;

        String sqlCustom = "insert into demo.public.\"Student\"\n values ("+sid+",'"+sname+"',"+marks+")";

        String sqlPreparedCustom = "insert into demo.public.\"Student\" values (?,?,?)";

        try {
            Connection connection = DriverManager.getConnection(url,uname,pass);
            Statement statement = connection.createStatement();
//            boolean status = statement.execute(sqlInsert);
//            System.out.println(status);

            PreparedStatement preparedStatement = connection.prepareStatement(sqlPreparedCustom);
            preparedStatement.setInt(1,sid);
            preparedStatement.setString(2,sname);
            preparedStatement.setInt(3,marks);

            boolean status= preparedStatement.execute();

            ResultSet rs = statement.executeQuery(sqlRead);

            while(rs.next()){
                System.out.println(rs.getInt(1) + "-" + rs.getString(2)+"-"+rs.getInt(3));
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Connection established");


    }
}