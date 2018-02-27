import java.sql.*;
//import java.util.*;
import java.text.*;
import java.util.Formatter;
import java.util.Scanner;


public class User {
    protected String User_Name;
    protected String Password;
    protected int User_ID=0;

//    private static String driver = "com.mysql.jdbc.Driver";
    //此处查看网络才知道。要求SSL，所以就酱紫咯：https://zhidao.baidu.com/question/2056521203295428667.html
//    private static String url = "jdbc:mysql://127.0.0.1:3306/Shop_User?useUnicode=true&characterEncoding=GBK&useSSL=true";
//    private static String user = "root";
//    private static String password = "zzb1184827350";
//    private static String url = "jdbc:mysql://192.168.2.127:3306/Shop_User?useUnicode=true&characterEncoding=GBK&useSSL=true";
//    private static String user = "pi";
//    private static String password = "zzb162122";


    protected Mysql_Connect mysql=new Mysql_Connect();

    public void setUser_Name(String s){
        this.User_Name=s;
    }

    public void setUser_ID(int d){
        this.User_ID=d;
    }
    public void setPassword(String p){
        this.Password=p;
    }
    public int getUser_ID(){
        return this.User_ID;
    }
    public String getPassword(){
        return this.Password;
    }
    public String getUser_Name(){
        return this.User_Name;
    }



    public boolean Sign_in(String User_name, String passwd) {
        String ps=" ";
        int id=0;
        try {
            mysql.Connect();
            Statement statement=mysql.getStatement();
            String Check = "select  id,password from User where name=' " + User_name + " ' ";
            ResultSet checkpasswd = statement.executeQuery(Check);
            if(checkpasswd.next()) {
                id = checkpasswd.getInt("id");
                ps = checkpasswd.getString("password");
            }
            checkpasswd.close();
            mysql.Dis_Connect();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //从数据库中取出来的字符串带空格？******
        ps=ps.trim();
//        System.out.println(ps);
        if (!passwd.equals(ps)) {
            System.out.println("\n\n\t\tThe Password is wrong !!!");
            return false;
        }
        System.out.println("\n\n\t\t*********Welcome to Here !!!*********");
        this.setUser_ID(id);

        return true;
    }




    public void Sign_up(String User_Name, String passwd) {
        try {
            mysql.Connect();
            Statement statement=mysql.getStatement();
            java.util.Date dNow = new java.util.Date();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

            String Rdate = ft.format(dNow);
            String sql = "select id,name,R_date from User where name=' " + User_Name + " '  and password=' " + passwd + " ' ";
            String insert = "insert into User(name,password,R_date) values(' " + User_Name + " ' ,' " + passwd + " ',' " + Rdate + " ')";
            boolean rs1 = statement.execute(insert);

            mysql.Dis_Connect();
            mysql.Connect();
            statement=mysql.getStatement();
            ResultSet rs = statement.executeQuery(sql);
            if(!rs1){
                System.out.println("\t\t----------------------------------");
                System.out.println("\t\t************注册成功！*************");
                System.out.println("\t\t----------------------------------");
                System.out.println("\t\t ID" + "\t\t" + " NAME" + "\t\t" + "Register_Date\n");
            }
            int user_id = 0;
            String name = " ";
            String R_d = " ";
            while (rs.next()) {
                user_id = rs.getInt("id");
                name = rs.getString("name");
                R_d = rs.getString("R_date");
                name = new String(name.getBytes("UTF-8"), "UTF-8");
                System.out.println("\t\t"+user_id + "\t\t" + name + "\t\t" + R_d);
            }
            System.out.println("\t\t----------------------------------");
            System.out.println("\t\t----------------------------------");

            rs.close();
            mysql.Dis_Connect();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Change_the_Password(User The_user, String new_passwd) {
        try {
            mysql.Connect();
            Statement statement=mysql.getStatement();
            String sql = "select id,name,password from User where name=' " + The_user.getUser_Name() + " '  and password='" + new_passwd + " ' ";
            String Check = "select  password from User where name=' " + The_user.getUser_Name() + " ' and id= "+this.getUser_ID()+" ";
            ResultSet checkpasswd = statement.executeQuery(Check);
            //此处要求我先走一步next(),果然，从next出现开始才开始读数，不然会报错！！
            if(checkpasswd.next()) {
                String oldps = checkpasswd.getString("password");
//                System.out.println(oldps + "&&&&&&");
                //老问题：String要用equals()才能比较，不能用== 会报错！！
                if (oldps.equals(The_user.getPassword())) {
                    System.out.println("The Old Password is wrong !!!");
                    checkpasswd.close();
                    mysql.Dis_Connect();
                    return;
                }
                setPassword(new_passwd);
                checkpasswd.close();
                String Change = "UPDATE User set password=' " + new_passwd + " ' where id="+The_user.getUser_ID()+"";
                boolean rs1=statement.execute(Change);
                if(!rs1) {
                    ResultSet rs = statement.executeQuery(sql);
                    System.out.println("\t\t----------------------------------");
                    System.out.println("*****************执行结果:*****************");
                    System.out.println("\t\t----------------------------------");
                    System.out.println(" \tID" + "\t\t" + " NAME" + "\t\t" + "New_Password");
                    System.out.println("\t\t----------------------------------");
                    int user_id = 0;
                    String name = " ";
                    String passwd = " ";
                    while (rs.next()) {
                        user_id = rs.getInt("id");
                        name = rs.getString("name");
                        passwd = rs.getString("password");
                        name = new String(name.getBytes("UTF-8"), "UTF-8");
                        System.out.println(user_id + "\t" + name + "\t" + passwd);
                    }
                    System.out.println("\t\t----------------------------------\n\n");
                    rs.close();
                }
            }
            mysql.Dis_Connect();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Change_the_User_Name(User The_user,String New_Name) {
        try {
            mysql.Connect();
            Statement statement=mysql.getStatement();
            String sql = "select id,name from User where name=' " + The_user.getUser_Name()+ " 'and id="+The_user.getUser_ID()+ " ";
            String Change = "UPDATE User set name=' "+ New_Name +" ' where name=' " + The_user.getUser_Name()+ " 'and id="+The_user.getUser_ID()+ " ";
            boolean rs1=statement.execute(Change);
            if (rs1) {
                ResultSet rs = statement.executeQuery(sql);
                System.out.println("\t\t----------------------------------");
                System.out.println("*****************执行结果:*****************");
                System.out.println("\t\t----------------------------------");
                System.out.println(" ID" + "\t\t" + " NAME" + "\t\t");
                System.out.println("----------------------------------");
                int user_id = 0;
                String name = " ";
                while (rs.next()) {
                    user_id = rs.getInt("id");
                    name = rs.getString("name");
                    name = new String(name.getBytes("UTF-8"), "UTF-8");
                    System.out.println(user_id + "\t" + name + "\t");
                }
                rs.close();
                System.out.println("----------------------------------\n\n");
            }
            mysql.Dis_Connect();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class Customer extends User{
    private int[] User_Goods=new int[10];
    public int sum=0;
    public void Show_Goods(Goods goods){
        Formatter f=new Formatter(System.out);
        f.format("%10.10s %10s %10s %10s %10s\n","序号","商品","价格","折扣","是否有货");
        f.format("%10.10s %10s %10s %10s %10s\n","---","---","---","---","------");
        for(int x:User_Goods){
            goods.Show_Good(x);
        }
        System.out.println("\n\n\t\t 您一共消费了 "+Cal_Balance()+" 元，欢迎下次再来！");

    }
    public void Buy(int id){
        int price=Goods.getPrice(id);
        if(!Goods.IsEmpty(id) && price!=0){
            User_Goods[sum]=id;
            sum++;
        }
    }

    public  int Cal_Balance(){
        int balance=0;
        for(int x:User_Goods){
            balance+=Goods.getPrice(x);
        }
        return balance;
    }

    public void Change_the_Balance() {
        try {
            mysql.Connect();
            Statement statement=mysql.getStatement();
            String sql = "update User set Balance= "+Cal_Balance()+" where name=' " + User_Name+ " 'and id="+User_ID+ " ";
            boolean rs1=statement.execute(sql);
            if (!rs1) {

            }
            mysql.Dis_Connect();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class Administrator extends User{
    int level=0;
    public void Insert_Good(int id){
        Scanner input = new Scanner(System.in);
        input.useDelimiter("\n");
        System.out.print("\t\tPlease Input the Good's Name:");
        String name=input.next();
        System.out.print("\t\tPlease Input the Good's Price:");
        int price=input.nextInt();
        System.out.print("\t\tPlease Input the Good's Count(default:100):");
        String count=input.next();
        System.out.print("\t\tPlease Input the Good's Discount(default: 1.0):");
        String discount=input.next();
        if(count.isEmpty()){
            if(discount.isEmpty()) {
                Goods.Insert_Good(name, price);
            }else {
                Goods.Insert_Good(name,price,Integer.valueOf(discount).floatValue());
            }
        }
        else {
            if(discount.isEmpty()) {
                Goods.Insert_Good(name, price,Integer.valueOf(count).intValue());
            }else {
                Goods.Insert_Good(name,price,Integer.valueOf(count).intValue(),Integer.valueOf(discount).floatValue());
            }
        }
    }
    private void Create_User(){

    }
}


