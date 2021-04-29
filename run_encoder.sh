# delete previous resources
rm -f sources.txt
find * -name "*.class" -delete

# compile new
find . -name '*.java' > sources.txt
javac -d . @sources.txt

# run
java com.encoder.Main $1