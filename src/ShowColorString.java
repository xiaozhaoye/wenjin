/**

 *作者：wwj

 *日期：2012/4/25

 *功能：在窗口中显示不停变化大小和颜色的字符

 *说明：利用多线程实现的applet小程序

 **/

import java.awt.*;

        import java.applet.Applet;

        import java.util.*;

        import java.awt.Graphics;





public class ShowColorString extends Applet implements Runnable	//继承Applet类并实现Runnable接口

{

    Thread clockThread=null;		//创建一个空线程

    private int size;

    private char c[]={ '一','个','彩','色','字','符','串'};

    int r1,g1,b1;



    public void init()			//初始化方法

    {



    }



    public void start()

    {

        if(clockThread==null)

        {

            clockThread=new Thread(this,"Clock2");	//创建线程对象clockThread

            clockThread.start();	//开始执行线程

        }

    }



    public void run()			//实现Runnable接口的run()方法

    {

        Thread myThread=Thread.currentThread();//创建线程对象myThread

        while(clockThread==myThread)

        {

            repaint();		//通过repaint方法调用paint方法

            try

            {

                Thread.sleep(1000);		//休眠1秒



            }

            catch (InterruptedException e){}

        }

    }







    public void paint(Graphics g)

    {

        for(int i=0;i<7;i++)

        {

            r1=(int)(Math.random()*255);	//通过调用Math类的random产生随机数

            g1=(int)(Math.random()*255);	//再通过随机数分别设置三原色，红绿蓝

            b1=(int)(Math.random()*255);

            Color color=new Color(r1,g1,b1);	//创建一个颜色对象

            g.setColor(color);					//设置颜色

            size=20+(int)(Math.random()*50);

            Font f=new Font("",1,size);		//设置字体

            g.setFont(f);

            g.drawChars(c,i,1,20+i*40,100);	//显示指定大小颜色的字符串

        }

    }



    public void stop()			//调用stop方法，停止线程

    {

        clockThread=null;

    }



}
