package com.itguigu.eduService.service.impl;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.data.domain.Example;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HelloB {

    public static void main(String[] args) {
//        scanner("asd");

        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        int width = sc.nextInt();
        int r = sc.nextInt();
        int side = sc.nextInt();

        Rectangle rectangle = new Rectangle(length, width);
        Circle circle = new Circle(r);
        Square square = new Square(side);

        System.out.println(rectangle.GetArea(length, width));

        double AreaCircle = circle.GetArea(r);
        int AreaCircle1 = (int)circle.GetArea(r);
        DecimalFormat df = new DecimalFormat("###.0");
        double temp;
        if (AreaCircle > AreaCircle1){
            temp = Double.parseDouble(df.format(AreaCircle));
            if (temp != AreaCircle)
                System.out.println(String.format("%.2f", AreaCircle));
            else
                System.out.println(AreaCircle);
        }
        else
            System.out.println(AreaCircle1);

        System.out.println(square.GetArea(side));
    }


    public static void scanner(String tip) {
        System.out.println("输入：");
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[] num1 = new int[m];
        int[] num2 = new int[n];
        // 换成其他数据类型也一样，其他数值类型就修改int跟nextInt就可以了，
        //String就把nextInt()换成next()
        for(int i = 0; i < m; i ++) {
            num1[i] = sc.nextInt();  // 一个一个读取
        }
        for(int i = 0; i < n; i ++) {
            num2[i] = sc.nextInt();
        }
        System.out.println("输出：");
        System.out.println(Arrays.toString(num1));
        System.out.println(Arrays.toString(num2));
    }

}


class shape{
    private int x;
    private int y;

    public shape(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class Rectangle extends shape{
    private int length;
    private int width;

    public Rectangle(int length, int width){
        super(length, width);
        this.length = length;
        this.width = width;
    }

    public int GetArea(int length, int width){
        int Area = length * width;
        return Area;
    }
}

class Circle extends shape{
    int r;

    public Circle(int r){
        super(r, r);
        this.r = r;
    }

    public double GetArea(int r){
        double Area;
        Area = 3.14 * r * r;
        return Area;
    }
}

class Square extends Rectangle{
    int length;

    public Square(int length){
        super(length, length);
        this.length = length;
    }

    public int GetArea(int length){
        int Area;
        Area = length * length;
        return Area;
    }
}
