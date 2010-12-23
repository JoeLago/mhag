#include <jni.h>
#include "Mhag.h"

extern "C" void init_gui_();
extern "C" void mhag_proc_();
extern "C" void gen_menu_list_(jboolean*lowrank, jboolean*blader, jintArray* num_list, jobjectArray* menus, jobjectArray* slots);

JNIEXPORT void JNICALL Java_Mhag_mhagInit
(JNIEnv *env, jobject)
{ init_gui_();
};

JNIEXPORT void JNICALL Java_Mhag_mhagProc
(JNIEnv *env, jobject)
{
   mhag_proc_();
};

JNIEXPORT void JNICALL Java_Mhag_genMenuList
  (JNIEnv *, jobject, jboolean lowrank, jboolean blader, jintArray num_list, jobjectArray menus, jobjectArray slots)
{
   int c_num_list[9];
   char c_menus[9][300][40];
   int c_slots[9][300];

   gen_menu_list_(&lowrank, &blader, c_num_list,c_menus,c_slots);

};
