import java.util.Scanner;

public class Menu{
    public void MainMenu() {
        System.out.println("\n\n\t\t\t 欢迎颜雨薇同学光临我们的Shop System" + "\n\n\n");
        System.out.println("\t\t\t\t 1. 我的账号\n\n");
        System.out.println("\t\t\t\t 2. 倾情购物\n\n");
        System.out.println("\t\t\t\t 3. 购物清单\n\n");
        System.out.println("\t\t\t\t 4. 退出系统\n\n");
    }
    public void Customer(Customer user,Goods goods){

        Scanner Buying = new Scanner(System.in);
        int opr=0;
        while(opr!=4) {
            System.out.println("\n\n\t\t\t 欢迎颜雨薇同学光临我们的Shop System" + "\n");
            System.out.println("\t\t\t\t 1. 修改名字\n\n");
            System.out.println("\t\t\t\t 2. 修改密码\n\n");
            System.out.println("\t\t\t\t 3. 已购商品\n\n");
            System.out.println("\t\t\t\t 4. 回到首页\n\n");
            System.out.print("\n\t Please Input Your Next Action : ");
            opr=Buying.nextInt();
            Scanner Operate = new Scanner(System.in);
            switch (opr){
                case 1:
                    System.out.print("\t Please Input Your New Name : ");
                    String new_name=Operate.nextLine();
                    user.Change_the_User_Name(user,new_name);
                    break;
                case 2:
                    System.out.print("\t Please Input Your New Password : ");
                    String Newpasswd=Operate.nextLine();
                    user.Change_the_Password(user,Newpasswd);
                    break;

                case 3:
                    System.out.println("\n\n\n\t\t************* Show The Goods *************\n");
                    user.Show_Goods(goods);
                    try {
                        Thread.sleep(3000);
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                case 4:
                    break;
            }
        }

    }
    public void InitMenu(){
        System.out.println("\n\n\t\t\t 欢迎颜雨薇同学光临我们的Shop System"+"\n\n");
        System.out.println("\t\t\t\t 1. Sign in the Shop System!\n\n");
        System.out.println("\t\t\t\t 2. Sign up the Shop System!\n\n");
        System.out.println("\t\t\t\t 3. Exit The Shop System!\n\n");
    }
    public boolean LoginMenu(User S){
        System.out.println("\n\n\t\t\t 欢迎颜雨薇同学光临我们的Shop System"+"\n\n");
        Scanner First = new Scanner(System.in);
        System.out.print("\t\t 1. Please Input Your Account : ");
        String  in = First.nextLine();
        in=in.trim();
        if(in.isEmpty()){
            return false;
        }
        System.out.print("\t\t 2. Please Input Your Password : ");
        String  in1 = First.nextLine();
        if(S.Sign_in(in,in1)){
            S.setUser_Name(in);
            S.setPassword(in1);
            return true;
        }
        return false;
    }
    public boolean SignupMenu(User S){
        System.out.println("\n\n\t\t\t 欢迎颜雨薇同学光临我们的Shop System"+"\n\n");
        Scanner First = new Scanner(System.in);
        System.out.print("\t\t 1. Please Input Your New Account : ");
        String  in = First.next();
        System.out.print("\t\t 2. Please Input Your New Password : ");
        String  in1 = First.next();
        System.out.print("\t\t 2. Please Re_Input Your Password : ");
        String in2=First.next();
        if(in1.equals(in2)){
            S.Sign_up(in,in1);
            return true;
        }
        else{
            System.out.println("\t\t前后密码不一致！\n\n\n\n");
            return  false;
        }

    }
    public void Buy_Goods(Customer user){
        Scanner Buying = new Scanner(System.in);
        int opr=0;
        int start=1;
        int stop=start+5;
        while(opr!=1000) {
            Goods.Show_Goods_List(start, stop);
            System.out.print("\n\n输入商品数字，即可购买，翻页输入0,上页-1，退出输入1000:");
            System.out.print("\n\n");
            opr = Buying.nextInt();
            if(opr==0){
                start=stop+1;
                stop=start+5;
            }else if(opr==-1){
                stop=start-1;
                start=stop-5;

            }
            else if(opr>0&& opr<1000){
                user.Buy(opr);
//                user.Insert_Good(opr);
            }
            else if(opr==1000){
                break;
            }
            else {
                opr=0;
            }
        }
    }
}
