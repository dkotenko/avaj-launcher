# delete previous resources
rm -f sources.txt simulation.txt
find * -name "*.class" -delete

# compile new
find . -name '*.java' > sources.txt
javac -d . @sources.txt

# run
java com.school21.Main $1 $2