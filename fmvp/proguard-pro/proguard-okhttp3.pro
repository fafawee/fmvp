#com.squareup.okhttp3:okhttp V3.4.2

-keepattributes Signature
-keepattributes *Annotation*



#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}
-keep interface okhttp3.** { *; }

#okio
-dontwarn okio.**
-keep class okio.**{*;}