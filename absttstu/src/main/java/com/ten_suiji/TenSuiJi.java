package com.ten_suiji;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 生成随机数
 * Created by Administrator on 2020/1/12.
 */
public class TenSuiJi {

    public static void main(String[] args) {
        String str[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        int count = 5408; //需要导出人数
        //生成随机数集合
        List<String> zhuanhuan = zhuanhuan(str, count);
        //导出excle
        export(zhuanhuan);
    }

    static void export(List<String> listData) {
        String filePath = "C:\\Users\\Administrator\\Desktop\\export.xls";
        WritableWorkbook wwb = null;
        try {
            wwb = Workbook.createWorkbook(new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //创建Excel表的数据
        assert wwb != null;
        WritableSheet sheet = wwb.createSheet("人员手机号", 0);//或者rwb.getSheet(0)获取第一个区域
        //在第一行添加列字段说明
        try {
            sheet.addCell(new Label(0, 0, "手机号"));
            for (int i = 0; i < listData.size(); i++) {
                sheet.addCell(new Label(0, i + 1, listData.get(i)));
            }
            try {
                wwb.write();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (WriteException e) {
            e.printStackTrace();
        } finally {
            try {
                wwb.close();
            } catch (IOException | WriteException e) {
                e.printStackTrace();
            }
        }

    }

    private static List<String> zhuanhuan(String str[], int count) {
        List<java.lang.String> list = new ArrayList<>();
        for (int j = 0; j < count; j++) {
            StringBuilder sb = new StringBuilder();
            sb.append("18");
            for (int i = 0; i < 9; i++) {
                Random e = new Random();
                int nextInt = e.nextInt(10);
                sb.append(nextInt);
                System.gc();
            }
            list.add(sb.toString());
        }
        return list;
    }
}