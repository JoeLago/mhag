subroutine init_gui
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

end subroutine init_gui

! generate menu list : 1-5: armor; 6: charm; 
! 7: 1-slot jewels; 8: 1,2-slot jewel;
! 9: 1,2,3 jewels

subroutine gen_menu_list(lowrank,blader,num_list,menulist,slotlist)
   use mhag
   implicit none
   logical,intent(in) :: lowrank,blader
   integer,intent(out) :: num_list(9)
   character(len=40),intent(out) :: menulist(300,9)
   integer,intent(out) :: slotlist(300,9)

   integer :: i,j,n
   type(armor_type) :: armor
   type(charm_type) :: charm
   type(jewel_type) :: jewel
   character(len=255) :: charm_name

   num_list=0
   menulist=''
   slotlist=0

   ! armor list
   do i=1,5
      do j=1,num_armor(i)
         armor=armor_list(j,i)
         if((armor%blade_or_gunner.eq."B").and.(.not.blader))cycle
         if((armor%blade_or_gunner.eq."G").and.blader)cycle
         if(lowrank.and.(.not.armor%lowrank))cycle
         num_list(i)=num_list(i)+1
         menulist(num_list(i),i)=armor%armor_name
         slotlist(num_list(i),i)=armor%num_slot
      enddo
   enddo

   ! charm list
   do j=1,num_charm
      charm=charm_list(j)
      if(lowrank.and.(.not.charm%lowrank))cycle
      num_list(6)=num_list(6)+1
      call write_charm_class(j,charm_name)
      menulist(num_list(6),6)=trim(charm_name)
      slotlist(num_list(6),6)=charm%num_slot
   enddo

   ! jewel_list
   do j=1,num_jewel
      jewel=jewel_list(j)
      n=jewel%num_slot
      if(n.eq.1)then
         num_list(7)=num_list(7)+1
         menulist(num_list(7),7)=jewel%jewel_name
      endif
      if(n.le.2)then
         num_list(8)=num_list(8)+1
         menulist(num_list(8),8)=jewel%jewel_name
      endif
      num_list(9)=num_list(9)+1
      menulist(num_list(9),9)=jewel%jewel_name
   enddo

end subroutine gen_menu_list
