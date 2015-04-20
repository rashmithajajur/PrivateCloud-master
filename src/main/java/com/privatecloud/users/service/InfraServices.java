package com.privatecloud.users.service;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vmware.vim25.HostSystemConnectionState;
import com.vmware.vim25.ManagedObjectReference;
import com.vmware.vim25.VirtualMachineCloneSpec;
import com.vmware.vim25.VirtualMachineMovePriority;
import com.vmware.vim25.VirtualMachinePowerState;
import com.vmware.vim25.VirtualMachineRelocateSpec;
import com.vmware.vim25.VirtualMachineSnapshotInfo;
import com.vmware.vim25.VirtualMachineSnapshotTree;
import com.vmware.vim25.mo.ComputeResource;
import com.vmware.vim25.mo.Datacenter;
import com.vmware.vim25.mo.Datastore;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.HostSystem;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ResourcePool;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.Task;
import com.vmware.vim25.mo.VirtualMachine;
import com.vmware.vim25.mo.ManagedEntity;

public class InfraServices {

	private static String vmname;
	private static String vmname1;
	private static String currentHostIp;
	private static Folder folder;
	private static ServiceInstance si;
	private static VirtualMachine vm;
	private static HostSystem hs;
	private static boolean flag = true;
	private static String snapshotname;
	private static ResourcePool[] rp;

	
	private static Logger LOGGER = LoggerFactory.getLogger("InfraServices");
	
	public static void create(String v, String os) {

		String resourcePool = null;
		String host;
		try {
			vmname = v;
			if ("win".equals(os))
				vmname1 = "T12-VM-Win-TPLATE";
			else
				vmname1 = "T12-VM01-Ubu";

			ServiceInstance si = new ServiceInstance(new URL(
					"https://130.65.132.112/sdk"), "administrator", "12!@qwQW",
					true);
			Folder rootFolder = si.getRootFolder();

			Datacenter dc = (Datacenter) si.getSearchIndex()
					.findByInventoryPath("T12-DC");
			LOGGER.info(dc.getName());

			HostSystem targetHost = null;

			targetHost = (HostSystem) new InventoryNavigator(rootFolder)
					.searchManagedEntity("HostSystem", "130.65.132.242");
			LOGGER.info("HOST   " + targetHost.getName());

			ResourcePool targetPool = null;
			if (resourcePool == null) {
				ComputeResource hostResource = (ComputeResource) targetHost
						.getParent();
				ManagedEntity[] rp = (ManagedEntity[]) new InventoryNavigator(
						rootFolder).searchManagedEntities("ResourcePool");
				for (int i = 0; i < rp.length; i++) {
					ResourcePool r = (ResourcePool) rp[i];

					targetPool = r;

				}
			} else {

				targetPool = (ResourcePool) new InventoryNavigator(rootFolder)
						.searchManagedEntities("ResourcePool")[0];
				//
			}

			VirtualMachine vm = (VirtualMachine) new InventoryNavigator(
					rootFolder).searchManagedEntity("VirtualMachine", vmname1);

			Datastore ds = (Datastore) new InventoryNavigator(rootFolder)
					.searchManagedEntity("Datastore", "nfs4team12");

			if (vm == null) {
				LOGGER.info("No VM " + vmname1 + " found");
				si.getServerConnection().logout();
				return;
			}

			VirtualMachineCloneSpec cloneSpec = new VirtualMachineCloneSpec();

			VirtualMachineRelocateSpec spec = new VirtualMachineRelocateSpec();
			spec.setPool(targetPool.getMOR());
			spec.setHost(targetHost.getMOR());
			spec.setDatastore(ds.getMOR());

			cloneSpec.setLocation(spec);
			cloneSpec.setPowerOn(false);
			cloneSpec.setTemplate(false);

			// String cloneName = "CLONE 100";
			LOGGER.info(vm.getParent().getName());
			Task task = vm.cloneVM_Task((Folder) dc.getVmFolder(), vmname,
					cloneSpec);
			LOGGER.info("Launching the VM clone task. "
					+ "Please wait ...");

			if (task.waitForTask() == task.SUCCESS) {
				LOGGER.info("VM got cloned successfully.");
			} else {
				LOGGER.info("Failure -: VM cannot be cloned");
			}
			LOGGER.info("Here");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean isPoweredOn(String vmname){
		try 
		{
			ServiceInstance si = new ServiceInstance(new URL("https://130.65.132.112/sdk"), "administrator", "12!@qwQW",
					true);

			Folder rootFolder = si.getRootFolder();
			VirtualMachine vm = (VirtualMachine) new InventoryNavigator(
					rootFolder).searchManagedEntity("VirtualMachine", vmname);

			return (vm.getRuntime().getPowerState().toString().contains("poweredOn"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean powerON(String vmname) {

		try 
		{
			ServiceInstance si = new ServiceInstance(new URL("https://130.65.132.112/sdk"), "administrator", "12!@qwQW",
					true);

			Folder rootFolder = si.getRootFolder();
			VirtualMachine vm = (VirtualMachine) new InventoryNavigator(
					rootFolder).searchManagedEntity("VirtualMachine", vmname);

			Task task = vm.powerOnVM_Task(null);
			if (task.waitForTask() == Task.SUCCESS) {
				LOGGER.info(vmname + " powered on");
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public static boolean powerOFF(String vmname) {

		try {
			ServiceInstance si = new ServiceInstance(new URL(
					"https://130.65.132.112/sdk"), "administrator", "12!@qwQW",
					true);

			Folder rootFolder = si.getRootFolder();
			VirtualMachine vm = (VirtualMachine) new InventoryNavigator(
					rootFolder).searchManagedEntity("VirtualMachine", vmname);

			Task task = vm.powerOffVM_Task();
			if (task.waitForTask() == Task.SUCCESS) {
				LOGGER.info(vmname + " powered off");
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

}
