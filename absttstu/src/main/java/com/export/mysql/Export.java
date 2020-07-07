package com.export.mysql;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Export {

    private static Connection conn = null;
    private static List<String> databases = new ArrayList<>(); //数据库集合

    static {
        String url = "jdbc:mysql://rm-2ze9f8s7lq4c9l5cv.mysql.rds.aliyuncs.com:3306/uuku1tl3?userUnicode=true";
        String driver = "com.mysql.jdbc.Driver";
        String username = "concon";
        String password = "Mysql_1234";
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            stmt.execute("show tables");
            //查询所有数据库
            ResultSet rs = stmt.executeQuery("select distinct TABLE_SCHEMA from information_schema.COLUMNS where column_name = 'placeholder' and table_name = 'node_relationship_b' ");
            while (rs.next()) {
                databases.add(rs.getString(1));
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void exportData(List<String> databases) {
        List<Data> list = new ArrayList<>();
        try {
            for (String database : databases) {
                try {
                    //切换到当前租户
                    conn.setCatalog(database);
                    Statement stmt = conn.createStatement();
                    ResultSet resultSet = stmt.executeQuery("SELECT note1.name as '记事名称',a.businessPk as '记事',a.fileBusinessSourcePk as '发票数据' FROM node_file a \n" +
                            "LEFT JOIN \n" +
                            "(SELECT pk,name FROM (\n" +
                            "SELECT pk,'住宿记事' as name FROM node_hotel \n" +
                            "UNION ALL\n" +
                            "SELECT pk,'其他记事' as name from node_other\n" +
                            "UNION ALL\n" +
                            "SELECT pk,'餐饮记事' as name FROM node_eating\n" +
                            "UNION ALL\n" +
                            "SELECT pk,'交通记事' as name FROM node_travel\n" +
                            "UNION ALL\n" +
                            "SELECT pk,'通讯记事' as name FROM node_communicate\n" +
                            "UNION ALL\n" +
                            "SELECT note_pk,(SELECT name FROM note_type b WHERE b.pk = billtype_pk) as name FROM note_all_index) AS note) as note1\n" +
                            "ON a.businessPk = note1.pk\n" +
                            "where a.fileBusinessSourcePk like '%\\_%' and not EXISTS (SELECT * from node_expense b where a.businessPk = b.pk ) and valid = 1;");
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            Data data = new Data();
                            data.setName(resultSet.getString("记事名称"));
                            data.setNote(resultSet.getString("记事"));
                            data.setData(resultSet.getString("发票数据"));
                            list.add(data);
                        }
                        resultSet.close();
                    } else {
                        System.out.println("无数据");
                    }
                    stmt.close();
                } catch (Exception e) {
                    System.out.println(database + "获取结果失败，信息：" + e.getMessage() );
                    //需要时可放开
                    //e.printStackTrace();
                }
            }
            makeExcle(list);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 导出数据
     * @param list
     */
    public static void makeExcle(List<Data> list) {
        //1. 导出Excel的路径
        String filePath = "C:\\Users\\Administrator\\Desktop\\export.xls";
        WritableWorkbook wwb = null;
        try {
            wwb = Workbook.createWorkbook(new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //创建Excel表的数据
        WritableSheet sheet = wwb.createSheet("数据", 0);//或者rwb.getSheet(0)获取第一个区域
        try {
            //在第一行添加列字段说明
            sheet.addCell(new Label(0, 0, "记事名称"));
            sheet.addCell(new Label(1, 0, "记事"));
            sheet.addCell(new Label(2, 0, "发票数据"));

            for (int i = 0; i < list.size(); i++) {
                //Number对应数据库的int类型数据
                //sheet.addCell(new jxl.write.Number(0,i,list.get(i).getId()));
                //Label对应数据库String类型数据
                sheet.addCell(new Label(0, i + 1, list.get(i).getName()));
                sheet.addCell(new Label(1, i + 1, list.get(i).getNote()));
                sheet.addCell(new Label(2, i + 1, list.get(i).getData()));
            }
            wwb.write();
        } catch (WriteException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                wwb.close();
            } catch (IOException | WriteException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        exportData(databases);
    }

}
