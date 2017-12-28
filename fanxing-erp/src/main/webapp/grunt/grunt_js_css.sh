#!/bin/sh

tomcat_path=/home/mysoft/tomcat7

echo "***************************copy webapp to grunt  start***********************************************"
rm -rf /home/mysoft/grunt_erp/product/cwd/webapp
rm -rf /home/mysoft/grunt_erp/product/dest/webapp
cp -rf   /home/mysoft/workspce/haibao/v1.0/product/fanxing-erp/src/main/webapp  /home/mysoft/grunt_erp/product/cwd
echo "***************************copy webapp to grunt  end  ***********************************************"

echo "***************************grunt js css start  ***********************************************"
grunt
echo "***************************grunt js css end  ***********************************************"

echo "**********************************stop tomcat service********************"
cd $tomcat_path/bin
./shutdown.sh


echo "***************************copy webapp to tomcat start***********************************************"
cp -rf   /home/mysoft/grunt_erp/product/dest/webapp/resources/*  $tomcat_path/webapps/fanxing-erp/resources
cp -rf   /home/mysoft/grunt_erp/product/dest/webapp/WEB-INF/template/*  $tomcat_path/webapps/fanxing-erp/WEB-INF/template
cp -rf   /home/mysoft/grunt_erp/product/dest/webapp/login.jsp  $tomcat_path/webapps/fanxing-erp/login.jsp
echo "***************************copy webapp to tomcat end***********************************************"


echo "**********************************start tomcat service********************"
cd $tomcat_path/bin
./startup.sh

