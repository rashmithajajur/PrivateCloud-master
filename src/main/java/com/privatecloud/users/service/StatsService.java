package com.privatecloud.users.service;

/*================================================================================
Copyright (c) 2008 VMware, Inc. All Rights Reserved.

Redistribution and use in source and binary forms, with or without modification, 
are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, 
this list of conditions and the following disclaimer.

 * Redistributions in binary form must reproduce the above copyright notice, 
this list of conditions and the following disclaimer in the documentation 
and/or other materials provided with the distribution.

 * Neither the name of VMware, Inc. nor the names of its contributors may be used
to endorse or promote products derived from this software without specific prior 
written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
IN NO EVENT SHALL VMWARE, INC. OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, 
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT 
LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE.
================================================================================*/


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.privatecloud.users.dto.VMStatsDTO;
import com.vmware.vim25.HostListSummaryQuickStats;
import com.vmware.vim25.HostRuntimeInfo;
import com.vmware.vim25.ObjectSpec;
import com.vmware.vim25.ObjectUpdate;
import com.vmware.vim25.PropertyChange;
import com.vmware.vim25.PropertyChangeOp;
import com.vmware.vim25.PropertyFilterSpec;
import com.vmware.vim25.PropertyFilterUpdate;
import com.vmware.vim25.PropertySpec;
import com.vmware.vim25.UpdateSet;
import com.vmware.vim25.VirtualMachineQuickStats;
import com.vmware.vim25.VirtualMachineRuntimeInfo;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.ManagedObject;
import com.vmware.vim25.mo.PropertyCollector;
import com.vmware.vim25.mo.PropertyFilter;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.VirtualMachine;
import com.vmware.vim25.mo.util.OptionSpec;
import com.vmware.vim25.mo.util.PropertyCollectorUtil;

public class StatsService 
{
	static String cpu = null;
	static String mem = null;
	static ArrayList<String> q1 = new ArrayList<>(0);
	static ArrayList<VMStatsDTO> vmDtoList = new ArrayList<>(0);
	
	private static Logger LOGGER = LoggerFactory.getLogger("StatsService");
	
	public static ArrayList<VMStatsDTO> status()
	{



		try{
			ServiceInstance si = new ServiceInstance(new URL("https://130.65.132.112/sdk"), "administrator" , "12!@qwQW", true);
			Folder rootFolder = si.getRootFolder();

			LOGGER.info("============ Data Centers ============");
			ManagedEntity[] dcs = new InventoryNavigator(rootFolder).searchManagedEntities(
					new String[][] { {"Datacenter", "name" }, }, true);
			for(int i=0; i<dcs.length; i++)
			{
				LOGGER.info("Datacenter["+i+"]=" + dcs[i].getName());
			}
			LOGGER.info("\n============ Hosts ============");
			ManagedEntity[] hosts = new InventoryNavigator(rootFolder).searchManagedEntities(
					new String[][] { {"HostSystem", "name" }, }, true);
			for(int i=0; i<hosts.length; i++)
			{
				LOGGER.info("host["+i+"]=" + hosts[i].getName());
			}
			LOGGER.info("\n============ Virtual Machines ============");
			ManagedEntity[] vms = new InventoryNavigator(rootFolder).searchManagedEntities(
					new String[][] { {"VirtualMachine", "name" }, }, true);
			for(int i=0; i<vms.length; i++)
			{
				LOGGER.info("");LOGGER.info("");
				LOGGER.info("vm["+i+"]=" + vms[i].getName());
				//			String[][] typeInfo = { new String[]{"VirtualMachine", "name","runtime"}};
				String[][] typeInfo = { new String[]{"VirtualMachine", "name","summary.quickStats"}};

				PropertySpec[] pSpecs = PropertyCollectorUtil.buildPropertySpecArray(typeInfo);
				ObjectSpec[] oSpecs = createObjectSpecs(vms[i]);
				PropertyFilterSpec pSpec = new PropertyFilterSpec();
				pSpec.setPropSet(pSpecs);
				pSpec.setObjectSet(oSpecs);

				PropertyCollector pc = si.getPropertyCollector();
				PropertyFilter pf = pc.createFilter(pSpec, false);

				BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
				String version = "";
				//			while(true)
				//			{
				UpdateSet update = pc.checkForUpdates(version);
				if(update != null && update.getFilterSet() != null) 
				{
					handleUpdate(update);
					version = update.getVersion();
					LOGGER.info("version is:" + version);
				} 
				else
				{
					LOGGER.info("No update is present!");
				}

				VMStatsDTO vmDto = new VMStatsDTO();
				vmDto.setVmName(vms[i].getName());
				vmDto.setCpu(cpu);
				vmDto.setMem(mem);
				
				vmDtoList.add(vmDto);
				//				 q1.add(vms[i]+","+cpu+","+mem);
				//				LOGGER.info("\nPress <Enter> to check for updates");
				//				LOGGER.info("Enter 'exit' <Enter> to exit the program");
				//				if(console.readLine().trim().equalsIgnoreCase("exit"))
				//					break;
				//			}
				pf.destroyPropertyFilter();
			}
		}catch(Exception e){e.printStackTrace();}
		return vmDtoList;
	}

	static ObjectSpec[] createObjectSpecs(ManagedObject mo)
	{
		ObjectSpec[] oSpecs = new ObjectSpec[] { new ObjectSpec() };            
		oSpecs[0].setObj(mo.getMOR());
		oSpecs[0].setSkip(Boolean.FALSE);
		//oSpecs[0].setSelectSet(PropertyCollectorUtil.buildFullTraversal()); // in doubt here...
		return oSpecs;
	}

	static void handleUpdate(UpdateSet update) 
	{
		ArrayList vmUpdates = new ArrayList();
		ArrayList hostUpdates = new ArrayList();
		PropertyFilterUpdate[] pfus = update.getFilterSet(); 
		for(int i=0; i<pfus.length; i++) 
		{
			ObjectUpdate[] ous = pfus[i].getObjectSet();
			for(int j=0; j<ous.length; ++j) 
			{
				if(ous[j].getObj().getType().equals("VirtualMachine")) 
				{
					vmUpdates.add(ous[j]);
				} 
				else if(ous[j].getObj().getType().equals("HostSystem")) 
				{
					hostUpdates.add(ous[j]);
				}
			}
		}      
		if(vmUpdates.size() > 0) 
		{
			LOGGER.info("Virtual Machine updates:");
			for(Iterator vmi = vmUpdates.iterator(); vmi.hasNext();) 
			{
				handleObjectUpdate((ObjectUpdate)vmi.next());
			}
		}      
		if(hostUpdates.size() > 0) 
		{
			LOGGER.info("Host updates:");
			for(Iterator vmi = hostUpdates.iterator(); vmi.hasNext();) 
			{
				handleObjectUpdate((ObjectUpdate)vmi.next());
			}
		}
	}

	static void handleObjectUpdate(ObjectUpdate oUpdate) 
	{
		PropertyChange[] pc = oUpdate.getChangeSet();
		LOGGER.info(oUpdate.getKind() + "Data:");
		handleChanges(pc);
	}   

	static void handleChanges(PropertyChange[] changes) 
	{
		for(int i=0; i < changes.length; i++) 
		{
			String name = changes[i].getName();
			Object value = changes[i].getVal();
			PropertyChangeOp op = changes[i].getOp();
			if(op != PropertyChangeOp.remove)
			{
				LOGGER.info("  Property Name: " + name);
				if("summary.quickStats".equals(name)) 
				{               
					if(value instanceof VirtualMachineQuickStats) 
					{
						VirtualMachineQuickStats vmqs = (VirtualMachineQuickStats)value;
						cpu = vmqs.getOverallCpuUsage()==null ? "unavailable" : vmqs.getOverallCpuUsage().toString();
						mem = vmqs.getHostMemoryUsage()==null ? "unavailable" : vmqs.getHostMemoryUsage().toString();
						LOGGER.info("   Guest Status: " + vmqs.getGuestHeartbeatStatus().toString());
						LOGGER.info("   CPU Load %: " + cpu);
						LOGGER.info("   Memory Load %: " + mem);
					} 
					else if (value instanceof HostListSummaryQuickStats) 
					{
						HostListSummaryQuickStats hsqs = (HostListSummaryQuickStats)value;
						String cpu = hsqs.getOverallCpuUsage()==null ? "unavailable" : hsqs.getOverallCpuUsage().toString();
						String memory = hsqs.getOverallMemoryUsage()==null ? "unavailable" : hsqs.getOverallMemoryUsage().toString();
						LOGGER.info("   CPU Load %: " + cpu);
						LOGGER.info("   Memory Load %: " + memory);
					}
				} 
				else if("runtime".equals(name)) 
				{
					if(value instanceof VirtualMachineRuntimeInfo) 
					{

						VirtualMachineRuntimeInfo vmri = (VirtualMachineRuntimeInfo)value;
						cpu = vmri.getPowerState().toString();
						LOGGER.info("   Power State: " + vmri.getPowerState().toString());
						mem = vmri.getConnectionState().toString();
						LOGGER.info("   Connection State: " + vmri.getConnectionState().toString());
						Calendar bTime = vmri.getBootTime();
						if(bTime != null) 
						{
							LOGGER.info("   Boot Time: " + bTime.getTime());
						}
						Long mOverhead = vmri.getMemoryOverhead();
						if(mOverhead != null) 
						{
							LOGGER.info("   Memory Overhead: "+mOverhead);
						}
					} 
					else if(value instanceof HostRuntimeInfo) 
					{
						HostRuntimeInfo hri = (HostRuntimeInfo)value;
						LOGGER.info("   Connection State: " + hri.getConnectionState().toString());
						Calendar bTime = hri.getBootTime();
						if(bTime != null) 
						{
							LOGGER.info("   Boot Time: " + bTime.getTime());
						}
					}
				} 
				else if("name".equals(name)) 
				{
					LOGGER.info("   "+value);
				} 
				else 
				{
					LOGGER.info("   "+value.toString());
				}
			} 
			else 
			{
				LOGGER.info("Property Name: " +name+ " value removed.");
			}
		}

	}

	private static OptionSpec[] constructOptions() 
	{
		return new OptionSpec[]
				{
				new OptionSpec("vmname","String",1, "Name of the virtual machine", null)
				};
	}

	public static VMStatsDTO getVMStatsDTOs(String vmName)
	{
		VMStatsDTO vmStatsDTO = null;
		try{
			ServiceInstance si = new ServiceInstance(new URL("https://130.65.132.112/sdk"), "administrator" , "12!@qwQW", true);
			InventoryNavigator inv = new InventoryNavigator(si.getRootFolder());

			//ManagedEntity[] mes = new InventoryNavigator(root).searchManagedEntities("VirtualMachine");

			//for( int i = 0 ; i < mes.length; i++)
			//{
			//VirtualMachine vm = ((VirtualMachine) mes[i]);
			VirtualMachine vm = (VirtualMachine) inv.searchManagedEntity("VirtualMachine", vmName);

			//				if(vm.getRuntime().powerState.toString().equals("poweredOn"))
			//				{

			if(vm != null) {

			LOGGER.info("VM Name:"+vm.getName()); 	
			LOGGER.info("Guest OS:"+vm.getSummary().getConfig().guestFullName); 
			LOGGER.info("VM Version:"+vm.getConfig().version); 
			LOGGER.info("CPU:"+vm.getConfig().getHardware().numCPU+" vCPU"); 
			LOGGER.info("Memory:"+vm.getConfig().getHardware().memoryMB+" MB"); 
			//LOGGER.info("Memory Overhead:"+(long)vm.getConfig().initialOverhead.initialMemoryReservation/1000000f+" MB"); 
			LOGGER.info("VMware Tools:"+vm.getGuest().toolsRunningStatus); 
			LOGGER.info("IP Addresses:"+vm.getSummary().getGuest().getIpAddress()); 
			LOGGER.info("State:"+vm.getGuest().guestState); 

			LOGGER.info("consumed memory " +vm.getResourcePool().getSummary().getQuickStats().guestMemoryUsage);
			LOGGER.info("CPU Usage " +vm.getResourcePool().getSummary().getQuickStats().overallCpuUsage);
			LOGGER.info("====================*********=============");
			
//			vmStatsDTO = new VMStatsDTO();
//			vmStatsDTO.setVmName(vm.getName());
//			vmStatsDTO.setcpu(vm.getConfig().getHardware().numCPU);
//			vmStatsDTO.setcpu(vm.getConfig().getHardware().numCPU);
			
			
			String[][] typeInfo = { new String[]{"VirtualMachine", "name","summary.quickStats"}};

			PropertySpec[] pSpecs = PropertyCollectorUtil.buildPropertySpecArray(typeInfo);
			ObjectSpec[] oSpecs = createObjectSpecs(vm);
			PropertyFilterSpec pSpec = new PropertyFilterSpec();
			pSpec.setPropSet(pSpecs);
			pSpec.setObjectSet(oSpecs);

			PropertyCollector pc = si.getPropertyCollector();
			PropertyFilter pf = pc.createFilter(pSpec, false);

			BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
			String version = "";
			//			while(true)
			//			{
			UpdateSet update = pc.checkForUpdates(version);
			if(update != null && update.getFilterSet() != null) 
			{
				handleUpdate(update);
				version = update.getVersion();
				LOGGER.info("version is:" + version);
			} 
			else
			{
				LOGGER.info("No update is present!");
			}

			vmStatsDTO = new VMStatsDTO();
			vmStatsDTO.setVmName(vm.getName());
			vmStatsDTO.setCpu(vm.getResourcePool().getSummary().getQuickStats().overallCpuUsage.toString());
			vmStatsDTO.setMem(vm.getResourcePool().getSummary().getQuickStats().guestMemoryUsage.toString());
			vmStatsDTO.setIp(vm.getSummary().getGuest().getIpAddress());
			pf.destroyPropertyFilter();
			//				}

			//}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return vmStatsDTO;
	}



	//		si.getServerConnection().logout();
}




