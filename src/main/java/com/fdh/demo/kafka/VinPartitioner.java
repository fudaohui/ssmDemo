/*
 * 文件名： VinPartitioner.java
 * 
 * 创建日期： 2017年2月24日
 *
 * Copyright(C) 2017, by <a href="mailto:liws@xingyuanauto.com">liws</a>.
 *
 * 原始作者: liws
 *
 */
package com.fdh.demo.kafka;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;

/**
 * 根据VIN码 进行车辆Topic分区
 *
 * @author <a href="mailto:liws@xingyuanauto.com">liws</a>
 *
 * @version $Revision$
 *
 * @since 2017年2月24日
 */
public class VinPartitioner implements Partitioner {
	

	public VinPartitioner(){
		
	}

//	@Override
	public int partition(Object key, int partitionSize) {
		int hCode = key.hashCode();
		int partitionIndex = Math.abs(hCode)%partitionSize;
		return partitionIndex;
	}

	public static void main(String[] args) {
		System.out.println(new VinPartitioner().partition("LNBSCB3F2JW179140", 3));
		System.out.println(new VinPartitioner().partition("661D0000000000002", 3));
	}

	@Override
	public void configure(Map<String, ?> configs) {
		
	}

	@Override
	public int partition(String topic, Object key, byte[] keyBytes,
			Object value, byte[] valueBytes, Cluster cluster) {
		List<PartitionInfo> list = cluster.availablePartitionsForTopic(topic);
		
		if(list.size()>0){
			//TODO partition:2 故障，所以进行分区算法是-1，生产环境需去掉
			int listSize = list.size()-1;
			int hCode = key.hashCode();
			int partitionIndex = Math.abs(hCode)%listSize;
			return partitionIndex;
		}
		return 0;
		
		
	}

	@Override
	public void close() {
		
	}
}
