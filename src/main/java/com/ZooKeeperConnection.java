package com;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

public class ZooKeeperConnection {

   // declare zookeeper instance to access ZooKeeper ensemble
  static private ZooKeeper zoo;
  static final CountDownLatch connectedSignal = new CountDownLatch(1);

   // Method to connect zookeeper ensemble.
   public static ZooKeeper connect(String host) throws IOException,InterruptedException {
	
      zoo = new ZooKeeper(host,5000,new Watcher() {
		
         public void process(WatchedEvent we) {

            if (we.getState() == KeeperState.SyncConnected) {
               connectedSignal.countDown();
            }
         }
      });
		
      connectedSignal.await();
      return zoo;
   }

   // Method to disconnect from zookeeper server
   public static void close() throws InterruptedException {
      zoo.close();
   }

   public static void main(String[] args) throws IOException, InterruptedException {
      ZooKeeper connect = connect("192.168.11.46:2181");

      close();
   }
}