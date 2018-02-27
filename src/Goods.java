import javafx.scene.paint.Stop;

import java.sql.*;
import java.util.Formatter;
import java.util.Scanner;
//import java.util.*;
//import java.text.*;

public class Goods{
    //    private static String driver = "com.mysql.jdbc.Driver";
//    //此处查看网络才知道。要求SSL，所以就酱紫咯：https://zhidao.baidu.com/question/2056521203295428667.html
////    private static String url = "jdbc:mysql://127.0.0.1:3306/Shop_User?useUnicode=true&characterEncoding=GBK&useSSL=true";
////    private static String user = "root";
////    private static String password = "zzb1184827350";
//    private static String url = "jdbc:mysql://192.168.2.127:3306/Shop_User?useUnicode=true&characterEncoding=GBK&useSSL=true";
//    private static String user = "pi";
//    private static String password = "zzb162122";
    private static Mysql_Connect mysql=new Mysql_Connect();

    public static boolean IsEmpty(int id){
        boolean IsEmpty=true;
        try {
            mysql.Connect();
            Statement statement=mysql.getStatement();
            String sql = "select Is_Empty from Goods where good_id= " + id + " ";
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()) {
                IsEmpty=rs.getBoolean("Is_Empty");
            }
            rs.close();
            mysql.Dis_Connect();
            return IsEmpty;
        }  catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return IsEmpty;
    }
    public static String getName(int id){
        String Name=" ";
        try {
            mysql.Connect();
            Statement statement=mysql.getStatement();
            String sql = "select good_name from Goods where good_id= " + id + " ";
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()) {
                Name=rs.getString("good_name");
            }
            rs.close();
            mysql.Dis_Connect();


        }  catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Name;
    }


    public static int getPrice(int id){
        int price=0;
        try {
            mysql.Connect();
            Statement statement=mysql.getStatement();
            String sql = "select price from Goods where good_id= " + id + " ";
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()) {
                price=rs.getInt("price");
            }
            rs.close();
            mysql.Dis_Connect();


        }  catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return price;
    }


    public static void Insert_Good(String name,int price){
        try {
            mysql.Connect();
            Statement statement=mysql.getStatement();
//
//            String sql = "select good_name,price from Goods where n";
            String insert = "insert into Goods(good_name,price,count,discount,Is_Empty) values(' "+name+" ' , "+price+" ,100,1.0,false)";
            //            String delete = "delete from tcount_tbl where runoob_author = \"RUNOOB.COM\" ";
            boolean rs1 = statement.execute(insert);
            //            boolean rs1 = statement.execute(delete);
//            ResultSet rs = statement.executeQuery(sql);
            if(rs1) {
                System.out.println("----------------------------------");
                System.out.println("*************入库成功:*************");
                System.out.println("----------------------------------");
            }

            mysql.Dis_Connect();

        }  catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void Insert_Good(String name,int price,int count){
        try {
            mysql.Connect();
            Statement statement=mysql.getStatement();
//
//            String sql = "select good_name,price from Goods where n";
            String insert = "insert into Goods(good_name,price,count,discount,Is_Empty) values(' "+name+" ' , "+price+" ,"+count+",1.0,false)";
            //            String delete = "delete from tcount_tbl where runoob_author = \"RUNOOB.COM\" ";
            boolean rs1 = statement.execute(insert);
            //            boolean rs1 = statement.execute(delete);
//            ResultSet rs = statement.executeQuery(sql);
            if(rs1) {
                System.out.println("----------------------------------");
                System.out.println("*************入库成功:*************");
                System.out.println("----------------------------------");
            }

            mysql.Dis_Connect();

        }  catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void Insert_Good(String name,int price,int count,float discount){
        try {
            mysql.Connect();
            Statement statement=mysql.getStatement();
//
//            String sql = "select good_name,price from Goods where n";
            String insert = "insert into Goods(good_name,price,count,discount,Is_Empty) values(' "+name+" ' , "+price+" ,"+count+","+discount+",false)";
            //            String delete = "delete from tcount_tbl where runoob_author = \"RUNOOB.COM\" ";
            boolean rs1 = statement.execute(insert);
            //            boolean rs1 = statement.execute(delete);
//            ResultSet rs = statement.executeQuery(sql);
            if(rs1) {
                System.out.println("----------------------------------");
                System.out.println("*************入库成功:*************");
                System.out.println("----------------------------------");
            }

            mysql.Dis_Connect();

        }  catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Insert_Good(String name,int price,float discount){
        try {
            mysql.Connect();
            Statement statement=mysql.getStatement();
//
            String insert = "insert into Goods(good_name,price,count,discount,Is_Empty) values(' "+name+" ' , "+price+" ,100,"+discount+",false)";
            //            String delete = "delete from tcount_tbl where runoob_author = \"RUNOOB.COM\" ";
            boolean rs1 = statement.execute(insert);
            //            boolean rs1 = statement.execute(delete);
//            ResultSet rs = statement.executeQuery(sql);
            if(rs1) {
                System.out.println("----------------------------------");
                System.out.println("*************入库成功:*************");
                System.out.println("----------------------------------");
            }

            mysql.Dis_Connect();

        }  catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void Show_Good(int id){
        try {
            mysql.Connect();
            Statement statement=mysql.getStatement();
            String sql = "select good_id,good_name,price,discount,Is_Empty from Goods where good_id="+id+" ";
            ResultSet rs = statement.executeQuery(sql);
            String good_name="";
            int good_id=0,price=0;
            float discount;
            String Is_Empty=" ";
            while(rs.next()) {
                good_id=rs.getInt("good_id");
                price=rs.getInt("price");
                good_name=rs.getString("good_name");
                if(rs.getBoolean("Is_Empty")){
                    Is_Empty="卖断";
                }
                else{
                    Is_Empty="还有";
                }
                discount=rs.getFloat("discount");
                Formatter f=new Formatter(System.out);
                f.format("%10d %10s %10d %10.2f %10s\n",good_id,good_name,price,discount,Is_Empty);
            }
            rs.close();
            mysql.Dis_Connect();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Show_Goods_List(int Start_id,int Stop_id){
        try {
            mysql.Connect();
            Statement statement=mysql.getStatement();
            if(Start_id>Stop_id){
                int tmp=Start_id;
                Start_id=Stop_id;
                Stop_id=tmp;
            }
            String sql = "select good_id,good_name,price,discount,Is_Empty from Goods where good_id<="+Stop_id+" and good_id>="+Start_id+" ";
            ResultSet rs;
            rs = statement.executeQuery(sql);
            String good_name="";
            int good_id=0,price=0;
            float discount;
            String Is_Empty=" ";
            Formatter f=new Formatter(System.out);
            f.format("%10s %10s %10s %10s %10s\n","ID","NAME","PRICE","DISC","NUM=0?");
            while(rs.next()) {
                good_id=rs.getInt("good_id");
                price=rs.getInt("price");
                good_name=rs.getString("good_name");
                if(rs.getBoolean("Is_Empty")){
                    Is_Empty="！卖断了！";
                }
                else{
                    Is_Empty="还有货！";
                }
                discount=rs.getFloat("discount");
                f.format("%10d %10s %10d %10.1f %10s\n",good_id,good_name,price,discount,Is_Empty);
            }
            rs.close();
            mysql.Dis_Connect();
        }  catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}