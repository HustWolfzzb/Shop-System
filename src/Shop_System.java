/*
 * 显示系统初始界面菜单
 */


//输入输出

import java.util.Scanner;

public class Shop_System{
    public static  void main (String[] args){
        //显示初始登录界面
        Menu menu = new Menu();
        menu.InitMenu();
        Scanner First = new Scanner(System.in);
        System.out.print("\t \tPlease Input Your Action: ");
        int in = First.nextInt();
        Customer user;
        user = new Customer();
        Goods goods=new Goods();
        //不管登陆没登陆，先定一个用户实例，用户实例的具体内容请见另外一个文件，也就是一开始导入的Customor类
        if(in==2){
            boolean yes=menu.SignupMenu(user);
            while(!yes){
                yes=menu.SignupMenu(user);
            }
        }
        else if(in==3)
        {
            System.out.println("\n\n\t\t 感谢光临我们的Shop System，期待与您下次相遇\n\n");
            return;
        }
        while(!menu.LoginMenu(user)){
            System.out.println("\n\t\t密码错误，请重来！");
        }

        Scanner Op = new Scanner(System.in);
        //当确定用户登录之后，进入主界面
        /*
         * 因为嫌麻烦，所以我直接定义一个主界面的类。
         * 而不是定义一个方法。
         * 好吧，我承认那会儿是我还才开始写，理不太新思路瞎写的。
         * 后来发现用着蛮方便就没改了。
         */
        //定义一个主界面的实例。然后当需要回到主界面的时候，直接调用实例的方法就可以了
        int op=0;
        //对输入的数据进行识别，然后去相对应的界面
        while (op!=4) {
//                System.out.println("\t\t Please Input: ");
            menu.MainMenu();
            op=Op.nextInt();
            switch (op) {
                case 1:
                    menu.Customer(user,goods);
                    //当对用户的管理界面结束的时候回到主界面
                    /*
                     * 进入用户设置界面的话。通过调用面板实例的相应方法，可以对用户信息进行管理
                     * 注意当进入这一个子页面的时候，就已经进入了用户实例的管理界面。
                     * 当然还是在主函数中，只不过是调用了Menu的方法而已。
                     */
                    break;
                case 2:
                    menu.Buy_Goods(user);
                    //如果Buy不自己停止的话会一直循环的哦
                    //回到主界面
                    break;
                case 3:
                    System.out.println("\t\t************* Show The Goods *************\n");
                    //其实我个人感觉好像用户信息类才是主体
                    /*
                     * 你看所有的选项都要用到它，当然这确实是废话
                     * 因为个性化的原因，所以必须对个人信息进行高度的集成化处理
                     * 所以才有了我们的用户类
                     */
                    //当用户的购物车信息展示2秒钟后。
                    // 再默认回到主界面。
                    // 后期可以改为手动回到主界面。
                    user.Show_Goods(goods);
                    try {
                        Thread.sleep(2000);
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                case 4:
                    /*
                     * 因为我在while循环里面设置:
                     * 如果操作数!=4，就会一直无限循环
                     * 那么当操作数为4的时候，就会打破整个循环，从而运行到main的后面直至结束。
                     */
                    //哈，每次退出前算账！！还要记到数据库！
                    if (user.Cal_Balance()!=0)
                        user.Change_the_Balance();
                    System.out.print("\t\tGoodBye！【 "+user.getUser_Name().toUpperCase()+" 】Thanks for your Shopping!");
                    System.out.print(" You almost Consume "+user.Cal_Balance()+" 元!\n\n\n");
                    break;
            }
            if(op!=4 && op!=3){
                System.out.println("Please Input the Next Action: ");
            }
        }
    }
}
