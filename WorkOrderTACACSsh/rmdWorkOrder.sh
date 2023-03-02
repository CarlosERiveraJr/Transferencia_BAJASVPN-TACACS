#VRS: 1.0
#PRG: REV
#FCH: 21/02/2018-28/02/2018

#Variables de entorno
DIR_HOME=/webapps/WorkOrderTACACS/WEB-INF #Directorio HOME
DIR_CLASSES=$DIR_HOME/classes #Directorio de clases
DIR_LIB=$DIR_HOME/lib #Directorio de librerias




##JAVA_HOME=/usr/java/jdk1.7.0_76/ #jdk 1.6
#JAVA_HOME=/usr/lib/jvm/jre-1.7.0-openjdk.x86_64
JAVA_HOME=/usr/lib/jvm/jre/

PATH="$DIR_CLASSES:$DIR_LIB:$JAVA_HOME:$JAVA_HOME/bin:$JAVA_HOME/lib:$PATH" #Variable PATH
CLASSPATH="./:$DIR_CLASSES:$DIR_LIB" #Variable CLASSPATH
LD_LIBRARY_PATH="$DIR_CLASSES:$DIR_LIB" #Variable LD_LIBRARY_PATH

export DIR_HOME DIR_LIB JAVA_HOME PATH CLASSPATH LD_LIBRARY_PATH

export LANG=es_MX.iso88591

#Ejecucion de comando
vsResultado=`java -Xms128m -Xmx1024m  -Duser.timezone=Mexico/General -Dfile.encoding=UTF-8  -Djava.ext.dirs="$DIR_LIB" com.telcel.csv.controllers.ControllerP "$1"`
echo $vsResultado

