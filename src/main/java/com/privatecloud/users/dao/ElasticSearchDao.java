package com.privatecloud.users.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

public class ElasticSearchDao {
	
	private static Client client;
	//String lastSearchTimeStamp = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(new Date());
	private static String lastSearchTimeStamp = "2015-04-25T02:58:00Z";
	
	@SuppressWarnings("resource")
	private static Client getClient() {
		client = new TransportClient()
        .addTransportAddress(new InetSocketTransportAddress("52.8.22.34", 9300));
		return client;	
	}
	
	private static void closeClient() {
		client.close();
	}
	
	public static List<Map<String,Object>> searchLogs(String vmName) {
		List<Map<String,Object>> searchResult = new ArrayList<Map<String,Object>>();
		SearchResponse response = getClient().prepareSearch("logstash-*")
		        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
		        .setQuery(QueryBuilders.matchQuery("vmhost", vmName))
		        .setPostFilter(FilterBuilders.rangeFilter("timestamp").from(lastSearchTimeStamp))
		        .setFrom(0).setSize(60).setExplain(true)
		        .execute()
		        .actionGet();
		
		SearchHit[] results = response.getHits().getHits();
        
        for (SearchHit hit : results) {
            searchResult.add(hit.getSource());
        }
		closeClient();
		return searchResult;
	}
	
	
	public static void main(String[] args) {
		List<Map<String,Object>> data = searchLogs("T12-VM01-Ubu");
		for (Map<String, Object> map : data) {
			System.out.println(map);
		}
	}
	
	
	public static void checkAndUpdateSystemUsage() {
//		Map<String, Long> vmList = VmStatisticsDao.getVMs();
//		Map<String, Long> vmStatProperties = VmStatisticsDao.getVmStatProperties();
//		
//		for(String vm : vmList.keySet()) {
//			
//			Map<String, Long> vmPropertySet = VmStatisticsDao.getVMPropertyThresholdValues(vm);
//			List<Map<String,Object>> searchResult = searchLogs(vm);
//			
//			for(Map<String,Object> resultEntry : searchResult) {
//				
//				for(String property: vmStatProperties.keySet()) {
//					
//					if(vmPropertySet.containsKey(property)) {
//						
//						long threshold = vmPropertySet.get(property);
//						long logValue = ((Integer)resultEntry.get(property.toLowerCase())).longValue();
//						//System.out.println(property + " threshold value - " + threshold + " | log value - " + logValue);
//						
//						if(logValue < threshold) {
//							if(VmStatisticsDao.isVmPropertyLimitExceeded(vmList.get(vm), vmStatProperties.get(property)))
//								VmStatisticsDao.setVmPropertyLimitExceeded(vmList.get(vm), vmStatProperties.get(property), false);
//						} else {
//							VmStatisticsDao.setVmPropertyLimitExceeded(vmList.get(vm), vmStatProperties.get(property), true);
//						}
//						
//					} else {
//						//System.out.println(property + " threshold not set for " + vm);
//					}
//				}
//			}
//		}
//		lastSearchTimeStamp = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(new Date());
	}

}
