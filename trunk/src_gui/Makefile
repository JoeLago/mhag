LIBFLAGS    = -shared -fPIC 
FCLAGS    = -O2 -c 
CCLAGS    = -O2 -c 
IDIRS  = -I/usr/lib/jvm/java-6-sun/include -I/usr/lib/jvm/java-6-sun/include/linux
BIN_DIR = ../bin_gui
OBJ_DIR = ../bin_gui/obj

OBJECT = \
			$(OBJ_DIR)/mhag_cpp.o \
			$(OBJ_DIR)/mhag.o \
			$(OBJ_DIR)/mhag_gui.o \
			$(OBJ_DIR)/main.o 

default: Mhag.class mhagFrame.class $(BIN_DIR)/libMhag.so mv_class

mhagFrame.class : mhagFrame.java
	javac -g mhagFrame.java 

Mhag.class : Mhag.java
	javac -g Mhag.java 

mv_class :
	mv *.class $(BIN_DIR)

$(BIN_DIR)/libMhag.so : $(OBJECT) 
		g++ -lgfortran $(LIBFLAGS) $(OBJECT) -o $(BIN_DIR)/libMhag.so 

$(OBJ_DIR)/mhag_cpp.o : Mhag.cpp
		g++ $(FCLAGS) $(IDIRS) Mhag.cpp -o $(OBJ_DIR)/mhag_cpp.o

$(OBJ_DIR)/mhag.o  : mhag.f90 
		gfortran $(FCLAGS) mhag.f90 -o $(OBJ_DIR)/mhag.o

$(OBJ_DIR)/mhag_gui.o  : mhag_gui.f90 
		gfortran $(FCLAGS) mhag_gui.f90 -o $(OBJ_DIR)/mhag_gui.o

$(OBJ_DIR)/main.o  : main.f90 
		gfortran $(FCLAGS) main.f90 -o $(OBJ_DIR)/main.o

clean : 
	rm -f *~ *.mod $(OBJECT) $(BIN_DIR)/libMhag.so $(BIN_DIR)/Mhag.class

