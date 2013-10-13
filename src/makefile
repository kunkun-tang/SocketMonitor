JARS = .:../lib/commons-logging-1.1.1.jar:../lib/commons-logging-1.1.1-javadoc.jar:../lib/commons-logging-1.1.1-sources.jar:../lib/commons-logging-adapters-1.1.1.jar:../lib/commons-logging-api-1.1.1.jar:../lib/commons-logging-tests.jar

JFLAGS = -d ../bin/ -cp $(JARS) 
JC = javac

.SUFFIXES : .java .class

%.class: %.java
	$(JC) $(JFLAGS) $< 

CLASSES = \
		org/cmu/ds2013s/Command.java\
		org/cmu/ds2013s/CommunicationUtil.java\
		org/cmu/ds2013s/CompositeWorkItem.java\
		org/cmu/ds2013s/DefaultLoadBalancerStrategy.java\
		org/cmu/ds2013s/DefaultSlaveChooser.java\
		org/cmu/ds2013s/Grep.java\
		org/cmu/ds2013s/HeartBeatCommand.java\
		org/cmu/ds2013s/HeartBeatSender.java\
		org/cmu/ds2013s/LoadBalancer.java\
		org/cmu/ds2013s/LoadBalancerStrategy.java\
		org/cmu/ds2013s/ManagerContext.java\
		org/cmu/ds2013s/MasterManager.java\
		org/cmu/ds2013s/MasterMessageHandler.java\
		org/cmu/ds2013s/MessageHandler.java\
		org/cmu/ds2013s/MigratableProcess.java\
		org/cmu/ds2013s/MigrateSendCommand.java\
		org/cmu/ds2013s/MigrateSourceCommand.java\
		org/cmu/ds2013s/MigrationTask.java\
		org/cmu/ds2013s/NetworkListener.java\
		org/cmu/ds2013s/NewJobCommand.java\
		org/cmu/ds2013s/ProcessManager.java\
		org/cmu/ds2013s/ProcessMeta.java\
		org/cmu/ds2013s/SlaveAliveChecker.java\
		org/cmu/ds2013s/SlaveChooserStrategy.java\
		org/cmu/ds2013s/SlaveManager.java\
		org/cmu/ds2013s/SlaveMessageHandler.java\
		org/cmu/ds2013s/SlaveMeta.java\
		org/cmu/ds2013s/TransactionFileInputStream.java\
		org/cmu/ds2013s/TransactionFileOutputStream.java\
		org/cmu/ds2013s/WebpageCrawler.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	rm *~; rm -rf ../bin/*
