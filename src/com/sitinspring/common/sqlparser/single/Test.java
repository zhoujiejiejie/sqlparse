package com.sitinspring.common.sqlparser.single;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Test {
    public static void main (String arg[])
    {
        System.out.println("请出入sql语句");
        boolean temp = true;
        while(temp)
        {
            @SuppressWarnings("resource")
            Scanner input = new Scanner(System.in);
            String sql = input.nextLine();

            //处理分行输入的问题，就是读;号才停止;
            while(sql.lastIndexOf(";")!=sql.length()-1){
                sql = sql+" "+input.nextLine();
            }

            sql = sql.trim();                              //处理前后空格;
            sql=sql.toLowerCase();                         //变小写;
            sql=sql.replaceAll("\\s+", " "); //"\\s+"多个空格 处理中间多余的空格回车和特殊字符;

            sql = sql.substring(0, sql.lastIndexOf(";"));  // 读;号才停止;  处理掉最后的;
            sql=""+sql+" ENDOFSQL";
            System.out.println(sql);


            //用一个ArryList来装分割好的sql
            List<List<String>> parameter_list=new ArrayList<List<String>>();

            if(sql.equals("quit"))
            {
                temp = false;
            }
            else
            {

                 /* 工厂类判断调用什么语句的正则表达分割 */
                parameter_list = SingleSqlParserFactory.generateParser(sql);


                System.out.println("执行结束SingleSqlParserFactory.generateParser函数");

                for(int i = 0;i < parameter_list.size();i++)
                {
                    System.out.println(parameter_list.get(i));
                }

            }


        }

    }
}