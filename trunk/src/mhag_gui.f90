subroutine initGui
   use mhag
   implicit none

   data_dir="./"
   method=1
   outform=1
   fileout=''
   filein=''
   io_unit=3
   fileinfo='log'
   if_info=.true.

   call read_data
   call data_pre_proc

end subroutine initGui
