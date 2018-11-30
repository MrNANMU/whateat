package com.dasong.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import com.dasong.R;
import com.dasong.base.context.App;

public class AnimUtils {

    public static void toss(View coin,View background,Animation.AnimationListener listener){
        if(coin == null){
            throw new NullPointerException("硬币View不能为空");
        }
        AnimUtils.rotateX(coin,2000,true);
        if(background != null) AnimUtils.loadXml(background,R.anim.anim_scale_s2b);
        AnimUtils.loadXml(coin, R.anim.anim_scale_b2s,listener);
    }

    public static void playAnimSet(Animator.AnimatorListener listener,ObjectAnimator...oas){
        final AnimatorSet set = new AnimatorSet();
        if(oas != null){
            AnimatorSet.Builder builder = set.play(oas[0]);
            for(int i=1; i<oas.length; i++){
                builder.with(oas[i]);
            }
        }
        set.addListener(listener);
        set.start();
    }

    public static void playAnimSet(ObjectAnimator...oas){
        final AnimatorSet set = new AnimatorSet();
        if(oas != null){
            AnimatorSet.Builder builder = set.play(oas[0]);
            for(int i=1; i<oas.length; i++){
                builder.with(oas[i]);
            }
        }
        set.start();
    }

    public static ObjectAnimator scaleXBig(View v,long duration,boolean start){
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, "scaleX", 1f,2f);
        scaleX.setDuration(duration);
        if(start){
            scaleX.start();
        }
        return scaleX;
    }

    public static ObjectAnimator scaleYBig(View v,long duration,boolean start){
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, "scaleY", 1f, 2f);
        scaleY.setDuration(duration);
        if(start){
            scaleY.start();
        }
        return scaleY;
    }

    public static ObjectAnimator scaleXSmall(View v,long duration,boolean start){
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, "scaleX", 2f,1f);
        scaleX.setDuration(duration);
        if(start){
            scaleX.start();
        }
        return scaleX;
    }

    public static ObjectAnimator scaleYSmall(View v,long duration,boolean start){
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, "scaleY", 2f, 1f);
        scaleY.setDuration(duration);
        if(start){
            scaleY.start();
        }
        return scaleY;
    }

    public static ObjectAnimator rotateX(View v,long duration,boolean start){
        ObjectAnimator rotateX = ObjectAnimator.ofFloat(v,"rotationY",0,1080);
        rotateX.setDuration(duration);
        rotateX.setInterpolator(new LinearInterpolator());
        if(start){
            rotateX.start();
        }
        return rotateX;
    }

    public static void loadXml(final View v,int animXml,Animation.AnimationListener listener){
        Animation animation = AnimationUtils.loadAnimation(App.getApplication(), animXml);
        if(listener != null) animation.setAnimationListener(listener);
        v.startAnimation(animation);
    }

    public static void loadXml(final View v,int animXml){
        loadXml(v,animXml,null);
    }

    public interface AnimWrapper{

    }
}
