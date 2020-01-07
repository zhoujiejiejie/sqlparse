package com.sitinspring.common.sqlparser.single;

//正确
public class UpdateSqlParser extends BaseSingleSqlParser{

    public UpdateSqlParser(String originalSql) {
        super(originalSql);

    }

    //update(table_name) set (key = value) where()；

    @Override
    protected void initializeSegments() {

        segments.add(new SqlSegment("(update)(.+)(set)","[,]"));
        segments.add(new SqlSegment("(set)(.+?)( where | ENDOFSQL)","[,]"));
        segments.add(new SqlSegment("(where)(.+)(ENDOFSQL)","(and|or)"));
    }

}