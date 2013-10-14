
web = www.yahoo.com
output = output.txt
all:
	mkdir bin; cd src; make

master:
	java -cp bin/:lib/commons-logging-1.1.1.jar:lib/commons-logging-1.1.1-javadoc.jar:lib/commons-logging-1.1.1-sources.jar:lib/commons-logging-adapters-1.1.1.jar:lib/commons-logging-api-1.1.1.jar:lib/commons-logging-tests.jar org/cmu/ds2013s/ProcessManager $(PORT)

slave:
	java -cp bin/:lib/commons-logging-1.1.1.jar:lib/commons-logging-1.1.1-javadoc.jar:lib/commons-logging-1.1.1-sources.jar:lib/commons-logging-adapters-1.1.1.jar:lib/commons-logging-api-1.1.1.jar:lib/commons-logging-tests.jar org/cmu/ds2013s/ProcessManager $(PORT) -c $(MHOST):$(MPORT)

webcrawler:
	java -cp bin/:lib/commons-loggilng-1.1.1.jar:lib/commons-logging-1.1.1-javadoc.jar:lib/commons-logging-1.1.1-sources.jar:lib/commons-logging-adapters-1.1.1.jar:lib/commons-logging-api-1.1.1.jar:lib/commons-logging-tests.jar org/cmu/ds2013s/WebpageCrawler $(web) $(output)

protoc:
	protoc -I=. --java_out=./src ./command.proto

clean:
	cd src; make clean

readProtoc:
	java -cp bin/:lib/protobuf-java-2.5.0.jar  org/liang/AddMachine
