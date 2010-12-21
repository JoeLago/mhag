#include <jni.h>
#include "Mhag.h"

extern "C" void initGui_();
extern "C" void mhag_proc_();

JNIEXPORT void JNICALL Java_Mhag_mhagInit
(JNIEnv *env, jobject)
{
   initGui_();
};

JNIEXPORT void JNICALL Java_Mhag_mhagProc
(JNIEnv *env, jobject)
{
   mhag_proc_();
};
