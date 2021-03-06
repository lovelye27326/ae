package gift.witch.android.ae.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.RectF;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import gift.witch.android.ae.R;
import gift.witch.android.ae.base.BaseCompatActivity;

/**
 * https://iamludaxu.gitbooks.io/android/content/dong-hua.html
 */
public class AnimationActivity extends BaseCompatActivity implements View.OnClickListener {


    private Button mBtn;
    private Button mBtn1;
    private Button mBtn2;
    private Button mBtn3;
    private Button mBtn4;
    private Button mBtn5;
    private TextView mBgTV;
    private Button mBtn6;
    private TextView mStringTV;
    private Button mBtn7;
    private EllipseAnimationView mEllipseAnimationView;
    private Button mBtn8;
    private RectFAnimationView mRectFAnimationView;
    private Button mBtn9;
    private View mKeyFrame;
    private Button mBtn10;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_animation);
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);
        mBtn1 = (Button) findViewById(R.id.btn1);
        mBtn1.setOnClickListener(this);
        mBtn2 = (Button) findViewById(R.id.btn2);
        mBtn2.setOnClickListener(this);
        mBtn3 = (Button) findViewById(R.id.btn3);
        mBtn3.setOnClickListener(this);
        mBtn4 = (Button) findViewById(R.id.btn4);
        mBtn4.setOnClickListener(this);
        mBtn5 = (Button) findViewById(R.id.btn5);
        mBtn5.setOnClickListener(this);
        mBtn6 = (Button) findViewById(R.id.btn6);
        mBtn6.setOnClickListener(this);

        mBgTV = (TextView) findViewById(R.id.bg);
        mBgTV.setBackgroundResource(R.drawable.frame_anim);
        mBgTV.setOnClickListener(this);
        mStringTV = (TextView) findViewById(R.id.string_tv);

        mBtn7 = (Button) findViewById(R.id.btn7);
        mBtn7.setOnClickListener(this);
        mEllipseAnimationView = (EllipseAnimationView)findViewById(R.id.ellipseview);

        mBtn8 = (Button) findViewById(R.id.btn8);
        mBtn8.setOnClickListener(this);
        mRectFAnimationView = (RectFAnimationView)findViewById(R.id.rectview);

        mBtn9 = (Button) findViewById(R.id.btn9);
        mBtn9.setOnClickListener(this);
        mKeyFrame = findViewById(R.id.keyframe);

        mBtn10 = (Button) findViewById(R.id.btn10);
        mBtn10.setOnClickListener(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn) {

            /**
             * ObjectAnimator
             */
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mBtn, "rotationX", 0.0F, 360.0F)
                    .setDuration(500);
            objectAnimator.setInterpolator(new AccelerateInterpolator());
            objectAnimator.start();

        } else if (v.getId() == R.id.btn1) {

            /**
             * ValueAnimator
             */
            ValueAnimator animator = ValueAnimator.ofFloat(0, 360.0F);

            /**
             * 更新动画
             */
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mBtn1.setRotationX((Float) animation.getAnimatedValue());
                }
            });

            /**
             * 监听动画变化
             */
            animator.addListener(new ValueAnimator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    Logger.i("onAnimationStart");
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    Logger.i("onAnimationEnd");
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    Logger.i("onAnimationCancel");
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                    Logger.i("onAnimationRepeat");
                }
            });

            /**
             * 设置重复次数
             */
            animator.setRepeatCount(2);

            animator.setDuration(500).start();


        } else if (v.getId() == R.id.btn2) {

            /**
             * PropertyValuesHolder
             */
            PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("rotationX", 0f, 360f);
            ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mBtn2, pvhZ);
            objectAnimator.setDuration(500).start();


        } else if (v.getId() == R.id.btn3) {

            /**
             * AnimatorSet
             */
            ObjectAnimator animatorA = ObjectAnimator.ofFloat(mBtn3, "TranslationX", -300, 300, -400);
            ObjectAnimator animatorB = ObjectAnimator.ofFloat(mBtn2, "scaleY", 0.5f, 1.5f, 1f);
            ObjectAnimator animatorC = ObjectAnimator.ofFloat(mBtn1, "rotation", 0, 270, 90, 180, 0);

            AnimatorSet animatorSet3 = new AnimatorSet();
            animatorSet3.play(animatorA).after(animatorC).before(animatorB);
            animatorSet3.setDuration(3 * 1000);
            animatorSet3.start();


        } else if (v.getId() == R.id.btn4) {
            ValueAnimator animator = ValueAnimator.ofFloat(0, 100.0f);
            animator.setInterpolator(new MyInterpolator());
            animator.setEvaluator(new MyTypeEvaluator());
            /**
             * IntEvaluator
             */
            //animator.setEvaluator(new IntEvaluator());
            //animator.setEvaluator(new FloatEvaluator());
            //animator.setEvaluator(new ArgbEvaluator());
            animator.setDuration(2500);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mBtn4.setTranslationX((Float) animation.getAnimatedValue());
                }
            });
            animator.start();

        } else if (v.getId() == R.id.btn5) {


            /**
             * 加载补间动画
             */
            //AnimationUtilsloadAnimation()

            /**
             *
             * 这里所有的动作都只是改变图形上的变化，不会改变具体的内容
             *
             * 比如一个按钮移动到另外一个位置，表面上在另外一个位置其实触发点还是在原来的位置
             *
             *************/
            /**
             * Animation
             */
            Animation animation = new ScaleAnimation(1, 0.5f, 1, 0.5f);
            /**
             * 设置时间
             */
            animation.setDuration(3000);
            /**
             * 停留在最后的状态
             */
            animation.setFillAfter(true);
            //animation.start();

            Animation animation2 = new TranslateAnimation(0, 100, 0, 100);
            animation2.setDuration(3000);
            /**
             * 重复次数 默认是0
             */
            animation2.setRepeatCount(1);
            /**
             * 停留在初始状态
             */
            animation2.setFillBefore(true);

            AnimationSet animationSet = new AnimationSet(true);
            animationSet.setFillAfter(true);
            animationSet.addAnimation(animation);
            animationSet.addAnimation(animation2);

            /**
             * 启动动画组合
             */
            mBtn5.startAnimation(animationSet);

        } else if (v.getId() == R.id.bg) {
            AnimationDrawable animation = (AnimationDrawable) mBgTV.getBackground();
            /**
             * 增加帧
             */
            //animation.addFrame();
            /**
             * 启动动画
             */
            animation.start();
            /**
             * 停止动画
             */
            animation.stop();
        } else if (v.getId() == R.id.btn6) {
            /**
             * 将A全部替换为B
             */
            String startString = "AAAAAAAAAAAAAAAAAAAAAAAAAA";
            String endString =   "BBBBBBBBBBBBBBBBBBBBBBBBBB";
            ValueAnimator animator = ValueAnimator.ofObject(new StringEvaluator(), startString, endString);
            animator.setDuration(2500);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mStringTV.setText((String) animation.getAnimatedValue());
                }
            });
            animator.start();
        }else if(v.getId() == R.id.btn7){
            /**
             * 启动动画
             */
            mEllipseAnimationView.doAnim();
        }else if(v.getId() == R.id.btn8){
            /**
             * 设置动画
             */
            RectF end = new RectF();
            end.set(0, 50, 0, 150);
            ObjectAnimator objectAnimator = ObjectAnimator.ofObject(mRectFAnimationView, "rectF",new RectFEvaluator(), end)
                    .setDuration(500);
            objectAnimator.setInterpolator(new BounceInterpolator());
            objectAnimator.start();
        }else if(v.getId() == R.id.btn9){
            Keyframe frame0 = Keyframe.ofFloat(0f, 0);
            Keyframe frame1 = Keyframe.ofFloat(0.1f, -20f);
            Keyframe frame2 = Keyframe.ofFloat(0.2f, 20f);
            Keyframe frame3 = Keyframe.ofFloat(0.3f, -20f);
            Keyframe frame4 = Keyframe.ofFloat(0.4f, 20f);
            Keyframe frame5 = Keyframe.ofFloat(0.5f, -20f);
            Keyframe frame6 = Keyframe.ofFloat(0.6f, 20f);
            Keyframe frame7 = Keyframe.ofFloat(0.7f, -20f);
            Keyframe frame8 = Keyframe.ofFloat(0.8f, 20f);
            Keyframe frame9 = Keyframe.ofFloat(0.9f, -20f);
            Keyframe frame10 = Keyframe.ofFloat(1, 0);
            PropertyValuesHolder frameHolder = PropertyValuesHolder.ofKeyframe("rotation",frame0,frame1,frame2,frame3,frame4,frame5,frame6,frame7,frame8,frame9,frame10);

            Animator animator = ObjectAnimator.ofPropertyValuesHolder(mKeyFrame,frameHolder);
            animator.setDuration(1000);
            animator.start();

        }else if(v.getId() == R.id.btn10){
            ObjectAnimator tv1BgAnimator = ObjectAnimator.ofInt(mBtn10, "BackgroundColor",  0xffff00ff, 0xffffff00, 0xffff00ff);
            /**
             * 延时
             */
            tv1BgAnimator.setStartDelay(1000);
            ObjectAnimator tv1TranslateY = ObjectAnimator.ofFloat(mBtn10, "translationY", 0, 300, 0);
            ObjectAnimator tv2TranslateY = ObjectAnimator.ofFloat(mBtn10, "rotation", 0, 270, 90, 180, 0);

            AnimatorSet animatorSet = new AnimatorSet();


            /**
             * 按序列执行动画
             */
            //playSequentially(Animator... items);
            //playSequentially(List<Animator> items);
            animatorSet.playSequentially(tv1BgAnimator,tv1TranslateY,tv2TranslateY);


            /**
             *同时执行
             */
            //playTogether(Animator... items);
            //playTogether(Collection<Animator> items);
            //animatorSet.playTogether(tv1BgAnimator,tv1TranslateY,tv2TranslateY);



            /**
             *t 通过AnimatorSet.Builder设置动画顺序
             */
            AnimatorSet.Builder builder = animatorSet.play(tv1BgAnimator);
            builder.with(tv1TranslateY);

            /**
             //和前面动画一起执行
             public Builder with(Animator anim)
             //执行前面的动画后才执行该动画
             public Builder before(Animator anim)
             //执行先执行这个动画再执行前面动画
             public Builder after(Animator anim)
             //延迟n毫秒之后执行动画
             public Builder after(long delay)
             */
            animatorSet.setDuration(1000);
            animatorSet.start();


        }
    }
}
