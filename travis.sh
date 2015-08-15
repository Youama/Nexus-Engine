#!/bin/bash

# author: David Belicza
# Javadoc generator and publisher script for Nexus Engine

# highlight
HGL='\e[93m'
# end of color
NC='\033[0m' 

# adding a virtual user
git config --global user.email "robot@youama.com"
git config --global user.name "Mr. Robot"

echo -e "\n${HGL}GENERATING OF JAVA DOCS${NC}\n"

# re-create the directory for the old repo
rm -rf javadoc_old
mkdir javadoc_old

# get the Java version.
# Java 1.5 will give 15.
# Java 1.6 will give 16.
# Java 1.7 will give 17.
# Java 1.8 will give 18.
VER=`java -version 2>&1 | sed 's/java version "\(.*\)\.\(.*\)\..*"/\1\2/; 1q'`

# only when JAVA SE (Oracle JDK 8)
if [ $VER == "18" ]
then
	echo -e "\n${HGL}...looking for the nexus-engine-javadoc repo${NC}\n"
	# clone the javadoc repo to the previously created directory
	cd javadoc_old
	git clone https://DoveID:$UTOKEN@github.com/Youama/nexus-engine-javadoc.git -q &> /dev/null
	cd ..

	# remove the local javadoc static files
	rm -rf javadoc/apidocs

	echo -e "\n${HGL}...create new JAVA doc${NC}\n"
	# generate javadoc from the source code
	mvn javadoc:javadoc

	if [ -d "javadoc/apidocs" ]
	then
		# copy the old repo headers to the newly generated javadoc
		cp javadoc_old/nexus-engine-javadoc/.gitignore javadoc/apidocs/.gitignore
		cp -r javadoc_old/nexus-engine-javadoc/.git javadoc/apidocs/.git

		# remove the iframe from javadoc
		cd javadoc/apidocs
		rm index.html
		cp index-all.html index.html

		echo -e "\n${HGL}...publishing the new JAVA doc${NC}\n"
		# publish the new docusmentation
		git add .
		git add -u .
		git commit -m "Auto commit from Nexus Engine build" &> /dev/null
		git push -q &> /dev/null

		echo -e "\n${HGL}JAVA doc has been generated and buplished automatically${NC}\n\n"
		echo -e "2015 ${HGL}Nexus Engine${NC}\n"
	else
		echo -e "\n${HGL}ERROR: JAVA doc is not created${NC}\n"
	fi
fi
