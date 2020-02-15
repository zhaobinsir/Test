# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#Gson-----------------
#-keep class com.google.gson.examples.android.model.** { <fields>; }

# Prevent proguard from stripping interface information from TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

#广告sdk-----------------------------------
# glide 的混淆代码
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
# banner 的混淆代码
-keep class com.youth.banner.** {
    *;
 }

 #wx
 -keep class com.tencent.mm.opensdk.** {
     *;
 }
 -keep class com.tencent.wxop.** {
     *;
 }
 -keep class com.tencent.mm.sdk.** {
     *;
 }

#注解不被混淆
 -keepattributes *Annotation*
 -keep class * extends java.lang.annotation.Annotation { *; }

 # Gson
 -keep class com.google.gson.stream.** { *; }
 -keepattributes EnclosingMethod
 -keep class com.reward.togo.bean.net.**{*;}

 #okhttp3
 -dontwarn com.squareup.okhttp3.**
 -keep class com.squareup.okhttp3.** { *;}
 -keep class okhttp3.** { *;}
 -keep class okio.** { *;}
 -dontwarn sun.security.**
 -keep class sun.security.** { *;}
 -dontwarn okio.**
 -dontwarn okhttp3.**

 -optimizations !code/simplification/cast,!field/*,!class/merging/*
 -keep public class * extends android.view.View{
     *** get*();
     void set*(***);
     public <init>(android.content.Context);
     public <init>(android.content.Context, android.util.AttributeSet);
     public <init>(android.content.Context, android.util.AttributeSet, int);
 }


 -keep class com.android.tiny.tinyinterface.**{*;}
 -keep class com.android.tiny.bean.**{*;}
 -keep class com.android.tiny.TinySdk{*;}
 -keep class com.android.tiny.mgr.DataMgr{*;}
