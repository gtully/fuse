A-MQ JMS Quick Start
===========================

This is an example of using JMS with [JBoss A-MQ].

[JBoss A-MQ] 6.2.0 Setup
------------------------

* Start A-MQ
 > ./bin/amq

* From the A-MQ shell, configure user named `admin` with password `admin`.
  > \> amq:create-admin-user --new-user-role admin --new-user admin --new-user-password admin


Run the HelloWorld example
--------------------------

From the command line, use [Apache Maven] to build this project and copy the dependencies
alongside their output:

 > mvn clean package dependency:copy-dependencies -DincludeScope=runtime -DskipTests

Now you can run the examples from the command line using java commands of the format:

Linux:
 > java -cp "target/classes/:target/dependency/*" org.jboss.amq.quickstart.jms.HelloWorld

Windows:
 > java -cp "target\classes\;target\dependency\*" org.jboss.amq.quickstart.jms.HelloWorld


The code sends a `Hello world!` message to a Queue named `queue`, receives the same message and
 prints it to the console.

Using the A-MQ shell you can note the enqueue and dequeue count using the destination
 statistics (dstat) command
  > \> activemq:dstat queues


Use two java processes, Sender and Receiver
-------------------------------------------

First produce some messages from the command line using the sender class:

 > java -cp "target/classes/:target/dependency/*" org.jboss.amq.quickstart.jms.Sender

Peek at the broker to see the Enqueue count and Queue size:
 > \> activemq:dstat queues

Stop and restart A-MQ to experience message durability, the Sender uses
 the PERSISTENT JMS delivery mode for its messages so they survive a restart.

 > \> osgi:shutdown
 
 > ./bin/amq
 
 > \> activemq:dstat queues

Receive the messages using the Receiver class:

 > java -cp "target/classes/:target/dependency/*" org.jboss.amq.quickstart.jms.Receiver


[JBoss A-MQ]: https://www.jboss.org/products/amq.html
[Apache Maven]: http://maven.apache.org
