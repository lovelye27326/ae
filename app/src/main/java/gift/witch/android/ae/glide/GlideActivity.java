package gift.witch.android.ae.glide;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;

import gift.witch.android.ae.R;
import gift.witch.android.ae.base.BaseCompatActivity;
import gift.witch.glide.GlideApp;
import gift.witch.glide.MyAnimator;
import gift.witch.glide.MyBlurTransformation;
import gift.witch.glide.Photo;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


/**
 *
 *
 */
public class GlideActivity extends BaseCompatActivity implements View.OnClickListener {


    private final static String TAG = "GlideActivity";

    private String url = "http://img.ivsky.com/img/tupian/pre/201707/24/meilidexiangcunmeijingtupian-003.jpg";
    private String urlgifUrl = "http://img.qqzhi.com/upload/img_4_1566750187D3165349633_23.jpg";
    private String gif = "http://pic27.nipic.com/20130323/12013739_171719485183_2.gif";
    private String urlHttps = "https://thumbs.dreamstime.com/z/https%E4%BA%92%E8%81%94%E7%BD%91-16357064.jpg";
    private ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7;
    private Button mClearBTN,mStopBTN,mResumeBTN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_glide);

        mClearBTN = (Button) findViewById(R.id.clear);
        mClearBTN.setOnClickListener(this);
        mStopBTN = (Button) findViewById(R.id.stop);
        mStopBTN.setOnClickListener(this);
        mResumeBTN = (Button) findViewById(R.id.resume);
        mResumeBTN.setOnClickListener(this);
        imageView1 = (ImageView) findViewById(R.id.image1);
        imageView2 = (ImageView) findViewById(R.id.image2);
        imageView3 = (ImageView) findViewById(R.id.image3);
        imageView4 = (ImageView) findViewById(R.id.image4);
        imageView5 = (ImageView) findViewById(R.id.image5);
        imageView6 = (ImageView) findViewById(R.id.image6);
        imageView7 = (ImageView) findViewById(R.id.image7);




        Glide.with(this)//获取上下文环境
                .load(urlHttps)//图片地址
                .into(imageView1);//图片显示的地方

        Glide.with(this).load(R.drawable.hsk1).into(imageView1);

        File file = new File("图片地址");
        Glide.with(this).load(file).into(imageView1);


        Uri uri = Uri.parse(url);
        Glide.with(this).load(uri).into(imageView1);

        GlideApp.with(this).load(urlHttps)
                .placeholder(R.drawable.image320)//加载的时候占位
                .error(new ColorDrawable(Color.BLUE))//请求资源失败的时候
                .fallback(new ColorDrawable(Color.CYAN))//当请求内容为null的时候显示
                .into(imageView2);

        RequestOptions requestOptions = new RequestOptions();
        //占位符
        requestOptions.placeholder(R.drawable.image320);
        requestOptions.error(new ColorDrawable(Color.BLUE));
        requestOptions.fallback(new ColorDrawable(Color.CYAN));
        //转换
        requestOptions.transform(new RoundedCorners(20));
        //缓存策略
        requestOptions.skipMemoryCache(false);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        //
        requestOptions.encodeFormat(Bitmap.CompressFormat.WEBP);//图片格式
        requestOptions.encodeQuality(90);//图片质量
        requestOptions.format(DecodeFormat.PREFER_RGB_565);//图片模式
        requestOptions.override(40, 40);//图片限制大小
        //requestOptions.signature(Key.CHARSET);


        requestOptions.dontTransform();//禁止转换
        requestOptions.dontAnimate();//禁止动画化

        /**
         * 图片转换
         */
        requestOptions.centerInside();
        requestOptions.centerCrop();
        requestOptions.circleCrop();
        requestOptions.fitCenter();


        GlideApp.with(this).load(url)
                .apply(requestOptions)
                .into(imageView3);

        /**
         * 不显示到ImageView里
         */
        SimpleTarget<Bitmap> simpleTarget = new SimpleTarget<Bitmap>(100, 100) {

            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                imageView4.setImageBitmap(resource);
            }
        };

        MultiTransformation multi = new MultiTransformation(
                new BlurTransformation(25),
                new RoundedCornersTransformation(128, 0, RoundedCornersTransformation.CornerType.BOTTOM));


        GlideApp.with(this).asBitmap()
                .load(urlHttps)
                .into(simpleTarget);


        Photo photo = new Photo();
        photo.setUrl(url);
        GlideApp.with(this).load(photo)
                .into(imageView5);


        /**
         * 过渡动画
         */
        BitmapTransitionOptions bitmapTransitionOptions = new BitmapTransitionOptions();
        //bitmapTransitionOptions.transition(R.anim.glide_animate);
        bitmapTransitionOptions.transition(new MyAnimator());


        GlideApp.with(this).asBitmap().load(url)
                .transition(bitmapTransitionOptions)
                .into(imageView6);

        GlideApp.with(this).load(url).thumbnail(0.3f);


        RequestOptions requestOptions1 = new RequestOptions();
        requestOptions1.transform(new MyBlurTransformation(this));
        GlideApp.with(this)
                .load(url)
                .apply(RequestOptions.bitmapTransform(multi))
                //.apply(requestOptions1)
                .into(imageView7);

        //清除内存缓存(需要在UI线程里调用)
        //GlideApp.get(this).clearMemory();
        //清除磁盘缓存(需要在子线程里调用)
        //GlideApp.get(this).clearDiskCache();

        //GlideApp.with(this).pauseRequests();
    }

    @Override
    protected void onStart() {
        super.onStart();
        GlideApp.with(this).pauseRequests();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.resume){
            GlideApp.with(this).resumeRequests();
        }else if (v.getId() == R.id.stop){
            GlideApp.with(this).pauseRequests();
        }else if(v.getId() == R.id.clear){
            GlideApp.get(this).clearMemory();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    GlideApp.get(GlideActivity.this).clearDiskCache();
                }
            }).start();

        }
    }
}
