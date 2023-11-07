package com1032.cw;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * A class to manage processes and their segments.
 * 
 * @author Samuel Ivuerah
 */
public class ProcessManager {

	/** A list to store the allocated processes. */
	private ArrayList<Process> allocatedProcesses;

	/** A list to store the allocated segments. */
	private ArrayList<Segment> allocatedSegments;

	/**
	 * A default constructor to intialise the processes and segments lists.
	 */
	public ProcessManager() {
		allocatedProcesses = new ArrayList<>(); // init
		allocatedSegments = new ArrayList<>();
	}

	/**
	 * A method to allocate a process with all its segments, to memory.
	 * 
	 * @param process The process to be allocated to memory.
	 * @param memory  The memory it should be allocated to.
	 * @return 1 if it was successful, 0 otherwise.
	 */
	public int allocate(Process process, Memory memory) {
		if (allocatedProcesses.contains(process)) { // Check if the process is already allocated.
			System.out.println("Error: Process " + process.getPid() + " is already allocated in memory.");
			return -1;
		}

		int processSize = calculateProcessSize(process); // Calculating the size of the process.
		if (processSize > memory.getAvailableMemory()) { // Checking if there is sufficient memory.
			System.out.println("Error: Insufficient memory to allocate process " + process.getPid());
			return -1;
		}

		for (Segment segment : process.getSegmentTable().getSegments()) { // Iterating through segments.
			String PId = Integer.toString(process.getPid()); // get PID as a string.
			String SId = Integer.toString(segment.getId()); // get the SID as a string.
			boolean tlbHit = memory.lookupInTLB(PId, SId) != null; // assess whether TLB hit or miss.

			if (allocatedSegments.contains(segment)) { // Identify segments that are already allocated.
				System.out.println("Skipping: Segment " + segment.toString());
				System.out.println(
						(tlbHit ? "TLB hit" : "TLB miss") + " for " + process.toString() + " " + segment.toString());
				// State whether TLB hit or miss.
				continue; // Skip them
			}

			if (segment.getLimit() > memory.getAvailableMemory()) { // Second check to make sure a segment can be
																	// allocated.
				System.out.println("Error: Insufficient memory to allocate segment " + segment.toString());
				continue; // Skip
			}

			segment.setValid(true); // Set the Invalid-valid bit for the segment.
			segment.setBase(memory.getOsSize() + calculateTotalSegmentSize()); // Set the new base of the segment.
			memory.addToTLB(PId, SId, segment.getBase()); // Add to TLB.
			System.out.println(
					(tlbHit ? "TLB hit" : "TLB miss") + " for " + process.toString() + " " + segment.toString());
			// State whether TLB hit or miss.
			allocatedSegments.add(segment); // Add the segment.
			memory.addSegmentToProcessMapping(segment.getId(), process.getPid());
		}

		allocatedProcesses.add(process); // Add the process
		return 1;
	}

	/**
	 * A method to allocate a specific segment of a process to memory.
	 * 
	 * @param process The process the segment is coming from.
	 * @param segment The segment to be allocated to memory.
	 * @param memory  The memory it should be allocated to.
	 */
	public void allocate(Process process, Segment segment, Memory memory) {
		if (allocatedSegments.contains(segment)) { // Check if the segment has already been allocated.
			System.out.println("Error: Segment " + segment.toString() + " is already allocated in memory.");
			return;
		}

		if (!process.getSegmentTable().getSegments().contains(segment)) { // Check if the segment belongs to the
																			// process.
			System.out.println(
					"Error: Segment " + segment.toString() + " does not belong to the process " + process.getPid());
			return;
		}

		if (segment.getLimit() > memory.getAvailableMemory()) { // Check if there is sufficient memory.
			System.out.println("Error: Insufficient memory to allocate segment " + segment.toString());
			return;
		}

		segment.setValid(true); // Set the invalid-valid bit.
		segment.setBase(memory.getOsSize() + calculateTotalSegmentSize()); // Set the base.

		String PId = Integer.toString(process.getPid()); // get PID as a string.
		String SId = Integer.toString(segment.getId()); // get the SID as a string.
		boolean tlbHit = memory.lookupInTLB(PId, SId) != null; // assess whether TLB hit or miss.
		memory.addToTLB(PId, SId, segment.getBase());

		// State whether TLB hit or miss.
		System.out.println((tlbHit ? "TLB hit" : "TLB miss") + " for " + process.toString() + " " + segment.toString());

		allocatedSegments.add(segment); // Add segment
		memory.addSegmentToProcessMapping(segment.getId(), process.getPid());
	}

	/**
	 * A method to deallocate a process and all its segments from memory.
	 * 
	 * @param process The process to deallocated.
	 * @param memory  The memory it should be allocated to.
	 * @return 1 if it was successful, 0 otherwise.
	 */
	public int deallocate(Process process, Memory memory) {
		if (!allocatedProcesses.contains(process)) { // Check if the process was allocated in the first place.
			System.out.println("Error: Process " + process.getPid() + " is not allocated in memory.");
			return -1;
		}

		// Iterate through segments in the process' segment table.
		for (Segment seg : process.getSegmentTable().getSegments()) {
			if (allocatedSegments.contains(seg)) { // Check if the segment is allocated
				String PId = Integer.toString(process.getPid()); // get PID as a string.
				String SId = Integer.toString(seg.getId()); // get the SID as a string.
				boolean tlbHit = memory.lookupInTLB(PId, SId) != null; // assess whether TLB hit or miss.
				seg.setValid(false); // Set the invalid-valid bit.
				// Make the address null as it no longer exists.
				memory.addToTLB(PId, SId, null);
				// State whether TLB hit or miss.
				System.out.println(
						(tlbHit ? "TLB hit" : "TLB miss") + " for " + process.toString() + " " + seg.toString());

				allocatedSegments.remove(seg); // Remove segment.
				seg.setBase(0); // Reset its base
			}
		}
		// Remove process from allocatedProcesses list
		allocatedProcesses.remove(process);

		compact(memory); // Compact the memory
		return 1;
	}

	/**
	 * A method to deallocate a specific segment of a process from memory.
	 * 
	 * @param process The process the segment belongs to.
	 * @param segment The segment to be deallocated.
	 * @param memory  The memory it should be allocated to.
	 * @return 1 if it was successful, 0 otherwise.
	 */
	public int deallocate(Process process, Segment segment, Memory memory) {
		if (!allocatedProcesses.contains(process)) { // Check if the process is actually allocated.
			System.out.println("Error: Process " + process.getPid() + " is not allocated in memory.");
			return -1;
		}

		if (!allocatedSegments.contains(segment)) { // Check if the segment belongs to the process.
			System.out.println("Error: Segment " + segment.toString() + " is not allocated in memory.");
			return -1;
		}

		String PId = Integer.toString(process.getPid()); // get PID as a string.
		String SId = Integer.toString(segment.getId()); // get the SID as a string.
		boolean tlbHit = memory.lookupInTLB(PId, SId) != null; // assess whether TLB hit or miss.
		segment.setValid(false); // Set the Invalid-valid bit.
		// Make the address null as it no longer exists.
		memory.addToTLB(PId, SId, null);
		// State whether TLB hit or miss.
		System.out.println((tlbHit ? "TLB hit" : "TLB miss") + " for " + process.toString() + " " + segment.toString());
		allocatedSegments.remove(segment); // Remove segment.
		segment.setBase(0); // reset the base.
		compact(memory); // Compact the memory.
		return 1;
	}

	/**
	 * A method to resize a process, with a new limits for the segments.
	 * 
	 * @param process  The process to be resized.
	 * @param segments The new limits for the segments.
	 * @param memory   The memory it should be allocated to.
	 * @return 1 if it was successful, 0 otherwise.
	 */
	public int resize(Process process, String segments, Memory memory) {
		if (!allocatedProcesses.contains(process)) { // Check if the process has been allocated.
			System.out.println("Error: Process " + process.getPid() + " is not allocated in memory.");
			return -1;
		}

		// List to keep track of segmentToRemoveFromMemory
		ArrayList<Segment> segmentsToRemoveFromMemory = process.resize(segments);

		for (Segment seg : segmentsToRemoveFromMemory) { // Iterate through the segmentsToRemove List.
			if (allocatedSegments.contains(seg)) { // Check if the segment is allocated.
				seg.setBase(0); // Reset the base.
				allocatedSegments.remove(seg); // Remove segment.
			}
		}

		compact(memory); // Compact the memory.
		return 1;
	}

	/**
	 * A method to find a segment in memory by using a memory address.
	 * 
	 * @param address The memory address to check for a segment in.
	 * @param memory  The memory to check.
	 */
	public void findSegmentByAddress(int address, Memory memory) {
		boolean tlbHit = false;
		Segment foundSegment = null;
		int limit = 0;

		for (Segment segment : allocatedSegments) { // Iterate through allocated segments.
			// Ensure they are within the range.
			if (segment.getBase() <= address && address < segment.getBase() + segment.getLimit()) {
				foundSegment = segment; // Get the segment.
				limit = segment.getLimit(); // Get the limit of the segment.
				break;
			}
		}

		if (foundSegment != null) { // If there was a segment found.
			String SId = Integer.toString(foundSegment.getId()); // Get SID.
			String PId = Integer.toString(memory.getProcessIdBySegmentId(foundSegment.getId())); // Get PID.
			Integer tlbAddress = memory.lookupInTLB(PId, SId); // Find the address.
			// Work out if the address falls in within the range.
			if (tlbAddress != null && (tlbAddress <= address && address < tlbAddress + limit)) {
				tlbHit = true;
			}

			// Assess of the segment has the right permissions.
			if (foundSegment.hasReadPermission()) {
				System.out.println("Segment found: " + foundSegment.toString());
				if (tlbHit) { // If there was a hit
					System.out.println("TLB hit for address " + address + ".");
				} else {
					System.out.println("TLB miss for address " + address + ".");
				}
				return;
			} else {
				System.out.println("Error: Segment at address " + address + " does not have read permissions.");
				return;
			}
		}

		System.out.println("TLB miss for address " + address + ".");
		System.out.println("Error: No segment found at address " + address + ".");
	}

	/**
	 * A method to compact loaded processes/segments to fill in gaps.
	 * 
	 * @param memory The memory to compact.
	 */
	public void compact(Memory memory) {
		// Compare the bases of the segments and order them.
		allocatedSegments.sort(Comparator.comparingInt(Segment::getBase));

		// Get the first available address and store, making this the next available
		// address.
		int totalSegmentSize = memory.getOsSize();
		for (Segment segment : allocatedSegments) { // Iterate through segments.
			if (segment.getBase() != totalSegmentSize) { // If the segment's base isn't the first location.
				segment.setBase(totalSegmentSize); // Set the base of that segment to the next location.
			}
			totalSegmentSize += segment.getLimit(); // Update next available location.
		}
	}

	/**
	 * A method to calculate the total segment size.
	 * 
	 * @return The total segment size.
	 */
	private int calculateTotalSegmentSize() {
		int totalSegmentSize = 0; // Initial size.
		for (Segment segment : allocatedSegments) { // Iterate through allocated segments.
			totalSegmentSize += segment.getLimit(); // Add the limits.
		}
		return totalSegmentSize;
	}

	/**
	 * A method to calculate the total Process size.
	 * 
	 * @param process The process in question.
	 * @return The size of the process.
	 */
	private int calculateProcessSize(Process process) {
		int size = 0; // Intial size.
		for (Segment segment : process.getSegmentTable().getSegments()) { // Iterate through segments.
			size += segment.getLimit(); // Add the limits.
		}
		return size;
	}

	public ArrayList<Process> getAllocatedProcesses() {
		return allocatedProcesses;
	}

	public ArrayList<Segment> getAllocatedSegments() {
		return allocatedSegments;
	}
}
