package com.zd.android.deam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.zd.android.deam.appDeam.ui.view.MyToolBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlActivity extends AppCompatActivity implements View.OnClickListener {

    private MyToolBar myBar;
    private WebView myWeb;
    private String htm = "<html>"
            + "<body>"
            + "图书封面<br>"
            + "<table width='200' border='1' >"
            + "<tr>"
            + "<td><a onclick='alert(\"Java Web开发速学宝典\")' ><img style='margin:10px' src='http://images.china-pub.com/ebook45001-50000/48015/cover.jpg' width='100'/></a></td>"
            + "<td><a onclick='alert(\"大象--Thinking in UML\")' ><img style='margin:10px' src='http://images.china-pub.com/ebook125001-130000/129881/zcover.jpg' width='100'/></td>"
            + "</tr>"
            + "<tr>"
            + "<td><img style='margin:10px' src='http://images.china-pub.com/ebook25001-30000/27518/zcover.jpg' width='100'/></td>"
            + "<td><img  style='margin:10px' src='http://images.china-pub.com/ebook30001-35000/34838/zcover.jpg' width='100'/></td>"
            + "</tr>" + "</table>" + "</body>" + "</html>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html);
        findView();
        init();
    }

    private void findView() {
        myBar = (MyToolBar) findViewById(R.id.test_bar);
        myWeb = (WebView) findViewById(R.id.test_web);
    }

    private void init() {
        myBar.setIBClick(this);
//        myWeb.loadData(htm, "text/html", "GBK");//直接加载HTML代码
        WebSettings settings = myWeb.getSettings();
        settings.setJavaScriptEnabled(true);//可以使用js
        settings.setDefaultTextEncodingName("GBK");//设置编码格式
        settings.setBuiltInZoomControls(false);//设置是否支持缩放
//        myWeb.addJavascriptInterface(obj,str);//向html页面注入Java对象，在Android4.2之前并未限制此方法，根据java反射机制造成攻击，所以在此版本之后则限制了js对java对象方法调用的权限（必须声明注解公共的方法才能被页面调用）

        myWeb.addJavascriptInterface(new TestGet(), "myTest");
        myWeb.setWebViewClient(new MyClient());//限制在webview中打开网页，不用默认浏览器
        myWeb.loadUrl("http://geek.csdn.net/forum/65");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_ib:
                finish();
                break;
            case R.id.right_ib:

                myWeb.loadUrl("javascript:window.myTest.showHtml('<head>'+" +
                        "document.getElementsByTagName('html')[0].innerHTML+'</head>');");

                break;
            default:
                break;
        }
    }
//    <li data-id="1"> <span class="box"></span> <span class="middle"> 广告</span> </li>
//    <li data-id="1" class="active"> <span class="box"></span> <span class="middle"> 广告</span> </li>

    private class MyClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {

            CharSequence sequence = view.getContentDescription();

            Log.d("MY_INFO", "onPageFinished: =======" + sequence);

            Log.d("TAG_WebView", "onPageFinished =============");
            view.loadUrl("javascript:window.myTest.showHtml('<head>'+" +
                    "document.getElementsByTagName('html')[0].innerHTML+'</head>');");
            super.onPageFinished(view, url);
        }
    }


    final class TestGet {
        @JavascriptInterface
        public void showHtml(String html) {
            Log.d("TAG", "showHtml: " + html);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    myBar.setTitleText("保存");
                }
            });

            //解析HTML的String类型代码
            Document parse = Jsoup.parse(html);

            //拿到form-group节点下的HTML数据
            Elements elements = parse.getElementsByClass("form-group");
//            elements.get(0).html("<p>加载完成</p>"); //修改

            int size = elements.size();

            Element element = elements.get(0);
            String s = element.toString();
            Log.d("MY_INFO", "showHtml: ========" + size + "----" + s);

            //通过ID获取到element并设置其src属性
//            Element element = parse.getElementById("imageView");
//            element.attr("src","file:///android_asset/dragon.jpg");
//
//            //通过类名获取到一组Elements，获取一组中第一个element并设置其文本
//            elements = parse.select("p.hint");
//            elements.get(0).text("您好，我是龙猫军团！");
//
//            //获取进行处理之后的字符串并重新加载
//            String body = parse.toString();
//            myWeb.loadDataWithBaseURL(null, body, "text/html", "utf-8", null);
        }

    }

}

