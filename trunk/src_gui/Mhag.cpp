#include <jni.h>
#include "Mhag.h"

extern "C" void init_gui_();
extern "C" void mhag_proc_();

JNIEXPORT void JNICALL Java_Mhag_mhagInit
(JNIEnv *env, jobject)
{
   init_gui_();
};

JNIEXPORT void JNICALL Java_Mhag_mhagProc
(JNIEnv *env, jobject)
{
   mhag_proc_();
};
