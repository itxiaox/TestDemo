package com.itxiaox.testdemo;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.view.KeyEvent;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
/**
 * @Title: UiAutomator 测试学习 ， 测试用例
 * @Description:
 * 参考资料 慕课网视频（https://www.imooc.com/video/17040）
 * uiautomatorviewer.bat 工具的使用
 * @author: xiao
 * @date:  2019/5/17 13:50
 * @version v1.0
 */
@RunWith(AndroidJUnit4.class)
public class MKTest {
    public UiDevice uiDevice;

    //初始化
    @Before
    public void setUp(){
        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    //通过 Android tools工具配合使用，此工具在 sdk/tools/bin/uiautomatorviewer.bat
    @Test
    public void calculatorTest() throws InterruptedException {
        // 0. 初始化，滑动解锁， 主屏幕， 屏幕，滑动，按键
        uiDevice.swipe(1071,785,1100,800,10);
        uiDevice.pressKeyCode(KeyEvent.KEYCODE_HOME);//解锁之后进入主屏幕，按两次
        uiDevice.pressKeyCode(KeyEvent.KEYCODE_HOME);
        // 1. 打开计算器，计算器图标，坐标点击
        uiDevice.click(1321,471);
        Thread.sleep(2000);//打开应用需要时间，加个线程延时

        //先清除历史记录
        uiDevice.findObject(By.res("com.sec.android.app.popupcalculator:id/clear_btn")).click();

        // 进行 1=2 = 3计算
        uiDevice.findObject(By.res("com.sec.android.app.popupcalculator:id/bt_clear")).click();//先清除记录
        uiDevice.findObject(By.res("com.sec.android.app.popupcalculator:id/bt_01")).click();//点击1
        uiDevice.findObject(By.res("com.sec.android.app.popupcalculator:id/bt_add")).click();//点击+
        uiDevice.findObject(By.res("com.sec.android.app.popupcalculator:id/bt_02")).click();//点击2
        uiDevice.findObject(By.res("com.sec.android.app.popupcalculator:id/bt_equal")).click();//点击=


        UiObject2 ac = uiDevice.findObject(By.res("com.sec.android.app.popupcalculator:id/txtCalc"));
        String sum = ac.getText();
        Assert.assertEquals("3",sum);

    }
    @After
    public void tearDown(){
        //恢复场景
        uiDevice.findObject(By.res("com.sec.android.app.popupcalculator:id/clear_btn")).click();
        uiDevice.pressBack();
    }
}
