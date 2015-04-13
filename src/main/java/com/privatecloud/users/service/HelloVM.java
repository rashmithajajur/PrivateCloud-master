package com.privatecloud.users.service;

import java.net.URL;


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

public class HelloVM {


	private static String vmname ;
	private static String vmname1;
	private static String currentHostIp;
	private static Folder folder;
	private static ServiceInstance si ;
	private static VirtualMachine vm ;
	private static HostSystem hs;
	private static boolean flag=true;
	private static String snapshotname;
	private static ResourcePool[] rp;

	public static void create(String v, String os) {

		
		String resourcePool = null;
		String host;
		try{
			vmname = v;
			if("win".equals(os))
				vmname1 = "T12-VM-Win-TPLATE";
			else
				vmname1 = "T12-VM01-Ubu";
		
			ServiceInstance si = new ServiceInstance(new URL("https://130.65.132.112/sdk"), "administrator" , "12!@qwQW", true);
			Folder rootFolder = si.getRootFolder();
		
			Datacenter dc = (Datacenter) si.getSearchIndex().findByInventoryPath("T12-DC");
			System.out.println(dc.getName());
			
				HostSystem targetHost = null;

					targetHost = (HostSystem) new InventoryNavigator(rootFolder).searchManagedEntity("HostSystem", "130.65.132.242");
					System.out.println("HOST   " + targetHost.getName());

				ResourcePool targetPool = null;
				if (resourcePool == null) {
					ComputeResource hostResource = (ComputeResource) targetHost.getParent();
					ManagedEntity[] rp = (ManagedEntity[]) new InventoryNavigator(rootFolder).searchManagedEntities("ResourcePool");
					for (int i = 0; i < rp.length; i++) {
						ResourcePool r = (ResourcePool) rp[i];

							targetPool = r;

					}
				} else {
					
			
				targetPool = (ResourcePool) new InventoryNavigator(rootFolder).searchManagedEntities("ResourcePool")[0];
//				
					}

				VirtualMachine vm = (VirtualMachine) new InventoryNavigator(
				        rootFolder).searchManagedEntity(
				            "VirtualMachine", vmname1);

				Datastore ds = (Datastore) new InventoryNavigator(rootFolder).searchManagedEntity("Datastore","nfs4team12");

				    if(vm==null)
				    {
				      System.out.println("No VM " + vmname1 + " found");
				      si.getServerConnection().logout();
				      return;
				    }

				    VirtualMachineCloneSpec cloneSpec = 
				      new VirtualMachineCloneSpec();
				    
				    VirtualMachineRelocateSpec spec = new VirtualMachineRelocateSpec();
					spec.setPool(targetPool.getMOR());
					spec.setHost(targetHost.getMOR());
					spec.setDatastore(ds.getMOR());

					cloneSpec.setLocation(spec);
				    cloneSpec.setPowerOn(false);
				    cloneSpec.setTemplate(false);

//				    String cloneName = "CLONE 100";
				    System.out.println(vm.getParent().getName());
					Task task = vm.cloneVM_Task((Folder) dc.getVmFolder(), 
							vmname , cloneSpec);
				    System.out.println("Launching the VM clone task. " +
				    		"Please wait ...");

				  
				    if(task.waitForTask()==task.SUCCESS)
				    {
				      System.out.println("VM got cloned successfully.");
				    }
				    else
				    {
				      System.out.println("Failure -: VM cannot be cloned");
				    }
				System.out.println("Here");
				
				

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

}
