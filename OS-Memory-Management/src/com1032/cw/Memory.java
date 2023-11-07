package com1032.cw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class to manage memory.
 * 
 * @author Samuel Ivuerah
 */
public class Memory {

	/** The size of the OS. */
	private int osSize;

	/** The size of the entire system. */
	private int totalSize;

	/** The process manager to manage the processes. */
	private ProcessManager processManager;

	/** The TLB manager to manage the TLB. */
	private TLBManager tlbManager;

	/** A mapping to map segment IDs to process IDs */
	private Map<Integer, Integer> segmentToProcessMap = new HashMap<>();

	/**
	 * A constructor so Memory can be instantiated with the size of the system and
	 * allow OS memory.
	 * 
	 * @param size   The size of the entire system.
	 * @param osSize The size of the OS.
	 * @throws IllegalArgumentException If either system or OS size are invalid.
	 */
	public Memory(int size, int osSize) throws IllegalArgumentException {
		if (size <= 0) {
			throw new IllegalArgumentException("Insufficient memory.");
		}
		if (osSize >= size || osSize <= 0) {
			throw new IllegalArgumentException("Invalid OS size");
		}
		this.totalSize = size;
		this.osSize = osSize;
		processManager = new ProcessManager(); // Creating a process manager.
		tlbManager = new TLBManager(); // Creating a TLB manager.
	}

	public int getOsSize() {
		return osSize;
	}

	/**
	 * A method to allocate a process with all its segments, to memory.
	 * 
	 * @param process The process to be allocated to memory.
	 * @return 1 if it was successful, 0 otherwise.
	 */
	public int allocate(Process process) {
		return processManager.allocate(process, this);
	}

	/**
	 * A method to allocate a specific segment of a process to memory.
	 * 
	 * @param process The process the segment is coming from.
	 * @param segment The segment to be allocated to memory.
	 */
	public void allocate(Process process, Segment segment) {
		processManager.allocate(process, segment, this);
	}

	/**
	 * A method to deallocate a process and all its segments from memory.
	 * 
	 * @param process The process to deallocated.
	 * @return 1 if it was successful, 0 otherwise.
	 */
	public int deallocate(Process process) {
		return processManager.deallocate(process, this);
	}

	/**
	 * A method to deallocate a specific segment of a process from memory.
	 * 
	 * @param process The process the segment belongs to.
	 * @param segment The segment to be deallocated.
	 * @return 1 if it was successful, 0 otherwise.
	 */
	public int deallocate(Process process, Segment segment) {
		return processManager.deallocate(process, segment, this);
	}

	/**
	 * A method to resize a process, with a new limits for the segments.
	 * 
	 * @param process  The process to be resized.
	 * @param segments The new limits for the segments.
	 * @return 1 if it was successful, 0 otherwise.
	 */
	public int resizeProcess(Process process, String segments) {
		return processManager.resize(process, segments, this);
	}

	/**
	 * A method to output the current state of memory to console.
	 */
	public void memoryState() {
		System.out.println("[OS " + osSize + "] | [Hole " + getAvailableMemory() + "]");
		//
	}

	/**
	 * A method to find a segment in memory by using a memory address.
	 * 
	 * @param address The memory address to check for a segment in.
	 */
	public void findSegmentByAddress(int address) {
		processManager.findSegmentByAddress(address, this);
	}

	/**
	 * A method to compact loaded processes/segments to fill in gaps.
	 */
	public void compact() {
		processManager.compact(this);
	}

	/**
	 * A method to lookup a combined PID and SID in the TLB.
	 * 
	 * @param pid the PID of the process.
	 * @param sid the SID of the segment.
	 * @return 1 if it was found, 0 otherwise.
	 */
	public Integer lookupInTLB(String pid, String sid) {
		return tlbManager.lookup(pid, sid);
	}

	/**
	 * A method to add a PID, SID and physical address to the TLB.
	 * 
	 * @param pid             The PID of the process.
	 * @param sid             The SID of the segment.
	 * @param physicalAddress The address.
	 */
	public void addToTLB(String pid, String sid, Integer physicalAddress) {
		tlbManager.addEntry(pid, sid, physicalAddress);
	}

	/**
	 * A method to ensure the mapping between segments and processes.
	 * 
	 * @param segmentId Segment's ID.
	 * @param processId Process' ID.
	 */
	public void addSegmentToProcessMapping(int segmentId, int processId) {
		segmentToProcessMap.put(segmentId, processId);
	}

	/**
	 * A method to get the process ID belonging to a segment.
	 * 
	 * @param segmentId The ID of the segment.
	 * @return The ID of the process, or -1 if there isn't one.
	 */
	public Integer getProcessIdBySegmentId(int segmentId) {
		Integer processId = segmentToProcessMap.get(segmentId);
		return processId != null ? processId : -1; // Return -1 or any other appropriate value for "not found" case
	}

	/**
	 * A method to calculate the available memory.
	 * 
	 * @return The units of memory free.
	 */
	public int getAvailableMemory() {
		int usedMemory = osSize;

		// Get the segments directly from the process manager
		List<Segment> allocatedSegments = processManager.getAllocatedSegments();

		// Calculate used memory by summing up the limit of all allocated segments
		usedMemory += allocatedSegments.stream().mapToInt(Segment::getLimit).sum();

		// Calculate and return available memory
		return totalSize - usedMemory;
	}

	/**
	 * A method to output the contents of the TLB.
	 */
	public void tlb() {
		System.out.println(tlbManager.toString());
	}

}
