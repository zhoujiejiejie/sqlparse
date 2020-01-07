package com.sitinspring.common.sqlparser.single;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SingleSqlParserFactory {


    // 看word是否在lineText中存在，支持正则表达式
    private static boolean contains(String sql,String regExp)         //sql:要解析的sql语句      regExp:正则表达式
    {
        Pattern pattern=Pattern.compile(regExp,Pattern.CASE_INSENSITIVE);
        Matcher matcher=pattern.matcher(sql);
        return matcher.find();
    }

    public static List<List<String>> generateParser(String sql)
    {
        BaseSingleSqlParser tmp = null;

        if(contains(sql,"(insert into)(.+)(select)(.+)(from)(.+)"))     //.contains()方法，当且仅当此字符串包含指定的char值序列时，返回true。
        {
            System.out.println("insert_select");
            tmp = new InsertSelectSqlParser(sql);
            //return tmp.splitSql2Segment();
        }
        else if(contains(sql,"(select)(.+)(from)(.+)"))
        {

            System.out.println("select");
            tmp = new SelectSqlParser(sql);
            //System.out.println("初始化SelectSqlParser结束");
        }
        else if(contains(sql,"(delete from)(.+)"))
        {
            System.out.println("delete");
            tmp = new DeleteSqlParser(sql);
            //return new DeleteSqlParser(sql);
        }
        else if(contains(sql,"(update)(.+)(set)(.+)"))
        {
            System.out.println("update");
            tmp = new UpdateSqlParser(sql);
            //return new UpdateSqlParser(sql);
        }
        else if(contains(sql,"(insert into)(.+)(values)(.+)"))
        {
            System.out.println("insert");
            tmp = new InsertSqlParser(sql);
            //return new InsertSqlParser(sql);
        }
        else if(contains(sql,"(create table)(.+)"))
        {
            System.out.println("create table");
            // return new InsertSqlParser(sql);
        }
        else if(contains(sql,"(create database)(.+)"))
        {
            System.out.println("create database");
            // return new InsertSqlParser(sql);
        }
        else if(contains(sql,"(show databases)"))
        {
            System.out.println("show databases");
            // return new InsertSqlParser(sql);
        }
        else if(contains(sql,"(use)(.+)"))
        {
            System.out.println("use");
            // return new InsertSqlParser(sql);
        }
        else
        {
            System.out.println("Input errors, please re-enter");
        }
        //sql=sql.replaceAll("ENDSQL", "");
        //	throw new Exception(sql.replaceAll("ENDOFSQL", ""));
        //return null;

        return tmp.splitSql2Segment();
    }



}